package com.example.technomusic.data.repository

import com.example.technomusic.data.model.Media
import com.example.technomusic.domain.repository.MediaRepository
import com.example.technomusic.domain.usecases.GetMediaQuery
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(private val mediaData: GetMediaQuery) : MediaRepository {
    private var cachedMediaData: Flow<List<Media>>? = null

    override suspend fun media(): Flow<List<Media>> {
        if (cachedMediaData == null) {
            cachedMediaData = mediaData.invoke()
        }
        return cachedMediaData!!
    }


}