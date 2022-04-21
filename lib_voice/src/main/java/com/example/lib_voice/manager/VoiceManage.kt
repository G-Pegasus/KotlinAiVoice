package com.example.lib_voice.manager

import android.content.Context
import android.util.Log
import com.baidu.speech.EventListener
import com.baidu.speech.asr.SpeechConstant
import com.example.lib_voice.asr.VoiceAsr
import com.example.lib_voice.impl.OnAsrResultListener
import com.example.lib_voice.tts.VoiceTTS
import com.example.lib_voice.wakeup.VoiceWakeUp
import com.example.lib_voice.words.WordsTools
import org.json.JSONObject

// 语音管理类
object VoiceManager: EventListener {

    private var TAG = VoiceManager::class.java.simpleName

    const val VOICE_API_ID = "25986155"
    const val VOICE_API_KEY = "cdDmbzw53gxdb1oQEScZyvBy"
    const val VOICE_SECRET_KEY = "G7MiXG713MIbx0YLrX5H51HUZnWR0aNo"

    // 接口
    private lateinit var mOnAsrResultListener: OnAsrResultListener

    fun initManager(mContext: Context, mOnAsrResultListener: OnAsrResultListener) {
        this.mOnAsrResultListener = mOnAsrResultListener

        VoiceTTS.initTTS(mContext)
        VoiceWakeUp.initWakeUp(mContext, this)
        VoiceAsr.initAsr(mContext, this)
    }

    // TTS Start

    fun start(text: String) {
        VoiceTTS.start(text, null)
    }

    // 播放
    fun start(text: String, mOnTTSResultListener: VoiceTTS.OnTTSResultListener?) {
        VoiceTTS.start(text, mOnTTSResultListener)
    }

    // 暂停
    fun pause() {
        VoiceTTS.pause()
    }

    // 继续
    fun resume() {
        VoiceTTS.resume()
    }

    // 停止
    fun stop() {
        VoiceTTS.stop()
    }

    // 释放
    fun release() {
        VoiceTTS.release()
    }

    // 设置发音人
    fun setPeople(people: String) {
        VoiceTTS.setPeople(people)
    }

    // 设置语速
    fun setVoiceSpeed(speed: String) {
        VoiceTTS.setVoiceSpeed(speed)
    }

    // 设置音量
    fun setVoiceVolume(volume: String) {
        VoiceTTS.setVoiceVolume(volume)
    }

    // TTS End

    // WakeUp Start

    // 启动唤醒
    fun startWakeUp() {
        VoiceWakeUp.startWakeUp()
    }
    // 停止唤醒
    fun stopWakeUp() {
        VoiceWakeUp.stopWakeUp()
    }

    // WakeUp End

    // Asr Start

    fun startAsr() {
        VoiceAsr.startAsr()
    }

    fun stopAsr() {
        VoiceAsr.stopAsr()
    }

    fun cancelAsr() {
        VoiceAsr.cancelAsr()
    }

    fun releaseAsr() {
        VoiceAsr.releaseAsr(this)
    }

    // Asr End

    override fun onEvent(name: String?, params: String?, byte: ByteArray?, offset: Int, length: Int) {
        Log.d(TAG, String.format("event: name=%s, params=%s", name, params))
        when(name) {
            SpeechConstant.CALLBACK_EVENT_WAKEUP_READY -> mOnAsrResultListener.wakeUpReady()
            SpeechConstant.CALLBACK_EVENT_ASR_BEGIN -> mOnAsrResultListener.asrStartSpeak()
            SpeechConstant.CALLBACK_EVENT_ASR_END -> mOnAsrResultListener.asrStopSpeak()
        }

        // 去除脏数据
        if (params == null) return

        val allJson = JSONObject(params)

        when(name) {
            SpeechConstant.CALLBACK_EVENT_WAKEUP_SUCCESS -> mOnAsrResultListener.wakeUpSuccess(allJson)
            SpeechConstant.CALLBACK_EVENT_WAKEUP_ERROR -> mOnAsrResultListener.voiceError("唤醒失败")
            SpeechConstant.CALLBACK_EVENT_ASR_FINISH -> mOnAsrResultListener.asrResult(allJson)
            SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL -> {
                byte?.let {
                    val nlu = JSONObject(String(byte, offset, length))
                    mOnAsrResultListener.nluResult(nlu)
                }
            }
        }
    }
}