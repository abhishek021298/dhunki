package com.example.technomusic.data.model

data class Media(
    val mediaId: String,
    val albumId: Long,
    val artistId: Long,
    val mediaUri: String,
    val albumUri : String,
    val title: String,
    val artist: String,
    val album: String,
    val genres: String,
    val folder: String,
    val duration: Long
)
