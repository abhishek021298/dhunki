package com.example.technomusic.domain.usecases

import android.app.Application
import android.graphics.BitmapFactory
import androidx.core.net.toUri
import com.example.technomusic.data.model.Song
import com.example.technomusic.domain.repository.MediaRepository
import com.example.technomusic.utils.Utility
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.FileNotFoundException
import javax.inject.Inject

class GetSongsUseCase @Inject constructor(private val mediaRepository: MediaRepository, private val application: Application) {

    suspend operator fun invoke(): Flow<List<Song>> = flow {
        val mediaData = mediaRepository.media()
        mediaData.collect { mediaList ->
            val songList = mutableListOf<Song>()

            mediaList.forEach {
                var mediaId: Int = -1

                try {
                    // Try to open album Uri and decode bitmap
                    application.contentResolver.openInputStream(it.albumUri.toUri())?.use { inputStream ->
                        BitmapFactory.decodeStream(inputStream)
                    }
                } catch (e: FileNotFoundException) {
                    // If there's an exception, get a random image mediaId
                    mediaId = Utility.getRandomImage()
                }

                val song = Song(
                    mediaUri = it.mediaUri,
                    albumUri = it.albumUri,
                    title = it.title,
                    artist = it.artist,
                    album = it.album,
                    defaultImageId = mediaId,
                    genres = it.genres
                )
                songList.add(song)
            }
            emit(songList) // Emit the songList when it's fully constructed
        }
    }
}
