package com.example.technomusic.di.module

import com.example.technomusic.data.repository.MediaRepositoryImpl
import com.example.technomusic.domain.repository.MediaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
   @Binds
    fun bindMediaRepository(mediaRepositoryImpl: MediaRepositoryImpl): MediaRepository
}