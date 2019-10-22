package com.vmadalin.core.ui.base

import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.ViewDataBinding

abstract class BaseViewHolder<T : ViewDataBinding>(
    val binding: T
): RecyclerView.ViewHolder(binding.root)
