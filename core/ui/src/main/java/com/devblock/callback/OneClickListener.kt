package com.devblock.callback

import android.os.SystemClock

import android.view.View


class OneClickListener(
        private val onClickListener: View.OnClickListener, private var defaultInterval: Int = 1000
) : View.OnClickListener {
    private var lastTimeClicked: Long = 0
    override fun onClick(v: View) {

        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onClickListener.onClick(v)
    }
}