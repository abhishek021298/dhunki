package com.example.technomusic.data.model

data class Genres(
    val genresName: String,
    val songs: List<Song>,
    val albumUri: String,
    val defaultImageId: Int,
    val primaryColor : String?
)
