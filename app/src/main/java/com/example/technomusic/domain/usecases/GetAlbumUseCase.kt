package com.example.technomusic.domain.usecases

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.net.toUri
import com.example.technomusic.data.model.Album
import com.example.technomusic.data.model.Song
import com.example.technomusic.utils.PaletteUtils
import com.example.technomusic.utils.Utility
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.FileNotFoundException
import javax.inject.Inject

class GetAlbumUseCase @Inject constructor(private val application: Application) {
    operator fun invoke(data: List<Song>): Flow<List<Album>> = flow {
        val album = data.groupBy { it.album }
            .map { (album, songs) ->
                val song = songs.first()
                var mediaId: Int = -1

                //Check albumUri is valid or not
                val imageBitmap: Bitmap? = try {
                    // Try to open album Uri and decode bitmap
                    application.contentResolver.openInputStream(song.albumUri.toUri())?.use { inputStream ->
                        BitmapFactory.decodeStream(inputStream)
                    }
                } catch (e: FileNotFoundException) {
                    // If there's an exception, get a random image mediaId
                    mediaId = Utility.getRandomImage()
                    Utility.getBitmapFromId(application, mediaId)
                }

                //Get primary color from image bitmap
                val primaryColor = imageBitmap?.let { PaletteUtils.getImagePrimaryColor(it) } ?: kotlin.run {
                    "#49C5B9"
                }
                Album(
                    albumName = album,
                    albumUri = song.albumUri,
                    songs = songs,
                    defaultImageId = mediaId,
                    primaryColor = primaryColor
                )
            }
        emit(album)
    }
}