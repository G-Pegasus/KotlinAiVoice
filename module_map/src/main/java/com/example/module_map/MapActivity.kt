package com.example.module_map

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.lib_base.base.BaseActivity
import com.example.lib_base.helper.ARouterHelper
import com.example.module_map.databinding.ActivityMapBinding

@Route(path = ARouterHelper.PATH_MAP)
class MapActivity: BaseActivity() {
    private lateinit var binding: ActivityMapBinding

    override fun getLayoutId(): Int {
        return R.layout.activity_map
    }

    override fun getTitleText(): String {
        return getString(com.example.lib_base.R.string.app_title_map)
    }

    override fun isShowBack(): Boolean {
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.let {
            it.title = getTitleText()
            it.setDisplayHomeAsUpEnabled(isShowBack())
            it.elevation = 0f
        }
    }
}