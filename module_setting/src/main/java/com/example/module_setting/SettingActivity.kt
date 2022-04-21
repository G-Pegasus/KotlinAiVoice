package com.example.module_setting

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.lib_base.base.BaseActivity
import com.example.lib_base.helper.ARouterHelper
import com.example.module_setting.databinding.ActivitySettingBinding

@Route(path = ARouterHelper.PATH_SETTING)
class SettingActivity: BaseActivity() {
    private lateinit var binding: ActivitySettingBinding

    override fun getLayoutId(): Int {
        return R.layout.activity_setting
    }

    override fun getTitleText(): String {
        return getString(com.example.lib_base.R.string.app_title_system_setting)
    }

    override fun isShowBack(): Boolean {
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.let {
            it.title = getTitleText()
            it.setDisplayHomeAsUpEnabled(isShowBack())
            it.elevation = 0f
        }
    }
}