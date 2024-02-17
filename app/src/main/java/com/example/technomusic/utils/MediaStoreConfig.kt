package com.example.technomusic.utils

import android.content.ContentResolver
import android.database.ContentObserver
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import java.io.File

internal object MediaStoreConfig {
    object Song {
        val Collection: Uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        }

        val Projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.ARTIST_ID,
            MediaStore.Audio.Media.GENRE,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATA,
        )
    }

    internal fun Cursor.getLong(columnName: String) = getLong(getColumnIndexOrThrow(columnName))
    internal fun Cursor.getString(columnName: String) = getString(getColumnIndexOrThrow(columnName))
    internal fun String.asFolder() = File(this).parentFile?.name.orEmpty()

    internal fun ContentResolver.observe(uri: Uri) = callbackFlow {
        val observer = object : ContentObserver(null) {
            override fun onChange(selfChange: Boolean) {
                trySend(selfChange)
            }
        }
        registerContentObserver(uri, true, observer)
        trySend(true)
        awaitClose { unregisterContentObserver(observer) }
    }

}