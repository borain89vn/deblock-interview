package com.devblock


import androidx.fragment.app.Fragment
import com.devblock.ui.components.TransparentProgressDialog
import com.devblock.utils.DialogUtils

open class BaseFragment (val layoutId: Int) : Fragment(layoutId) {

    private var progressDialog: TransparentProgressDialog? = null


    protected fun hideProgress() {
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





}