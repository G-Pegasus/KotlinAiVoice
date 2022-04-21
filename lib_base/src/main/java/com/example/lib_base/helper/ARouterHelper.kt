package com.example.lib_base.helper

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.example.lib_base.BuildConfig

// 路由帮助类
object ARouterHelper {

    // Module First Run Path
    const val PATH_APP_MANAGER = "/app_manager/app_manager_activity"
    const val PATH_CONSTELLATION = "/constellation/constellation_activity"
    const val PATH_DEVELOPR = "/developr/developr_activity"
    const val PATH_JOKE = "/joke/joke_activity"
    const val PATH_MAP = "/map/map_activity"
    const val PATH_SETTING = "/setting/setting_activity"
    const val PATH_VOICE_SETTING = "/voice/voice_setting_activity"
    const val PATH_WEATHER = "/weather/weather_activity"

    fun initHelper(application: Application) {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(application)
    }

    // 跳转页面
    fun startActivity(path: String) {
        ARouter.getInstance().build(path).navigation()
    }

    // 跳转页面
    fun startActivity(activity: Activity, path: String, requestCode: Int) {
        ARouter.getInstance().build(path).navigation(activity, requestCode)
    }

    // 跳转页面 String
    fun startActivity(path: String, key: String, value: String) {
        ARouter.getInstance().build("/test/1")
            .withString(key, value)
            .navigation()
    }

    // 跳转页面 Int
    fun startActivity(path: String, key: String, value: Int) {
        ARouter.getInstance().build("/test/1")
            .withInt(key, value)
            .navigation()
    }

    // 跳转页面 Boolean
    fun startActivity(path: String, key: String, value: Boolean) {
        ARouter.getInstance().build("/test/1")
            .withBoolean(key, value)
            .navigation()
    }

    // 跳转页面 Bundle
    fun startActivity(path: String, key: String, bundle: Bundle) {
        ARouter.getInstance().build("/test/1")
            .withBundle(key, bundle)
            .navigation()
    }

    // 跳转页面 Object
    fun startActivity(path: String, key: String, any: Any) {
        ARouter.getInstance().build("/test/1")
            .withObject(key, any)
            .navigation()
    }
}