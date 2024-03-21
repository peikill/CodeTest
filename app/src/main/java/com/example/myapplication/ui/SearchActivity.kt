package com.example.myapplication.ui

import android.content.Intent
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import com.blankj.utilcode.util.KeyboardUtils
import com.example.myapplication.databinding.ActivitySearchBinding
import com.example.myapplication.tool.PARAM_PARAM


class SearchActivity : BaseActivity<ActivitySearchBinding>(){

    override fun initData() {
    }

    override fun initObserver() {
    }

    override fun initView() {
        mBinding.etSearch.requestFocus()
        KeyboardUtils.showSoftInput(this)
        mBinding.tvCalcel.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        mBinding.etSearch.setOnEditorActionListener(OnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                val intent = Intent()
                intent.putExtra(PARAM_PARAM, mBinding.etSearch.text.toString())
                setResult(RESULT_OK, intent)
                finish()
                true
            } else false
        })

    }

    override fun getViewBinding() = ActivitySearchBinding.inflate(layoutInflater)

}


