package com.example.technomusic.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technomusic.data.model.Song
import com.example.technomusic.domain.usecases.GetSongsUseCase
import com.example.technomusic.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SongsViewModel @Inject constructor(private val songsUseCase: GetSongsUseCase) : ViewModel() {


    private val _stateFlow: MutableLiveData<Resource<List<Song>>> = MutableLiveData(Resource.Loading())
    val stateFlow: LiveData<Resource<List<Song>>> get() = _stateFlow

//    init {
//        viewModelScope.launch {
//            val data = songsUseCase.invoke()
//            _stateFlow.value = Resource.Success(data)
//            Log.e("TAG", ":$data ")
//
//        }
//    }
}