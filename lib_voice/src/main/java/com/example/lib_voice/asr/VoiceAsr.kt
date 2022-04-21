package com.example.lib_voice.asr

import android.content.Context
import com.baidu.speech.EventListener
import com.baidu.speech.EventManager
import com.baidu.speech.EventManagerFactory
import com.baidu.speech.asr.SpeechConstant
import org.json.JSONObject
import java.util.*

object VoiceAsr {

    private lateinit var asr: EventManager
    private lateinit var asrJson: String

    fun initAsr(mContext: Context, listener: EventListener) {
        val map = HashMap<Any, Any>()
        map[SpeechConstant.ACCEPT_AUDIO_VOLUME] = true
        map[SpeechConstant.ACCEPT_AUDIO_DATA] = false
        map[SpeechConstant.DISABLE_PUNCTUATION] = false
        map[SpeechConstant.PID] = 1736 // 15373

        // 转换成JSON
        asrJson = JSONObject(map as Map<Any,Any>).toString()

        asr = EventManagerFactory.create(mContext, "asr")
        asr.registerListener(listener)
    }

    fun startAsr() {
        asr.send(SpeechConstant.ASR_START, asrJson, null, 0, 0)
    }

    fun stopAsr() {
        asr.send(SpeechConstant.ASR_STOP, null, null, 0, 0)
    }

    fun cancelAsr() {
        asr.send(SpeechConstant.ASR_CANCEL, null, null, 0, 0)
    }

    fun releaseAsr(listener: EventListener) {
        asr.unregisterListener(listener)
    }
}