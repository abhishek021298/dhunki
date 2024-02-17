package com.example.technomusic.interfaces

import com.example.technomusic.data.model.Song

interface MediaAdapterClickListener {
    fun onMediaClick(songData: Song, position: Int)
}