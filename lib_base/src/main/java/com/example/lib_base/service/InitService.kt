package com.example.lib_base.service

import android.app.IntentService
import android.content.Intent
import com.example.lib_base.util.L
import com.example.lib_base.util.SpUtils
import com.example.lib_voice.words.WordsTools

// 初始化服务
class InitService: IntentService(InitService::class.simpleName) {

    override fun onCreate() {
        super.onCreate()
        L.i("初始化开始")
    }

    override fun onHandleIntent(intent: Intent?) {
        L.i("执行初始化操作")

        SpUtils.initUtils(this)
        WordsTools.initTools(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        L.i("初始化完成")
    }
}