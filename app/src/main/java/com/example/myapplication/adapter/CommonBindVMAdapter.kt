package com.example.uicore.base.adapter.binding.singeItem

import androidx.lifecycle.ViewModel

/**
 * MVVM通用的单Item的Adapter
 */
open class CommonBindVMAdapter<T> constructor(
    viewModel: ViewModel,
    commonData: MutableList<T>,
    commonItemLayoutId: Int,
    commonViewModelId: Int,
    commonDataBrId: Int
) : BaseBindVMAdapter<T>(
    viewModel,
    commonData,
    commonItemLayoutId,
    commonViewModelId,
    commonDataBrId
)





