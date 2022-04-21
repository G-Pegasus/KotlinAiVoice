package com.example.lib_base.helper

import android.content.Context
import android.view.Gravity
import android.view.WindowManager

class WindowHelper {
    private lateinit var mContext: Context
    private lateinit var wm: WindowManager

    fun initHelper(mContext: Context) {
        this.mContext = mContext

        wm = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }

    // 创建布局属性
    fun creatLayoutParams(width: Int, height: Int) {
        val lp = WindowManager.LayoutParams()
        lp.apply {
            this.width = width
            this.height = height
            gravity = Gravity.CENTER
        }
    }
}