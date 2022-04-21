package com.example.lib_voice.words

import android.content.Context
import com.example.lib_voice.R
import kotlin.random.Random

object WordsTools {

    // 唤醒词条
    private lateinit var wakeupArray: Array<String>

    // 无法应答
    private lateinit var noAnswerArray: Array<String>

    // 暂不支持功能
    private lateinit var noSupportArray: Array<String>

    // 初始化工具
    fun initTools(mContext: Context) {
        mContext.apply {
            wakeupArray = resources.getStringArray(R.array.WakeUpListArray)
            noAnswerArray = resources.getStringArray(R.array.NoAnswerArray)
            noSupportArray = resources.getStringArray(R.array.NoSupportArray)
        }
    }

    fun wakeupWords(): String {
        return randomArray(wakeupArray)
    }

    fun noAnswerWords(): String {
        return randomArray(noAnswerArray)
    }

    fun noSupportWords(): String {
        return randomArray(noSupportArray)
    }

    private fun randomArray(array: Array<String>): String {
        return array[Random.nextInt(array.size)]
    }
}