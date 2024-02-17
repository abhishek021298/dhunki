package com.example.technomusic.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.technomusic.data.model.Album
import com.example.technomusic.domain.usecases.GetAlbumUseCase
import com.example.technomusic.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(private val albumUseCase: GetAlbumUseCase) : ViewModel() {
    private val _stateFlow: MutableStateFlow<Resource<List<Album>>> = MutableStateFlow(Resource.Loading<List<Album>>())
    val stateFlow: StateFlow<Resource<List<Album>>> get() = _stateFlow

//    init {
//        viewModelScope.launch {
//            val data = albumUseCase.invoke()
//            data.collect {
//                _stateFlow.value = Resource.Success(it)
//            }
//        }
//    }
}