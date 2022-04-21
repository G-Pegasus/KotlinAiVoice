package com.example.module_joke

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.lib_base.base.BaseActivity
import com.example.lib_base.helper.ARouterHelper
import com.example.module_joke.databinding.ActivityJokeBinding

@Route(path = ARouterHelper.PATH_JOKE)
class JokeActivity: BaseActivity() {
    private lateinit var binding: ActivityJokeBinding

    override fun getLayoutId(): Int {
        return R.layout.activity_joke
    }

    override fun getTitleText(): String {
        return getString(com.example.lib_base.R.string.app_title_joke)
    }

    override fun isShowBack(): Boolean {
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.let {
            it.title = getTitleText()
            it.setDisplayHomeAsUpEnabled(isShowBack())
            it.elevation = 0f
        }
    }
}