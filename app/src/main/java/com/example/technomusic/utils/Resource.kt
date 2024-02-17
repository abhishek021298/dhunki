package com.example.technomusic.utils

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val code: Int, val message: String, val data: T? = null) : Resource<T>()
    class Loading<T> : Resource<T>()
}