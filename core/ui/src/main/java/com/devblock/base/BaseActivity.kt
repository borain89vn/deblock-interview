package com.devblock.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.devblock.extension.observe


abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {
    private lateinit var viewModel: VM
    private lateinit var binding: DB

    @get:LayoutRes
    abstract val layoutId: Int

    abstract fun getVM(): VM

    abstract fun bindVM(binding: DB, vm: VM)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        viewModel = getVM()
        bindVM(binding, viewModel)
        with(viewModel) {
            observe(progressLiveEvent) { show ->
                if (show) showProgress()
                else hideProgress()
            }


        }
    }

    fun showProgress() = BaseProgress().show(supportFragmentManager, PROGRESS)

    fun hideProgress() =
        supportFragmentManager.fragments.filterIsInstance<BaseProgress>().forEach {
            it.dismiss()
        }

    companion object {
        private const val PROGRESS = "Press"
    }
}