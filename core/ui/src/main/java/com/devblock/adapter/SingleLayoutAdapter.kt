package com.devblock.adapter


import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.devblock.BaseViewModel

/**
 * Simplest implementation of [BaseAdapter] to use as initView single layout adapter.
 */
open class SingleLayoutAdapter<T, B : ViewDataBinding>(
        private val layoutId: Int,
        items: List<T>,
        viewModel: BaseViewModel? = null,
        onBind: B.(Int) -> Unit = {}
) : BaseAdapter<T, B>(viewModel = viewModel, items = items, onBind = onBind) {

    override fun getLayoutId(position: Int): Int = layoutId

}