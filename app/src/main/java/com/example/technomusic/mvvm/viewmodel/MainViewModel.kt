package com.example.technomusic.mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technomusic.data.model.Album
import com.example.technomusic.data.model.Artist
import com.example.technomusic.data.model.Genres
import com.example.technomusic.data.model.Song
import com.example.technomusic.domain.usecases.GetAlbumUseCase
import com.example.technomusic.domain.usecases.GetArtistUseCase
import com.example.technomusic.domain.usecases.GetGenresUseCase
import com.example.technomusic.domain.usecases.GetSongsUseCase
import com.example.technomusic.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val songsUseCase: GetSongsUseCase,
    private val albumUseCase: GetAlbumUseCase,
    private val artistUseCase: GetArtistUseCase,
    private val genresUseCase: GetGenresUseCase
) : ViewModel() {

    private val TAG = "MainViewModel"

    private val _songsMutableData: MutableLiveData<Resource<List<Song>>> = MutableLiveData(Resource.Loading())
    val songsLiveData: LiveData<Resource<List<Song>>> get() = _songsMutableData

    private val _artistMutableData: MutableLiveData<Resource<List<Artist>>> = MutableLiveData(Resource.Loading())
    val artistLiveData: LiveData<Resource<List<Artist>>> get() = _artistMutableData

    private val _albumMutableData: MutableLiveData<Resource<List<Album>>> = MutableLiveData(Resource.Loading())
    val albumLiveData: LiveData<Resource<List<Album>>> get() = _albumMutableData

    private val _genresMutableData: MutableLiveData<Resource<List<Genres>>> = MutableLiveData(Resource.Loading())
    val genresLiveData: LiveData<Resource<List<Genres>>> get() = _genresMutableData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Get media query and songs data
                val songsData = songsUseCase.invoke()

                songsData.collect { songList ->
                    Log.e(TAG, "song: $songList")
                    albumUseCase.invoke(songList).collectLatest {
                        Log.e(TAG, "albumData: $it", )
                        _albumMutableData.postValue(Resource.Success(it))
                    }

                    genresUseCase.invoke(songList).collectLatest {
                        Log.e(TAG, "genresData: $it", )
                        _genresMutableData.postValue(Resource.Success(it))
                    }

                    artistUseCase.invoke(songList).collectLatest {
                        Log.e(TAG, "artistData: $it", )
                        _artistMutableData.postValue(Resource.Success(it))
                    }
                    _songsMutableData.postValue(Resource.Success(songList))
                }
            } catch (e: Exception) {

            }
        }
    }
}