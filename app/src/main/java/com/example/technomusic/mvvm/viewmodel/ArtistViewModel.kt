package com.example.technomusic.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.technomusic.data.model.Artist
import com.example.technomusic.domain.usecases.GetArtistUseCase
import com.example.technomusic.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ArtistViewModel @Inject constructor(private val artistUseCase: GetArtistUseCase) : ViewModel() {

    private val _stateFlow: MutableStateFlow<Resource<List<Artist>>> = MutableStateFlow(Resource.Loading<List<Artist>>())
    val stateFlow: StateFlow<Resource<List<Artist>>> get() = _stateFlow
//
//    init {
//        viewModelScope.launch {
//            val data = artistUseCase.invoke()
//            data.collect {
//                _stateFlow.value = Resource.Success(it)
//            }
//        }
//    }
}