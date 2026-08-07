package com.soc.agent.ui.pattern

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.soc.agent.security.PatternLock
import kotlin.math.hypot

/**
 * Custom 3x3 Android-style pattern entry view.
 *
 * Renders the dot grid and the live stroke as the finger moves, and reports a
 * completed gesture through [onPatternComplete]. The raw dot sequence is
 * normalised through [PatternLock.normalize] before being handed off, so a
 * stroke that skips an intermediate dot (e.g. corner-to-corner across the
 * centre) yields the canonical pattern the hash was derived from.
 *
 * The view is purely presentational + gesture-input; it holds no secrets and
 * stores nothing. Security lives in [PatternLock].
 */
class PatternView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    /** Invoked with the normalised dot-index list when the finger lifts. */
    var onPatternComplete: ((List<Int>) -> Unit)? = null

    private val dotPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = 0xFF8B94B5.toInt()
    }
    private val highlightPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = 0xFF7C4DFF.toInt()
    }
    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = 0xFF7C4DFF.toInt()
        strokeWidth = 8f.toDp()
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }
    private val errorPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = 0xFFFF5252.toInt()
        strokeWidth = 8f.toDp()
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }

    private val dotRadiusDefault = 12f.toDp()
    private val dotRadiusHighlight = 20f.toDp()

    private var centerX = 0f
    private var centerY = 0f
    private var touchRadius = 0f

    private val nodes = ArrayList<Int>(PatternLock.MAX_POINTS)
    private var touchX = 0f
    private var touchY = 0f
    private var tracking = false
    private var isError = false

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2f
        centerY = h / 2f
        // Dots sit at ±spacing from centre; spacing must fit 2 steps inside the
        // smaller dimension.
        val fitted = minOf(w, h) - resources.displayMetrics.density * 96f
        val step = fitted / 2f
        touchRadius = resources.displayMetrics.density * 48f
        _step = step
    }

    /** Grid step (distance between adjacent dots) recomputed on size change. */
    private var _step = 200f
    private fun dotCenter(i: Int): Pair<Float, Float> {
        val col = i % 3
        val row = i / 3
        val x = centerX + (col - 1) * _step
        val y = centerY + (row - 1) * _step
        return x to y
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Completed stroke (thick line through the chosen dots).
        if (nodes.size > 1) {
            val line = if (isError) errorPaint else strokePaint
            val p = Path()
            val (sx, sy) = dotCenter(nodes[0])
            p.moveTo(sx, sy)
            for (i in 1 until nodes.size) {
                val (x, y) = dotCenter(nodes[i])
                p.lineTo(x, y)
            }
            // Live segment to the finger while tracking.
            if (tracking) p.lineTo(touchX, touchY)
            canvas.drawPath(p, line)
        }

        // Dots.
        for (i in 0..8) {
            val (x, y) = dotCenter(i)
            val selected = i in nodes
            val radius = if (selected) dotRadiusHighlight else dotRadiusDefault
            val dotPaint = if (selected) highlightPaint else this.dotPaint
            canvas.drawCircle(x, y, radius, dotPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                tracking = true
                isError = false
                nodes.clear()
                touchX = event.x
                touchY = event.y
                maybeAddNode(event.x, event.y)
                invalidate()
                performHapticFeedback(android.view.HapticFeedbackConstants.VIRTUAL_KEY)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                touchX = event.x
                touchY = event.y
                maybeAddNode(event.x, event.y)
                invalidate()
                return true
            }
            MotionEvent.ACTION_UP -> {
                tracking = false
                invalidate()
                if (nodes.size > 1) {
                    onPatternComplete?.invoke(nodes.toList())
                } else {
                    nodes.clear()
                    invalidate()
                }
                return true
            }
            MotionEvent.ACTION_CANCEL -> {
                tracking = false
                nodes.clear()
                invalidate()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    private fun maybeAddNode(x: Float, y: Float) {
        for (i in 0..8) {
            if (i in nodes) continue
            val (dx, dy) = dotCenter(i)
            if (hypot(x - dx, y - dy) <= touchRadius) {
                nodes.add(i)
                // Line made with full freedom; normalise never repeats dots so
                // duplicate protection in PatternLock keeps the gesture canonical.
                invalidate()
                performClick()
                break
            }
        }
    }

    /** Clear the current stroke (e.g. after an incorrect attempt). */
    fun reset() {
        nodes.clear()
        tracking = false
        isError = false
        invalidate()
    }

    /** Flash the grid red to flag a mismatch without clearing the dots. */
    fun showError() {
        isError = true
        invalidate()
        postDelayed({ isError = false; invalidate() }, 600L)
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }

    private fun Float.toDp(): Float =
        this * resources.displayMetrics.density
}