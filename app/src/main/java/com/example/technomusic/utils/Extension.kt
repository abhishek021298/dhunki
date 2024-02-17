package com.example.technomusic.utils

import android.content.ContentResolver
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.InputStream

// Convert px to dp
val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

//Convert dp to px
val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Context.getBitmapFromUri(uri: Uri): Bitmap? {
    var bitmap: Bitmap? = null
    try {
        val contentResolver: ContentResolver = this.contentResolver
        val inputStream: InputStream? = contentResolver.openInputStream(uri)
        bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream?.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return bitmap
}
