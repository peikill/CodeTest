package com.example.myapplication.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.example.myapplication.BR
import com.example.myapplication.MainViewModel
import com.example.myapplication.R
import com.example.myapplication.StatusData
import com.example.myapplication.bean.MusicBean
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.tool.PARAM_PARAM
import com.example.myapplication.tool.SEARCH_REQUEST_CODE
import com.example.myapplication.tool.testCode
import com.example.uicore.base.adapter.binding.singeItem.CommonBindVMAdapter


class MainActivity : BaseVMActivity<ActivityMainBinding, MainViewModel>() {

    override fun initData() {
        mViewModel.getMusics()
//      val re = testCode(Pair(1, 9), Pair(78, 90), Pair(7, 10))
    }

    override fun initObserver() {
        mViewModel.sortData.observe(this) {
            mMusicAdapter.setNewInstance(it.toMutableList())
        }

        mViewModel.filterData.observe(this) {
            if (it.isNullOrEmpty()) {
                ToastUtils.showShort("The data is empty")
            }
            mMusicAdapter.setNewInstance(it.toMutableList())
        }

        mViewModel.musicStatus.observe(this) {
            mBinding.retry.visibility = if (it is StatusData.Error)
                View.VISIBLE else View.INVISIBLE
            mBinding.listMusic.visibility = if (it is StatusData.Error)
                View.INVISIBLE else View.VISIBLE

            when (it) {
                is StatusData.Musice -> {
                    mMusicAdapter.setNewInstance(it.music.toMutableList())
                }

                is StatusData.Empty -> {
                    ToastUtils.showShort("The data is empty")
                }

                else -> {
                }
            }
        }
    }

    override fun initView() {
        monitoringRadioGrop()
        initMusicList()
        mBinding.retry.setOnClickListener {
            mViewModel.getMusics()
        }
        mBinding.tvSearch.setOnClickListener {
            startActivityForResult(Intent(this, SearchActivity::class.java), SEARCH_REQUEST_CODE)
        }
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SEARCH_REQUEST_CODE) {
            val input = data?.getStringExtra(PARAM_PARAM)
            mBinding.tvSearch.text = input
            mViewModel.filterMusic(input, mBinding.radioPrice.isChecked)
        }
    }

    private fun monitoringRadioGrop() {
        mBinding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_off -> {
                    mViewModel.filterMusic(mBinding.tvSearch.text.toString(), false)
                }

                R.id.radio_price -> {
                    mViewModel.filterMusic(mBinding.tvSearch.text.toString(), true)
                }
            }
        }
    }

    private val mMusicAdapter by lazy {
        CommonBindVMAdapter<MusicBean>(
            mViewModel, mutableListOf(),
            R.layout.item_music, BR.viewModel,
            BR.bean
        )
    }

    private fun initMusicList() {
        mBinding.listMusic.adapter = mMusicAdapter
        mBinding.listMusic.itemAnimator = null
        val mLayout = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        mBinding.listMusic.layoutManager = mLayout
        mBinding.listMusic.setHasFixedSize(true)
    }
}


