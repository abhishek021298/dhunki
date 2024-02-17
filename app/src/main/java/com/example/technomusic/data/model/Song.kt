package com.example.technomusic.data.model

import java.io.Serializable

data class Song(
    val mediaUri: String,
    val albumUri : String,
    val title: String,
    val artist: String,
    val album: String,
    val defaultImageId : Int,
    val genres : String
) : Serializable
