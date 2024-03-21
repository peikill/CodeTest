package com.example.uicore.base.adapter

import android.view.View
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView

/**
 * desc   : 统一Adapter接口
 */
interface IAdapter<T> {

    fun getRecyclerViewAdapter(): RecyclerView.Adapter<*>

    fun addData(datas: List<T>)

    fun addData(position: Int, item: T)

    fun remove(position: Int)

    fun getDatas(): List<T>

    fun getItem(position: Int): T?

    fun replaceData(datas: List<T>)

    fun setOnItemClickListener(listener: (adapter: IAdapter<out T>, view: View, position: Int) -> Unit)

    fun setOnItemLongClickListener(listener: (adapter: IAdapter<out T>, view: View, position: Int) -> Boolean)

    fun setOnItemChildClickListener(listener: (adapter: IAdapter<out T>, view: View, position: Int) -> Unit)

    fun setOnItemChildLongClickListener(listener: (adapter: IAdapter<out T>, view: View, position: Int) -> Boolean)

    fun notifyDataSetChanged()

    fun notifyItemRangeChanged(positionStart: Int, itemCount: Int, @Nullable payload: Any? = null)

    fun notifyItemMoved(fromPosition: Int, toPosition: Int)

    fun notifyItemRangeInserted(positionStart: Int, itemCount: Int)

    fun notifyItemRemoved(position: Int)

    fun notifyItemRangeRemoved(positionStart: Int, itemCount: Int)

    fun notifyItemInserted(position: Int)

    fun notifyItemChanged(position: Int, @Nullable payload: Any? = null)

    fun getItemCount(): Int

    fun getItemViewType(position: Int): Int

    fun addData(position: Int, datas: List<T>)
}