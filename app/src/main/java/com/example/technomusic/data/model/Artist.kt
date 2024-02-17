package com.example.technomusic.data.model

data class Artist(
    val artistName: String,
    val songs: List<Song>,
    val defaultImageId : Int,
)
