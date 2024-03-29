package com.example.lib_network.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class HttpInterceptor: Interceptor {

    private val tag = "HTTP"

    override fun intercept(chain: Interceptor.Chain): Response {
        // 请求参数
        val request = chain.request()
        val response = chain.proceed(request)

        Log.i(tag, "==================REQUEST===================")
        if (request.method() == "GET") {
            Log.i(tag, request.url().toString())
        }

        Log.i(tag, "==================RESPONSE===================")
        response.body()?.let {
            Log.i(tag, it.string())
        }

        return response
    }
}