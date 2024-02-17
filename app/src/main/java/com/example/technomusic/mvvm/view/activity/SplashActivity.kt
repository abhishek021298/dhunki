package com.example.technomusic.mvvm.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.technomusic.R
import com.example.technomusic.databinding.ActivitySplashBinding
import com.example.technomusic.utils.PermissionUtils

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val mPermission = PermissionUtils(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            installSplashScreen()
            initActivity()
        } else {
            setTheme(R.style.Theme_TechnoMusic_FullScreen)
            setContentView(binding.root)
            Handler(mainLooper).postDelayed({
                initActivity()
            }, 1000)
        }


    }

    private fun initActivity() {
        if (mPermission.isPermissionAccepted()) {
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()

        } else {
            startActivity(Intent(this, PermissionActivity::class.java))
            finish()
        }
    }
}