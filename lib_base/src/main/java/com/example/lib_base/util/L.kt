package com.example.lib_base.util

import android.util.Log
import com.example.lib_base.BuildConfig

// Log 日志
object L {
    private const val TAG: String = "AiVoiceApp"

    fun i(text: String?) {
        if (BuildConfig.DEBUG) {
            text?.let {
                Log.i(TAG, it)
            }
        }
    }

    fun e(text: String?) {
        if (BuildConfig.DEBUG) {
            text?.let {
                Log.e(TAG, it)
            }
        }
    }
}