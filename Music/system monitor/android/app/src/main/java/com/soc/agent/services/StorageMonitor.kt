package com.soc.agent.services

import android.content.Context
import android.os.Environment
import android.os.StatFs
import com.soc.agent.api.dto.StorageSample

/**
 * Samples storage usage for the internal data partition and, when present, the
 * external (SD) storage, using [StatFs] on the respective mount points.
 */
class StorageMonitor(private val context: Context) {

    fun sample(): List<StorageSample> {
        val result = mutableListOf<StorageSample>()

        // Internal /data partition.
        val internalPath = Environment.getDataDirectory()
        result += statFor(internalPath, "internal", internalPath.absolutePath, "ext4")

        // External storage if mounted and readable.
        val externalDir = Environment.getExternalStorageDirectory()
        val externalState = Environment.getExternalStorageState()
        if (externalState == Environment.MEDIA_MOUNTED ||
            externalState == Environment.MEDIA_MOUNTED_READ_ONLY
        ) {
            result += statFor(externalDir, "external", externalDir.absolutePath, "vfat")
        }

        return result
    }

    private fun statFor(path: java.io.File, filesystem: String, mount: String, type: String): StorageSample {
        return try {
            val stat = StatFs(path.absolutePath)
            val blockSize = stat.blockSizeLong
            val totalBlocks = stat.blockCountLong
            val availableBlocks = stat.availableBlocksLong
            val freeBlocks = stat.freeBlocksLong

            val totalB = totalBlocks * blockSize
            val freeB = availableBlocks * blockSize // free to non-root users
            val usedB = (totalB - freeB).coerceAtLeast(0L)
            val usagePct = if (totalB > 0) (usedB.toDouble() / totalB.toDouble()) * 100.0 else 0.0

            StorageSample(
                filesystem = filesystem,
                mount = mount,
                type = type,
                totalB = totalB,
                usedB = usedB,
                freeB = freeB,
                usagePct = usagePct
            )
        } catch (e: Exception) {
            // Mount point may not be accessible (e.g. permission or no device).
            StorageSample(
                filesystem = filesystem,
                mount = mount,
                type = type,
                totalB = 0L,
                usedB = 0L,
                freeB = 0L,
                usagePct = 0.0
            )
        }
    }
}