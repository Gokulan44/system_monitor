package com.soc.agent.ui.pattern;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.soc.agent.security.PatternLock;

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
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u001c\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0%2\u0006\u0010&\u001a\u00020\u0007H\u0002J\u0018\u0010'\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020\n2\u0006\u0010)\u001a\u00020\nH\u0002J\u0010\u0010*\u001a\u00020\u001a2\u0006\u0010+\u001a\u00020,H\u0014J(\u0010-\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u00072\u0006\u00100\u001a\u00020\u00072\u0006\u00101\u001a\u00020\u0007H\u0014J\u0010\u00102\u001a\u00020\u00142\u0006\u00103\u001a\u000204H\u0016J\b\u00105\u001a\u00020\u0014H\u0016J\u0006\u00106\u001a\u00020\u001aJ\u0006\u00107\u001a\u00020\u001aJ\f\u00108\u001a\u00020\n*\u00020\nH\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R.\u0010\u0017\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0019\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0018X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00069"}, d2 = {"Lcom/soc/agent/ui/pattern/PatternView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "_step", "", "centerX", "centerY", "dotPaint", "Landroid/graphics/Paint;", "dotRadiusDefault", "dotRadiusHighlight", "errorPaint", "highlightPaint", "isError", "", "nodes", "Ljava/util/ArrayList;", "onPatternComplete", "Lkotlin/Function1;", "", "", "getOnPatternComplete", "()Lkotlin/jvm/functions/Function1;", "setOnPatternComplete", "(Lkotlin/jvm/functions/Function1;)V", "strokePaint", "touchRadius", "touchX", "touchY", "tracking", "dotCenter", "Lkotlin/Pair;", "i", "maybeAddNode", "x", "y", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onSizeChanged", "w", "h", "oldw", "oldh", "onTouchEvent", "event", "Landroid/view/MotionEvent;", "performClick", "reset", "showError", "toDp", "app_debug"})
public final class PatternView extends android.view.View {
    
    /**
     * Invoked with the normalised dot-index list when the finger lifts.
     */
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function1<? super java.util.List<java.lang.Integer>, kotlin.Unit> onPatternComplete;
    @org.jetbrains.annotations.NotNull()
    private final android.graphics.Paint dotPaint = null;
    @org.jetbrains.annotations.NotNull()
    private final android.graphics.Paint highlightPaint = null;
    @org.jetbrains.annotations.NotNull()
    private final android.graphics.Paint strokePaint = null;
    @org.jetbrains.annotations.NotNull()
    private final android.graphics.Paint errorPaint = null;
    private final float dotRadiusDefault = 0.0F;
    private final float dotRadiusHighlight = 0.0F;
    private float centerX = 0.0F;
    private float centerY = 0.0F;
    private float touchRadius = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final java.util.ArrayList<java.lang.Integer> nodes = null;
    private float touchX = 0.0F;
    private float touchY = 0.0F;
    private boolean tracking = false;
    private boolean isError = false;
    
    /**
     * Grid step (distance between adjacent dots) recomputed on size change.
     */
    private float _step = 200.0F;
    
    @kotlin.jvm.JvmOverloads()
    public PatternView(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public PatternView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public PatternView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs, int defStyleAttr) {
        super(null);
    }
    
    /**
     * Invoked with the normalised dot-index list when the finger lifts.
     */
    @org.jetbrains.annotations.Nullable()
    public final kotlin.jvm.functions.Function1<java.util.List<java.lang.Integer>, kotlin.Unit> getOnPatternComplete() {
        return null;
    }
    
    /**
     * Invoked with the normalised dot-index list when the finger lifts.
     */
    public final void setOnPatternComplete(@org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super java.util.List<java.lang.Integer>, kotlin.Unit> p0) {
    }
    
    @java.lang.Override()
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    }
    
    private final kotlin.Pair<java.lang.Float, java.lang.Float> dotCenter(int i) {
        return null;
    }
    
    @java.lang.Override()
    protected void onDraw(@org.jetbrains.annotations.NotNull()
    android.graphics.Canvas canvas) {
    }
    
    @java.lang.Override()
    public boolean onTouchEvent(@org.jetbrains.annotations.NotNull()
    android.view.MotionEvent event) {
        return false;
    }
    
    private final void maybeAddNode(float x, float y) {
    }
    
    /**
     * Clear the current stroke (e.g. after an incorrect attempt).
     */
    public final void reset() {
    }
    
    /**
     * Flash the grid red to flag a mismatch without clearing the dots.
     */
    public final void showError() {
    }
    
    @java.lang.Override()
    public boolean performClick() {
        return false;
    }
    
    private final float toDp(float $this$toDp) {
        return 0.0F;
    }
}