package com.example.technomusic.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.technomusic.data.model.Genres
import com.example.technomusic.domain.usecases.GetGenresUseCase
import com.example.technomusic.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(private val getGenresUseCase: GetGenresUseCase) : ViewModel() {
    private val _stateFlow: MutableStateFlow<Resource<List<Genres>>> = MutableStateFlow(Resource.Loading())
    val stateFlow: StateFlow<Resource<List<Genres>>> get() = _stateFlow

//    init {
//        viewModelScope.launch {
//            val data = getGenresUseCase.invoke()
//            data.collect {
//                _stateFlow.value = Resource.Success(it)
//            }
//        }
//    }
}