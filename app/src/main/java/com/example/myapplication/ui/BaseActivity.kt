package com.example.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.gyf.immersionbar.ImmersionBar

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected val mBinding by lazy {
        getViewBinding()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initView()
        initImmersionBar()
        initData()
        initObserver()
    }

    protected abstract fun initData()

    protected abstract fun initObserver()

    protected abstract fun initView()

    protected abstract fun getViewBinding(): VB

    private fun initImmersionBar() {
        ImmersionBar.with(this).statusBarDarkFont(true).init()
    }
}

