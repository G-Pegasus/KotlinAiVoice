package com.example.module_developr

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.lib_base.base.BaseActivity
import com.example.lib_base.base.adapter.CommonAdapter
import com.example.lib_base.base.adapter.CommonViewHolder
import com.example.lib_base.helper.ARouterHelper
import com.example.lib_voice.manager.VoiceManager
import com.example.module_developr.data.DeveloperListData
import com.example.module_developr.databinding.ActivityDeveloperBinding

@Route(path = ARouterHelper.PATH_DEVELOPR)
class DeveloperActivity: BaseActivity() {
    private lateinit var binding: ActivityDeveloperBinding

    // 标题
    private val mTypeTitle = 0
    // 内容
    private val mTypeContent = 1

    private val mList = ArrayList<DeveloperListData>()

    override fun getLayoutId(): Int {
        return R.layout.activity_developer
    }

    override fun getTitleText(): String {
        return getString(com.example.lib_base.R.string.app_title_developer)
    }

    override fun isShowBack(): Boolean {
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeveloperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.let {
            it.title = getTitleText()
            it.setDisplayHomeAsUpEnabled(isShowBack())
            it.elevation = 0f
        }

        initData()
        initListView()
    }

    // 初始化数据
    private fun initData() {
        val dataArray: Array<String> = resources.getStringArray(com.example.lib_base.R.array.DeveloperListArray)
        dataArray.forEach {
            if (it.contains("[")) {
                addItemData(mTypeTitle, it.replace("[", "").replace("]", ""))
            } else {
                addItemData(mTypeContent, it)
            }
        }
    }

    // 初始化列表
    private fun initListView() {
        // 布局管理器
        binding.rvDeveloperView.layoutManager = LinearLayoutManager(this)
        // 分割线
        binding.rvDeveloperView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        // 适配器
        binding.rvDeveloperView.adapter = CommonAdapter(mList, object : CommonAdapter.OnMoreBindDataListener<DeveloperListData> {
            override fun onBindViewHolder(
                model: DeveloperListData,
                viewHolder: CommonViewHolder,
                type: Int,
                position: Int
            ) {
                when(model.type) {
                    mTypeTitle -> {
                        viewHolder.setText(R.id.mTvDeveloperTitle, model.text)
                    }

                    mTypeContent -> {
                        viewHolder.setText(R.id.mTvDeveloperContent, "${position}.${model.text}")
                        viewHolder.itemView.setOnClickListener {
                            itemClickFun(position)
                        }
                    }
                }
            }

            override fun getLayoutId(type: Int): Int {
                return if (type == mTypeTitle) {
                    R.layout.layout_developer_title
                } else {
                    R.layout.layout_developer_content
                }
            }

            override fun getItemType(position: Int): Int {
                return mList[position].type
            }
        })
    }

    // 添加数据
    private fun addItemData(type: Int, text: String) {
        mList.add(DeveloperListData(type, text))
    }

    // 点击事件
    private fun itemClickFun(position: Int) {
        when(position) {
            1 -> ARouterHelper.startActivity(ARouterHelper.PATH_APP_MANAGER)
            2 -> ARouterHelper.startActivity(ARouterHelper.PATH_CONSTELLATION)
            3 -> ARouterHelper.startActivity(ARouterHelper.PATH_JOKE)
            4 -> ARouterHelper.startActivity(ARouterHelper.PATH_MAP)
            5 -> ARouterHelper.startActivity(ARouterHelper.PATH_SETTING)
            6 -> ARouterHelper.startActivity(ARouterHelper.PATH_VOICE_SETTING)
            7 -> ARouterHelper.startActivity(ARouterHelper.PATH_WEATHER)

            9 -> VoiceManager.startAsr()
            10 -> VoiceManager.stopAsr()
            11 -> VoiceManager.cancelAsr()
            12 -> VoiceManager.releaseAsr()

            14 -> VoiceManager.startWakeUp()
            15 -> VoiceManager.stopWakeUp()

            20 -> VoiceManager.start("龙明毅和邓先阆是卷王")
            21 -> VoiceManager.pause()
            22 -> VoiceManager.resume()
            23 -> VoiceManager.stop()
            24 -> VoiceManager.release()
        }
    }
}