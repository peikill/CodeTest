package com.example.uicore.base.adapter.binding.singeItem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.uicore.base.adapter.BaseSingleAdapter


/**
 * dataBinding模式抽象的单Item基类BaseAdapter
 */
abstract class BaseBindAdapter<T> constructor(
    data: MutableList<T>,
    var layoutId: Int,
    private var brId: Int
) :
    BaseSingleAdapter<T, BaseViewHolder>(layoutId, data) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            HEADER_VIEW, EMPTY_VIEW, FOOTER_VIEW, LOAD_MORE_VIEW -> {
                super.onCreateViewHolder(parent, viewType)
            }
            else -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding =
                    DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutId, parent, false)
                val baseViewHolder = BaseViewHolder(binding.root)
                bindViewClickListener(baseViewHolder, viewType)
                return baseViewHolder
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        when (getItemViewType(position)) {
            HEADER_VIEW, EMPTY_VIEW, FOOTER_VIEW, LOAD_MORE_VIEW -> {
                super.onBindViewHolder(holder, position)
            }
            else -> {
                DataBindingUtil.getBinding<ViewDataBinding>(holder.itemView)
                    ?.setVariable(brId, data[position - headerLayoutCount])
                convert(holder, data[position - headerLayoutCount])
                DataBindingUtil.getBinding<ViewDataBinding>(holder.itemView)
                    ?.executePendingBindings()
            }
        }
    }



    override fun convert(holder: BaseViewHolder, item: T) {

    }

}