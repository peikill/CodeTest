package com.example.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.architecture.ext.util.ClassUtils

abstract class BaseVMActivity<VB : ViewBinding, VM : ViewModel> : BaseActivity<VB>() {

    open val mViewModel: VM by lazy {
        ViewModelProvider(this).get(ClassUtils.getSuperClassGenericType(javaClass)) as VM
    }
}

