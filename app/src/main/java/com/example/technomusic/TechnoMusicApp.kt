package com.example.technomusic

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TechnoMusicApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}