package com.example.technomusic.data.model

data class Album(
    val albumName: String,
    val albumUri: String,
    val songs: List<Song>,
    val defaultImageId: Int = -1,
    val primaryColor: String? = null
)
