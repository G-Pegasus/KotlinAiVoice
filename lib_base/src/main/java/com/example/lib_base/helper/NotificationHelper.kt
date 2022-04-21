package com.example.lib_base.helper

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat

// 通知栏帮助类
@SuppressLint("StaticFieldLeak")
object NotificationHelper {

    private lateinit var mContext: Context
    private lateinit var nm: NotificationManager
    private const val CHANNEL_ID = "ai_voice_service"
    private const val CHANNEL_NAME = "语音服务"

    fun initHelper(mContext: Context) {
        this.mContext = mContext

        nm = mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 创建渠道
        setBindVoiceChannel()
    }

    // 创建绑定服务的渠道
    private fun setBindVoiceChannel() {
        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)

        // 呼吸灯
        channel.enableLights(false)
        // 震动
        channel.enableVibration(false)
        // 角标
        channel.setShowBadge(false)
        nm.createNotificationChannel(channel)
    }

    // 绑定语音服务
    fun bindVoiceService(contentText: String): Notification {
        val notificationCompat = NotificationCompat.Builder(mContext, CHANNEL_ID)

        // 设置标题
        notificationCompat.setContentTitle(CHANNEL_NAME)
        // 设置描述
        notificationCompat.setContentText(contentText)
        // 设置时间
        notificationCompat.setWhen(System.currentTimeMillis())
        // 禁止滑动
        notificationCompat.setAutoCancel(false)
        return notificationCompat.build()
    }
}