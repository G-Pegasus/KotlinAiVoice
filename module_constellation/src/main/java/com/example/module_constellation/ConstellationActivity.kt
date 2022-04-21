package com.example.module_constellation

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.lib_base.base.BaseActivity
import com.example.lib_base.helper.ARouterHelper
import com.example.module_constellation.databinding.ActivityConstellationBinding

@Route(path = ARouterHelper.PATH_CONSTELLATION)
class ConstellationActivity: BaseActivity() {
    private lateinit var binding: ActivityConstellationBinding

    override fun getLayoutId(): Int {
        return R.layout.activity_constellation
    }

    override fun getTitleText(): String {
        return getString(com.example.lib_base.R.string.app_title_constellation)
    }

    override fun isShowBack(): Boolean {
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConstellationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.let {
            it.title = getTitleText()
            it.setDisplayHomeAsUpEnabled(isShowBack())
            it.elevation = 0f
        }
    }
}