package com.example.kotlintest.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.lib_base.helper.NotificationHelper
import com.example.lib_base.util.L
import com.example.lib_voice.engine.VoiceEngineAnalyze
import com.example.lib_voice.impl.OnAsrResultListener
import com.example.lib_voice.impl.OnNluResultListener
import com.example.lib_voice.manager.VoiceManager
import com.example.lib_voice.tts.VoiceTTS
import com.example.lib_voice.words.WordsTools
import org.json.JSONObject

class VoiceService: Service(), OnNluResultListener {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        L.i("语音服务启动")
        initCoreVoiceService()
    }

    /**
     * START_STICKY: 当系统内存不足的时候，杀掉了服务，当系统内存不在紧张时，启动服务
     * START_NOT_STICKY: 当系统内存不足的时候，杀掉了服务，直到下一次startService才启动
     * START_REDELIVER: 重新传递Intent值
     * START_STICKY_COMPATIBILITY:START_STICKY兼容版本，但是它也不能保证系统kill掉服务一定能重启
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        bindNotification()
        return START_STICKY_COMPATIBILITY
    }

    // 绑定通知栏
    private fun bindNotification() {
        startForeground(1000, NotificationHelper.bindVoiceService("正在运行"))
    }

    // 初始化语音服务
    private fun initCoreVoiceService() {
        VoiceManager.initManager(this, object :OnAsrResultListener{
            // 准备就绪
            override fun wakeUpReady() {
                L.i("唤醒准备就绪")
                VoiceManager.start("小龙准备就绪啦")
            }

            override fun asrStartSpeak() {
                L.i("开始说话")
            }

            override fun asrStopSpeak() {
                L.i("结束说话")
            }

            override fun wakeUpSuccess(result: JSONObject) {
                L.i("唤醒成功：$result")
                val errorCode = result.optInt("errorCode")
                // 唤醒成功
                if (errorCode == 0) {
                    // 唤醒词
                    val word = result.optString("word")
                    if (word == "小龙小龙") {
                        // 应答
                        VoiceManager.start(WordsTools.wakeupWords(), object :VoiceTTS.OnTTSResultListener {
                            override fun ttsEnd() {
                                // 开启识别
                                VoiceManager.startAsr()
                            }

                        })
                    }
                }
            }

            override fun asrResult(result: JSONObject) {
                L.i("===================RESULT===================")
                L.i("nlu：$result")
            }

            override fun nluResult(nlu: JSONObject) {
                L.i("===================NLU===================")
                L.i("nlu：$nlu")

                VoiceEngineAnalyze.analyzeNlu(nlu, this@VoiceService)
            }

            override fun voiceError(text: String) {
                L.i("发生错误：$text")
            }

        })
    }

    // 查询天气
    override fun queryWeather() {

    }
}