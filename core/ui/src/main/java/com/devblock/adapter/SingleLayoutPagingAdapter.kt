package com.devblock.adapter

import androidx.databinding.ViewDataBinding
import com.devblock.utils.paging.ItemComparable

open class SingleLayoutPagingAdapter<T : ItemComparable, B : ViewDataBinding>(
    private val layoutId: Int,
    onItemClicked: ((T) -> Unit)? = null,
    onBind: B.(Int) -> Unit = {}
) : BasePagingAdapter<T, B>(onItemClicked = onItemClicked, onBind = onBind) {

    override fun getLayoutId(position: Int): Int = layoutId
}