package com.recep.movieapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.recep.movieapp.data.Repository
import com.recep.movieapp.model.response.NowPlayingResponse
import com.recep.movieapp.model.response.UpcomingResponse
import com.recep.movieapp.utils.Resource
import com.recep.movieapp.utils.mLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    val nowPlayingResponse: MutableLiveData<NowPlayingResponse?> = MutableLiveData()
    val upcomingResponse: MutableLiveData<UpcomingResponse?> = MutableLiveData()

    init {
        getNowPlaying()
        getUpcoming()
    }

    private fun getNowPlaying() {
        viewModelScope.launch {
            val response = repository.getNowPlaying()
            when (response) {
                is Resource.Error -> {
                    "NowPlaying Error ${response.message}".mLog()
                }
                is Resource.Loading -> {}
                is Resource.Success -> {
                    nowPlayingResponse.value = response.data
                }
            }
        }
    }

    // for pagination
    fun getUpcoming(page: Int = 1) {
        viewModelScope.launch {
            val response = repository.getUpcoming(page = page)
            when (response) {
                is Resource.Error -> {
                    "Upcoming Error ${response.message}".mLog()
                }
                is Resource.Loading -> {}
                is Resource.Success -> {
                    upcomingResponse.value = response.data
                }
            }
        }
    }
}