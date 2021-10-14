package com.devblock

import android.content.Context
import android.content.Intent
import com.devblock.interview.MainActivity
import com.devblock.utils.AppLauncher

import javax.inject.Inject

class DefaultAppLauncher @Inject constructor(): AppLauncher {

    override fun getLauncherIntent(context: Context): Intent =
        Intent(context, MainActivity::class.java)
}