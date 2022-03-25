package com.example.richtextview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.shuyu.textutillib.RichTextBuilder
import com.shuyu.textutillib.listener.SpanAtUserCallBack
import com.shuyu.textutillib.listener.SpanTopicCallBack
import com.shuyu.textutillib.listener.SpanUrlCallBack
import com.shuyu.textutillib.model.TopicModel
import com.shuyu.textutillib.model.UserModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val topicModels = ArrayList<TopicModel>()

    private val nameList = ArrayList<UserModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resolveRichShow()
    }

    private fun resolveRichShow() {
        nameList.clear()
        topicModels.clear()

        val userModel = UserModel("22222", "2222")
        nameList.add(userModel)
        val userModel2 = UserModel("kkk", "23333")
        nameList.add(userModel2)

        val topicModel = TopicModel("话题话题", "333")
        topicModels.add(topicModel)

        val topicModel2 = TopicModel("话题话题2", "333")
        topicModels.add(topicModel2)

        val content = "这是测试 话题话题 文本哟 www.baidu.com " +
                "\n来@某个人  @22222 @kkk  话题话题2 " +
                "\n好的,来几个表情，最后来一个电话 13245685478sdasdfasdfasdfasdfasdfasdfasdf"

        val spanUrlCallBack = object : SpanUrlCallBack {
            override fun phone(view: View, phone: String) {
                Toast.makeText(view.context, phone + " 被点击了", Toast.LENGTH_SHORT).show()
                if (view is TextView) {
                    view.highlightColor = Color.TRANSPARENT
                }
            }

            override fun url(view: View, url: String) {
                Toast.makeText(view.context, url + " 被点击了", Toast.LENGTH_SHORT).show()
                if (view is TextView) {
                    view.highlightColor = Color.TRANSPARENT
                }
            }
        }

        val spanAtUserCallBack = object : SpanAtUserCallBack {
            override fun onClick(view: View, userModel1: UserModel) {
                Toast.makeText(view.context, userModel1.user_name + " 被点击了", Toast.LENGTH_SHORT).show()
                if (view is TextView) {
                    view.highlightColor = Color.TRANSPARENT
                }
            }
        }

        val spanTopicCallBack = object : SpanTopicCallBack {
            override fun onClick(view: View, topicModel: TopicModel) {
                Toast.makeText(view.context, topicModel.topicName + " 被点击了", Toast.LENGTH_SHORT).show()
                if (view is TextView) {
                    view.highlightColor = Color.TRANSPARENT
                }
            }
        }

        //直接使用RichTextView
        richText.atColor = Color.RED
        richText.topicColor = Color.BLUE
        richText.linkColor = Color.YELLOW
        richText.isNeedNumberShow = true
        richText.isNeedUrlShow = true
        richText.setSpanAtUserCallBackListener(spanAtUserCallBack)
        richText.setSpanTopicCallBackListener(spanTopicCallBack)
        richText.setSpanUrlCallBackListener(spanUrlCallBack)
        //所有配置完成后才设置text
        richText.setRichText(content, nameList, topicModels)

        val richTextBuilder = RichTextBuilder(this)
        richTextBuilder.setContent(content)
            .setAtColor(Color.RED)
            .setLinkColor(Color.BLUE)
            .setTopicColor(Color.YELLOW)
            .setListUser(nameList)
            .setListTopic(topicModels)
            .setTextView(richText2)
            .setNeedUrl(true)
            .setNeedNum(true)
            //.setVerticalAlignment(CenteredImageSpan.ALIGN_CENTER)
            .setSpanAtUserCallBack(spanAtUserCallBack)
            .setSpanUrlCallBack(spanUrlCallBack)
            .setSpanTopicCallBack(spanTopicCallBack)
            .build()
        richText2.movementMethod = CustomMovementMethod().getInstance()
    }

}