package com.devblock.ui.components

import android.app.Dialog
import android.content.Context
import android.view.Display
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.devblock.ui.R

class TransparentProgressDialog(context: Context, display: Display,isCancel:Boolean?=false) : Dialog(context, R.style.TransparentProgressDialog) {


    init {
        val wlmp = window!!.attributes
        wlmp.gravity = Gravity.CENTER_HORIZONTAL


        window!!.attributes = wlmp
        setTitle(null)
        setCancelable(isCancel!!)
        setOnCancelListener(null)
        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL
        val params = LinearLayout.LayoutParams(display.width / 8, display.width / 8)
        val progressBar = ProgressBar(getContext())

        layout.addView(progressBar, params)
        addContentView(layout, params)

    }


}