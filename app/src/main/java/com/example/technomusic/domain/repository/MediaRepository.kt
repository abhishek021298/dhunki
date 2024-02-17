package com.example.technomusic.domain.repository

import com.example.technomusic.data.model.Media
import kotlinx.coroutines.flow.Flow

interface MediaRepository {

    suspend fun media(): Flow<List<Media>>
}