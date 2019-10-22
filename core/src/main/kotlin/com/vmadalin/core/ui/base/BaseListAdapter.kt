package com.vmadalin.core.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<T>(
    itemsSame: (T, T) -> Boolean,
    contentsSame: (T, T) -> Boolean
) : ListAdapter<T, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = itemsSame(oldItem, newItem)
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        contentsSame(oldItem, newItem)
}) {

    abstract fun onCreateViewHolder(
        parent: ViewGroup, inflater:
        LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return onCreateViewHolder(parent, layoutInflater, viewType)
    }
}
