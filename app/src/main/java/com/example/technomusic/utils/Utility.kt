package com.example.technomusic.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.core.view.WindowCompat
import java.util.Random

object Utility {
    @Suppress("DEPRECATION")
    fun Activity.transparentStatusAndNavigation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // this.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            // this.window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            // this.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        } else {
            val systemUiVisibility = 0 or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            window.decorView.systemUiVisibility = systemUiVisibility

            val winParams = window.attributes
            winParams.flags = winParams.flags or WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION

            winParams.flags = winParams.flags and (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION).inv()

            window.attributes = winParams
        }

        makeDarkNavigationBarTextColor(false)
        makeDarkStatusBarTextColor(false)
    }


    private fun Activity.makeDarkStatusBarTextColor(isDark: Boolean = true) {
        val windowInset = WindowCompat.getInsetsController(this.window, this.window.decorView)
        //windowInset.isAppearanceLightStatusBars = (this.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_NO
        windowInset.isAppearanceLightStatusBars = isDark // force fully dark text in both light & dark mode
    }

    private fun Activity.makeDarkNavigationBarTextColor(isDark: Boolean = true) {
        val windowInset = WindowCompat.getInsetsController(this.window, this.window.decorView)
        windowInset.isAppearanceLightNavigationBars = isDark // force fully dark text in both light & dark mode
    }

    fun getRandomImage(): Int {
        val random = Random()
        return DefaultMediaList.defaultMediaList[random.nextInt(DefaultMediaList.defaultMediaList.size)]
    }

    fun getBitmapFromId(context: Context, id : Int) : Bitmap {
        return BitmapFactory.decodeResource(context.resources, id)
    }

}