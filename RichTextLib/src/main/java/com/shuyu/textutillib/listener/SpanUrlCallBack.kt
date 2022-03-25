package com.shuyu.textutillib.listener

import android.view.View

/**
 * url被点击的回掉
 */

open interface SpanUrlCallBack {
    fun phone(view: View, phone: String)

    fun url(view: View, url: String)
}
