package com.example.technomusic.domain.usecases

import com.example.technomusic.domain.repository.MediaRepository
import javax.inject.Inject

class GetMediaUseCase @Inject constructor(private val mediaRepository: MediaRepository) {
    suspend operator fun invoke() = mediaRepository.media()
}