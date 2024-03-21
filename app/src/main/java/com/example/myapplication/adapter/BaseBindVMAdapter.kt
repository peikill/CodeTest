package com.example.uicore.base.adapter.binding.singeItem

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.architecture.config.DataBindingAdapterConfig


/**
 * MVVM模式抽象的单Item基类BaseAdapter
 */
abstract class BaseBindVMAdapter<T> constructor(
    var viewModel: ViewModel,
    vmData: MutableList<T>,
    vmLayoutId: Int,
    private var viewModelBrId: Int,
    vmDataBrId: Int,
    val dataBindingConfig: DataBindingAdapterConfig? = null
) : BaseBindAdapter<T>(vmData, vmLayoutId, vmDataBrId) {
    override fun convert(holder: BaseViewHolder, item: T) {
       val dataBinding= DataBindingUtil.getBinding<ViewDataBinding>(holder.itemView)
        dataBinding?.setVariable(viewModelBrId, viewModel)
        dataBindingConfig?.bindingParams?.forEach {
            dataBinding?.setVariable(it.key, it.value)
        }
    }


}