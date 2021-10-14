package com.devblock


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.devblock.ui.components.TransparentProgressDialog
import com.devblock.utils.DialogUtils



 open class BaseFragmentBinding<B : ViewDataBinding>(val layoutId: Int) : Fragment(layoutId) {

    private var progressDialog: TransparentProgressDialog? = null
    lateinit var binding: B


   protected  fun hideProgress() {
        progressDialog?.let { if (it.isShowing) it.cancel() }
    }


   protected fun showProgress() {

        progressDialog?.let { if (it.isShowing) return }
        try {
            progressDialog = DialogUtils.showLoadingDialog(this,true)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewInitialized(binding)
    }

     override fun onDestroyView() {
         super.onDestroyView()
         hideProgress()
     }

    open fun onViewInitialized(binding: B) {}

}
