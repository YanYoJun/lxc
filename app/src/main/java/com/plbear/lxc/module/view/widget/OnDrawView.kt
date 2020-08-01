package com.plbear.lxc.module.view.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Context context, @Nullable AttributeSet attrs, int defStyleAttr
 * created by yanyongjun on 2020/7/4
 */
class OnDrawView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint()
    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) {
            return
        }
        canvas.drawCircle(300f, 300f, 200f, paint)
        super.onDraw(canvas)
    }
}