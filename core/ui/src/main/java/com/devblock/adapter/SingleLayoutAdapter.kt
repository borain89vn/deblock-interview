package com.devblock.adapter


import androidx.databinding.ViewDataBinding
import com.devblock.base.BaseViewModel

/**
 * Simplest implementation of [BaseAdapter] to use as initView single layout adapter.
 */
open class SingleLayoutAdapter<T : Any, B : ViewDataBinding>(
    private val layoutId: Int,
    items: List<T> = emptyList(),
    onItemClicked: ((T) -> Unit)? = null,
    onBind: B.(Int) -> Unit = {}
) : BaseAdapter<T, B>(items = items, onItemClicked = onItemClicked, onBind = onBind) {

    override fun getLayoutId(position: Int): Int = layoutId
}