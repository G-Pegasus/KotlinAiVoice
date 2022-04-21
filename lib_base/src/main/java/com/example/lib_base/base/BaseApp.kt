package com.example.lib_base.base

import android.app.Application
import android.content.Intent
import com.example.lib_base.helper.ARouterHelper
import com.example.lib_base.helper.NotificationHelper
import com.example.lib_base.service.InitService
import com.example.lib_base.util.SpUtils
import com.example.lib_voice.manager.VoiceManager

open class BaseApp: Application() {

    override fun onCreate() {
        super.onCreate()

        ARouterHelper.initHelper(this)
        NotificationHelper.initHelper(this)
        startService(Intent(this, InitService::class.java))
    }
}