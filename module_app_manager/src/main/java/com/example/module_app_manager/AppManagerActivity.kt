package com.example.module_app_manager

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.lib_base.base.BaseActivity
import com.example.lib_base.helper.ARouterHelper
import com.example.module_app_manager.databinding.ActivityAppManagerBinding

@Route(path = ARouterHelper.PATH_APP_MANAGER)
class AppManagerActivity: BaseActivity() {
    private lateinit var binding: ActivityAppManagerBinding

    override fun getLayoutId(): Int {
        return R.layout.activity_app_manager
    }

    override fun getTitleText(): String {
        return getString(com.example.lib_base.R.string.app_title_app_manager)
    }

    override fun isShowBack(): Boolean {
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.let {
            it.title = getTitleText()
            it.setDisplayHomeAsUpEnabled(isShowBack())
            it.elevation = 0f
        }
    }
}