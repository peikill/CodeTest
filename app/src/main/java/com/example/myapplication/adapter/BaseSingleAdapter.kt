package com.example.uicore.base.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * Adapter实现基类
 */
abstract class BaseSingleAdapter<T, H : BaseViewHolder>(
    layoutResId: Int,
    data: MutableList<T>?
) : BaseQuickAdapter<T, H>(layoutResId, data), IAdapter<T>, LoadMoreModule {

    private var onItemClickListener:
            ((adapter: IAdapter<out T>, v: View, position: Int) -> Unit)? = null
    private var onItemLongClickListener:
            ((adapter: IAdapter<out T>, v: View, position: Int) -> Boolean)? = null
    private var onItemChildClickListener:
            ((adapter: IAdapter<out T>, v: View, position: Int) -> Unit)? = null
    private var onItemChildLongClickListener:
            ((adapter: IAdapter<out T>, v: View, position: Int) -> Boolean)? = null


    override fun getDatas(): List<T> {
        return data
    }


    override fun setOnItemClickListener(listener: (adapter: IAdapter<out T>, view: View, position: Int) -> Unit) {
        onItemClickListener = listener
        super.setOnItemClickListener { adapter, view, position ->
            listener?.invoke(
                this,
                view,
                position
            )
        }
    }


    override fun setOnItemLongClickListener(listener: (adapter: IAdapter<out T>, view: View, position: Int) -> Boolean) {
        onItemLongClickListener = listener
        super.setOnItemLongClickListener { adapter, view, position ->
            onItemLongClickListener?.invoke(this, view, position) ?: false
        }
    }

    override fun setOnItemChildClickListener(listener: (adapter: IAdapter<out T>, view: View, position: Int) -> Unit) {
        onItemChildClickListener = listener
        super.setOnItemChildClickListener { adapter, view, position ->
            onItemChildClickListener?.invoke(this, view, position)
        }
    }

    override fun setOnItemChildLongClickListener(listener: (adapter: IAdapter<out T>, view: View, position: Int) -> Boolean) {
        onItemChildLongClickListener = listener
        super.setOnItemChildLongClickListener { adapter, view, position ->
            onItemChildLongClickListener?.invoke(this, view, position) ?: false
        }
    }

    override fun addData(datas: List<T>) {
        super.addData(datas)
    }

    override fun addData(position: Int, datas: List<T>) {
        super.addData(position, datas)
    }

    override fun addData(position: Int, data: T) {
        super.addData(position, data)
    }

    override fun replaceData(datas: List<T>) {
        super.replaceData(datas)
    }

    override fun getRecyclerViewAdapter(): RecyclerView.Adapter<*> {
        return this
    }

    fun getViewHolder(position: Int): BaseViewHolder? {
        return recyclerView.findViewHolderForAdapterPosition(position) as? BaseViewHolder
    }

    fun getItemData(position: Int): T {
        return data[position]
    }
}