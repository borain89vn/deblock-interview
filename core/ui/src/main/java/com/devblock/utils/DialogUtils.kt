package com.devblock.utils

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.devblock.ui.R
import com.devblock.ui.components.TransparentProgressDialog

object DialogUtils {

    fun showLoadingDialog(fragment:  Fragment,isCancel:Boolean?=false): TransparentProgressDialog? {
        val context = fragment.context ?: return null
        context?.let {
            val display = fragment.activity?.windowManager?.defaultDisplay
            val progressDialog = TransparentProgressDialog(it, display!!,isCancel)
            progressDialog.let {
                if (!it.isShowing) {
                    try {
                        it.show()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                return it
            }
        }

    }

    fun showOkAlert(context: Context, message: String?,action:()->Unit={}) {
        try {

            val builder = AlertDialog.Builder(context)
            builder.setMessage(message)
            builder.setPositiveButton(context.getString(R.string.alert_ok_button)) { dlog, _ ->
                action.invoke()
                dlog.dismiss()
            }
            val alertDialog = builder.create()
            alertDialog.setCanceledOnTouchOutside(false)


            alertDialog.show()
        } catch (e: Exception) {
            Log.e("ShowOkAlert", "Exception: " + e.message)
        }

    }

    fun showOkAlert(context: Context, title: Int, message: Int) {
        try {

            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(message)
            builder.setPositiveButton(context.getString(R.string.alert_ok_button)) { dlog, _ -> dlog.dismiss() }
            val alertDialog = builder.create()
            alertDialog.setCanceledOnTouchOutside(true)


            alertDialog.show()
        } catch (e: Exception) {
            Log.e("ShowOkAlert", "Exception: " + e.message)
        }

    }
    fun showOkAlert(context: Context, message: Int) {
        try {

            val builder = AlertDialog.Builder(context)

            builder.setMessage(message)
            builder.setPositiveButton(context.getString(R.string.alert_ok_button)) { dlog, _ -> dlog.dismiss() }
            val alertDialog = builder.create()
            alertDialog.setCanceledOnTouchOutside(true)


            alertDialog.show()
        } catch (e: Exception) {
            Log.e("ShowOkAlert", "Exception: " + e.message)
        }

    }
}