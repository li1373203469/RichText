package com.example.richtextview

import android.text.Layout
import android.text.Spannable
import android.text.method.BaseMovementMethod
import android.text.style.ClickableSpan
import android.view.MotionEvent
import android.widget.TextView

/**
 * @author: lzw
 * @date: 2022/3/25 14:54
 * Description:  替换LinkMovementMethod，这个不会触发TextView的滑动事件
 */
class CustomMovementMethod : BaseMovementMethod() {
    private var customMovementMethod: CustomMovementMethod? = null

    fun getInstance(): CustomMovementMethod? {
        if (customMovementMethod == null) {
            synchronized(CustomMovementMethod::class.java) {
                if (customMovementMethod == null) {
                    customMovementMethod = CustomMovementMethod()
                }
            }
        }
        return customMovementMethod
    }

    override fun onTouchEvent(widget: TextView, buffer: Spannable, event: MotionEvent): Boolean {
        val action: Int = event.action
        if (action == MotionEvent.ACTION_UP ||
            action == MotionEvent.ACTION_DOWN
        ) {
            var x = event.x
            var y = event.y
            x -= widget.totalPaddingLeft
            y -= widget.totalPaddingTop
            x += widget.scrollX
            y += widget.scrollY
            val layout: Layout = widget.layout
            val line: Int = layout.getLineForVertical(y.toInt())
            val off: Int = layout.getOffsetForHorizontal(line, x)
            val link: Array<ClickableSpan> = buffer.getSpans(off, off, ClickableSpan::class.java)
            if (link.isNotEmpty()) {
                if (action == MotionEvent.ACTION_UP) {
                    //除了点击事件，我们不要其他东西
                    link[0].onClick(widget)
                }
                return true
            }
        }
        return true
    }

}