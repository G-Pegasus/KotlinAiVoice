package com.example.module_voice_setting

import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.lib_base.base.BaseActivity
import com.example.lib_base.base.adapter.CommonAdapter
import com.example.lib_base.base.adapter.CommonViewHolder
import com.example.lib_base.helper.ARouterHelper
import com.example.lib_voice.manager.VoiceManager
import com.example.module_voice_setting.databinding.ActivityVoiceSettingBinding

@Route(path = ARouterHelper.PATH_VOICE_SETTING)
class VoiceSettingActivity: BaseActivity() {
    private lateinit var binding: ActivityVoiceSettingBinding
    private val mList: ArrayList<String> = ArrayList()
    private var mTtsPeopleIndex: Array<String>? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_voice_setting
    }

    override fun getTitleText(): String {
        return getString(com.example.lib_base.R.string.app_title_voice_setting)
    }

    override fun isShowBack(): Boolean {
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVoiceSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.let {
            it.title = getTitleText()
            it.setDisplayHomeAsUpEnabled(isShowBack())
            it.elevation = 0f
        }

        // 默认值
        binding.barVoiceSpeed.progress = 5
        binding.barVoiceVolume.progress = 5

        // 设置最大值
        binding.barVoiceSpeed.max = 15
        binding.barVoiceVolume.max = 15

        initData()
        initListener()
        initPeopleView()

        binding.btnTest.setOnClickListener {
            VoiceManager.start("大家好，我承认龙明毅是卷王")
        }
    }

    // 初始化数据
    private fun initData() {
        val mTtsPeople = resources.getStringArray(R.array.TTSPeople)

        mTtsPeopleIndex = resources.getStringArray(R.array.TTSPeopleIndex)

        mTtsPeople.forEach {
            mList.add(it)
        }
    }

    // 初始化发音人列表
    private fun initPeopleView() {
        binding.rvVoicePeople.layoutManager = LinearLayoutManager(this)
        binding.rvVoicePeople.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding.rvVoicePeople.adapter =
            CommonAdapter(mList, object :CommonAdapter.OnBindDataListener<String>{
            override fun onBindViewHolder(
                model: String,
                viewHolder: CommonViewHolder,
                type: Int,
                position: Int
            ) {
                viewHolder.setText(R.id.mTvPeopleContent, model)
                viewHolder.itemView.setOnClickListener {
                    mTtsPeopleIndex?.let {
                        VoiceManager.setPeople(it[position])
                    }
                    Toast.makeText(this@VoiceSettingActivity, "设置成功", Toast.LENGTH_SHORT).show()
                }
            }

            override fun getLayoutId(type: Int): Int {
                return R.layout.layout_tts_people_list
            }

        })
    }

    // 初始化监听
    private fun initListener() {
        binding.barVoiceSpeed.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.barVoiceSpeed.progress = progress
                VoiceManager.setVoiceSpeed("$progress")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        binding.barVoiceVolume.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.barVoiceVolume.progress = progress
                VoiceManager.setVoiceVolume("$progress")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }
}