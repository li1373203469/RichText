package com.shuyu.textutillib.listener

import android.content.Context

import com.shuyu.textutillib.model.TopicModel
import com.shuyu.textutillib.model.UserModel
import com.shuyu.textutillib.span.ClickAtUserSpan
import com.shuyu.textutillib.span.ClickTopicSpan
import com.shuyu.textutillib.span.LinkSpan

/**
 * 自定义点击接口
 */

open interface SpanCreateListener {

    fun getCustomClickAtUserSpan(context: Context, userModel: UserModel, color: Int, spanClickCallBack: SpanAtUserCallBack): ClickAtUserSpan

    fun getCustomClickTopicSpan(context: Context, topicModel: TopicModel, color: Int, spanTopicCallBack: SpanTopicCallBack): ClickTopicSpan

    fun getCustomLinkSpan(context: Context, url: String, color: Int, spanUrlCallBack: SpanUrlCallBack): LinkSpan
}
