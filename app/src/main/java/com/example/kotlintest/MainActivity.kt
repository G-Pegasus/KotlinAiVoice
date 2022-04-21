package com.example.kotlintest

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import com.ToxicBakery.viewpager.transforms.ScaleInOutTransformer
import com.example.kotlintest.data.MainListData
import com.example.kotlintest.databinding.ActivityMainBinding
import com.example.kotlintest.service.VoiceService
import com.example.lib_base.base.BaseActivity
import com.example.lib_base.base.adapter.BasePagerAdapter
import com.example.lib_base.helper.ARouterHelper
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions

class MainActivity: BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mList = ArrayList<MainListData>()
    private val mListView = ArrayList<View>()

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getTitleText(): String {
        return getString(com.example.lib_base.R.string.app_name)
    }

    override fun isShowBack(): Boolean {
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.let {
            it.title = getTitleText()
            it.setDisplayHomeAsUpEnabled(isShowBack())
            it.elevation = 0f
        }

        // 动态权限
        if (checkPermission(Manifest.permission.RECORD_AUDIO)) {
            linkService()
        } else {
            requestPermission(arrayOf(Manifest.permission.RECORD_AUDIO))
            linkService()
        }

        // 窗口权限
        if (!checkWindowPermission()) {
            requestWindowPermission()
        }

        initPagerData()
        initPagerView()
    }

    private fun initPagerView() {
        binding.mViewPager.pageMargin = 20
        binding.mViewPager.offscreenPageLimit = mList.size
        binding.mViewPager.adapter = BasePagerAdapter(mListView)
        binding.mViewPager.setPageTransformer(true, ScaleInOutTransformer())
    }

    @SuppressLint("Recycle")
    private fun initPagerData() {
        val title = resources.getStringArray(R.array.MainTitleArray)
        val color = resources.getIntArray(R.array.MainColorArray)
        val icon = resources.obtainTypedArray(R.array.MainIconArray)

        for ((index, value) in title.withIndex()) {
            mList.add(MainListData(value, icon.getResourceId(index, 0), color[index]))
        }

        val windowHeight = windowManager.defaultDisplay.height

        mList.forEach {
            val view = View.inflate(this, R.layout.layout_main_list, null)
            val mCvMainView = view.findViewById<CardView>(R.id.mCvMainView)
            val mIvMainIcon = view.findViewById<ImageView>(R.id.mIvMainIcon)
            val mTvMainText = view.findViewById<TextView>(R.id.mTvMainText)

            mCvMainView.setBackgroundColor(it.color)
            mIvMainIcon.setImageResource(it.icon)
            mTvMainText.text = it.title

            mCvMainView.layoutParams?.let { lp ->
                lp.height = windowHeight / 5 * 3
            }

            // 点击事件
            mCvMainView.setOnClickListener { _ ->
                when (it.icon) {
                    R.drawable.img_main_weather -> ARouterHelper.startActivity(ARouterHelper.PATH_WEATHER)
                    R.drawable.img_main_contell -> ARouterHelper.startActivity(ARouterHelper.PATH_CONSTELLATION)
                    R.drawable.img_main_joke_icon -> ARouterHelper.startActivity(ARouterHelper.PATH_JOKE)
                    R.drawable.img_main_map_icon -> ARouterHelper.startActivity(ARouterHelper.PATH_MAP)
                    R.drawable.img_main_app_manager -> ARouterHelper.startActivity(ARouterHelper.PATH_APP_MANAGER)
                    R.drawable.img_main_voice_setting -> ARouterHelper.startActivity(ARouterHelper.PATH_VOICE_SETTING)
                    R.drawable.img_main_system_setting -> ARouterHelper.startActivity(ARouterHelper.PATH_SETTING)
                    R.drawable.img_main_developer -> ARouterHelper.startActivity(ARouterHelper.PATH_DEVELOPR)
                }
            }

            mIvMainIcon.setOnClickListener { _ ->
                when (it.icon) {
                    R.drawable.img_main_weather -> ARouterHelper.startActivity(ARouterHelper.PATH_WEATHER)
                    R.drawable.img_main_contell -> ARouterHelper.startActivity(ARouterHelper.PATH_CONSTELLATION)
                    R.drawable.img_main_joke_icon -> ARouterHelper.startActivity(ARouterHelper.PATH_JOKE)
                    R.drawable.img_main_map_icon -> ARouterHelper.startActivity(ARouterHelper.PATH_MAP)
                    R.drawable.img_main_app_manager -> ARouterHelper.startActivity(ARouterHelper.PATH_APP_MANAGER)
                    R.drawable.img_main_voice_setting -> ARouterHelper.startActivity(ARouterHelper.PATH_VOICE_SETTING)
                    R.drawable.img_main_system_setting -> ARouterHelper.startActivity(ARouterHelper.PATH_SETTING)
                    R.drawable.img_main_developer -> ARouterHelper.startActivity(ARouterHelper.PATH_DEVELOPR)
                }
            }

            mListView.add(view)
        }
    }

    // 连接服务
    private fun linkService() {
        startService(Intent(this, VoiceService::class.java))
    }
}