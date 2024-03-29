package com.example.lib_voice.tts

import android.content.Context
import android.util.Log
import com.baidu.tts.client.SpeechError
import com.baidu.tts.client.SpeechSynthesizer
import com.baidu.tts.client.SpeechSynthesizerListener
import com.baidu.tts.client.TtsMode
import com.example.lib_voice.manager.VoiceManager

/**
 * 百度AI语音 - TTS封装
 * 1.实现其他参数
 * 2.实现监听播放结束
 */
object VoiceTTS : SpeechSynthesizerListener {

    private var TAG = VoiceTTS::class.java.simpleName

    // TTS对象
    private lateinit var mSpeechSynthesizer: SpeechSynthesizer
    private var mOnTTSResultListener: OnTTSResultListener? = null

    // 初始化TTS
    fun initTTS(mContext: Context) {
        // 初始化对象
        mSpeechSynthesizer = SpeechSynthesizer.getInstance()
        // 初始化TTS
        mSpeechSynthesizer.setContext(mContext)
        // 设置Key
        mSpeechSynthesizer.setAppId(VoiceManager.VOICE_API_ID)
        mSpeechSynthesizer.setApiKey(VoiceManager.VOICE_API_KEY, VoiceManager.VOICE_SECRET_KEY)
        // 设置监听
        mSpeechSynthesizer.setSpeechSynthesizerListener(this)

        // 发声人
        setPeople("0")
        // 语速
        setVoiceSpeed("5")
        // 音量
        setVoiceVolume("5")
        // 初始化
        mSpeechSynthesizer.initTts(TtsMode.ONLINE)
        Log.i(TAG, "TTS init")
    }

    // 设置发音人
    fun setPeople(people: String) {
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, people)
    }

    // 设置语速
    fun setVoiceSpeed(speed: String) {
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEED, speed)
    }

    // 设置音量
    fun setVoiceVolume(volume: String) {
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_VOLUME, volume)
    }

    override fun onSynthesizeStart(p0: String?) {
        Log.i(TAG, "合成开始")
    }

    override fun onSynthesizeDataArrived(p0: String?, p1: ByteArray?, p2: Int, p3: Int) {

    }

    override fun onSynthesizeFinish(p0: String?) {
        Log.i(TAG, "合成结束")
    }

    override fun onSpeechStart(p0: String?) {
        Log.i(TAG, "播放开始")
    }

    override fun onSpeechProgressChanged(p0: String?, p1: Int) {

    }

    override fun onSpeechFinish(p0: String?) {
        Log.i(TAG, "播放结束")
        mOnTTSResultListener?.ttsEnd()
    }

    override fun onError(string: String?, error: SpeechError?) {
        Log.i(TAG, "TTS错误: $string: $error")
    }

    // 播放并且有回调
    fun start(text: String, mOnTTSResultListener: OnTTSResultListener?) {
        VoiceTTS.mOnTTSResultListener = mOnTTSResultListener
        mSpeechSynthesizer.speak(text)
    }

    // 暂停
    fun pause() {
        mSpeechSynthesizer.pause()
    }

    // 继续
    fun resume() {
        mSpeechSynthesizer.resume()
    }

    // 停止
    fun stop() {
        mSpeechSynthesizer.stop()
    }

    // 释放
    fun release() {
        mSpeechSynthesizer.release()
    }

    // 接口
    interface OnTTSResultListener {
        fun ttsEnd()
    }
}