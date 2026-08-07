package com.soc.agent.security

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.ImageFormat
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.hardware.camera2.CaptureRequest
import android.media.Image
import android.media.ImageReader
import android.net.Uri
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.ContextCompat
import com.soc.agent.database.AppDatabase
import com.soc.agent.database.entity.IntruderSelfieEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class IntruderSelfieManager private constructor(context: Context) {

    private val context = context.applicationContext
    private val db = AppDatabase.getInstance(context)
    private val settingsManager = SecuritySettingsManager.getInstance(context)
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    companion object {
        @Volatile
        private var INSTANCE: IntruderSelfieManager? = null

        fun getInstance(context: Context): IntruderSelfieManager =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: IntruderSelfieManager(context.applicationContext).also { INSTANCE = it }
            }
    }

    fun hasCameraPermission(): Boolean =
        ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_GRANTED

    fun requestCameraPermission(activity: Activity, requestCode: Int) {
        activity.requestPermissions(
            arrayOf(Manifest.permission.CAMERA),
            requestCode
        )
    }

    fun maybeCaptureSelfie(
        activity: Activity,
        packageName: String,
        appName: String,
        gateMethod: String,
        failureReason: String,
        attemptNumber: Int
    ) {
        scope.launch {
            val shouldCapture = settingsManager.shouldCaptureSelfie(attemptNumber)
            if (!shouldCapture) return@launch

            if (!hasCameraPermission()) {
                Log.w("IntruderSelfie", "Camera permission not granted, skipping selfie capture")
                return@launch
            }

            val imagePath = captureSelfieBlocking()
            if (imagePath != null) {
                val record = IntruderSelfieEntity(
                    packageName = packageName,
                    appName = appName,
                    gateMethod = gateMethod,
                    timestamp = System.currentTimeMillis(),
                    imagePath = imagePath,
                    failureReason = failureReason,
                    attemptNumber = attemptNumber
                )
                db.intruderSelfieDao().insert(record)
            }
        }
    }

    private fun captureSelfieBlocking(): String? {
        val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        var cameraId: String? = null

        try {
            for (id in cameraManager.cameraIdList) {
                val characteristics = cameraManager.getCameraCharacteristics(id)
                val facing = characteristics.get(CameraCharacteristics.LENS_FACING)
                if (facing == CameraCharacteristics.LENS_FACING_FRONT) {
                    cameraId = id
                    break
                }
            }
        } catch (e: CameraAccessException) {
            Log.e("IntruderSelfie", "Camera access error", e)
            return null
        }

        cameraId ?: return null

        val imageReader = ImageReader.newInstance(640, 480, ImageFormat.JPEG, 2)
        val future = CompletableFuture<String>()
        val mainHandler = Handler(Looper.getMainLooper())

        imageReader.setOnImageAvailableListener({ reader ->
            val image = reader.acquireLatestImage() ?: return@setOnImageAvailableListener
            val path = saveImageToStorage(image)
            image.close()
            future.complete(path)
        }, mainHandler)

        try {
            cameraManager.openCamera(cameraId, object : android.hardware.camera2.CameraDevice.StateCallback() {
                override fun onOpened(camera: android.hardware.camera2.CameraDevice) {
                    val surface = imageReader.surface
                    try {
                        @Suppress("DEPRECATION")
                        camera.createCaptureSession(
                            listOf(surface),
                            object : android.hardware.camera2.CameraCaptureSession.StateCallback() {
                                override fun onConfigured(session: android.hardware.camera2.CameraCaptureSession) {
                                    val request = camera.createCaptureRequest(android.hardware.camera2.CameraDevice.TEMPLATE_STILL_CAPTURE)
                                    request.addTarget(surface)
                                    session.capture(request.build(), null, mainHandler)
                                }
                                override fun onConfigureFailed(session: android.hardware.camera2.CameraCaptureSession) {
                                    future.completeExceptionally(Exception("Camera session config failed"))
                                }
                            },
                            mainHandler
                        )
                    } catch (e: CameraAccessException) {
                        future.completeExceptionally(e)
                    }
                }
                override fun onDisconnected(camera: android.hardware.camera2.CameraDevice) {
                    camera.close()
                    future.completeExceptionally(Exception("Camera disconnected"))
                }
                override fun onError(camera: android.hardware.camera2.CameraDevice, error: Int) {
                    camera.close()
                    future.completeExceptionally(Exception("Camera error: $error"))
                }
            }, mainHandler)
        } catch (e: CameraAccessException) {
            Log.e("IntruderSelfie", "Error opening camera", e)
            return null
        }

        return try {
            future.get(5, TimeUnit.SECONDS)
        } catch (e: Exception) {
            Log.e("IntruderSelfie", "Selfie capture timeout or error", e)
            null
        }
    }

    private fun saveImageToStorage(image: Image): String? {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileName = "INTRUDER_${timeStamp}.jpg"

        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/SOCAgent/IntruderSelfies")
        }

        val resolver = context.contentResolver
        val uri: Uri? = try {
            resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        } catch (e: Exception) {
            Log.e("IntruderSelfie", "Failed to create MediaStore entry", e)
            return null
        }

        uri ?: return null

        val buffer = image.planes[0].buffer
        val bytes = ByteArray(buffer.remaining())
        buffer.get(bytes)

        try {
            resolver.openOutputStream(uri).use { outputStream: OutputStream? ->
                outputStream?.write(bytes)
            }
        } catch (e: Exception) {
            Log.e("IntruderSelfie", "Failed to write image", e)
            resolver.delete(uri, null, null)
            return null
        }

        return uri.toString()
    }
}