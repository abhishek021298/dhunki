package com.example.technomusic.domain.usecases

import android.content.ContentResolver
import android.content.ContentUris
import android.net.Uri
import android.provider.MediaStore
import com.example.technomusic.data.model.Media
import com.example.technomusic.utils.MediaStoreConfig
import com.example.technomusic.utils.MediaStoreConfig.asFolder
import com.example.technomusic.utils.MediaStoreConfig.getLong
import com.example.technomusic.utils.MediaStoreConfig.getString
import com.example.technomusic.utils.MediaStoreConfig.observe
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class GetMediaQuery @Inject constructor(private val contentResolver: ContentResolver) {

    operator fun invoke() = contentResolver.observe(uri = MediaStoreConfig.Song.Collection).map {
        buildList {
            contentResolver.query(
                MediaStoreConfig.Song.Collection,
                MediaStoreConfig.Song.Projection,
                buildString {
                    append("${MediaStore.Audio.Media.IS_MUSIC} != 0")

                }, null,
                null
            )?.use { cursor ->
                while (cursor.moveToNext()) {
                    val id = cursor.getLong(MediaStore.Audio.Media._ID)
                    val albumId = cursor.getLong(MediaStore.Audio.Media.ALBUM_ID)
                    val artistId = cursor.getLong(MediaStore.Audio.Media.ARTIST_ID)
                    val title = cursor.getString(MediaStore.Audio.Media.TITLE)
                    val artist = cursor.getString(MediaStore.Audio.Media.ARTIST)
                    val album = cursor.getString(MediaStore.Audio.Media.ALBUM)
                    val genres = cursor.getString(MediaStore.Audio.Media.GENRE)
                    val duration = cursor.getLong(MediaStore.Audio.Media.DURATION)

                    val mediaId = id.toString()
                    val mediaUri = ContentUris.withAppendedId(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        id
                    )
                    val albumUri = Uri.parse("content://media/external/audio/albumart/$albumId")

                    val folder = cursor.getString(MediaStore.Audio.Media.DATA).asFolder()


                    Media(
                        mediaId = mediaId,
                        albumId = albumId,
                        artistId = artistId,
                        mediaUri = mediaUri.toString(),
                        albumUri = albumUri.toString(),
                        title = title,
                        artist = artist,
                        album = album,
                        genres = genres ?: "Other",
                        folder = folder,
                        duration = duration
                    ).let(::add)
                }
            }
        }

    }
}