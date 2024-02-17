package com.example.technomusic.domain.usecases

import android.util.Log
import com.example.technomusic.data.model.Artist
import com.example.technomusic.data.model.Song
import com.example.technomusic.utils.Utility
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetArtistUseCase @Inject constructor() {
    private val TAG = "GetArtistUseCase"
    operator fun invoke(data: List<Song>): Flow<List<Artist>> = flow {
        val artists = data.groupBy { it.artist }
            .map { (artist, songs) ->
                Artist(
                    artistName = artist,
                    songs = songs,
                    defaultImageId = Utility.getRandomImage()
                )
            }
        Log.e(TAG, "invoke: $artists", )
        emit(artists)
    }
}