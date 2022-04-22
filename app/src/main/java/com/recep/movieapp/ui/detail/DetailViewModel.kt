package com.recep.movieapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.recep.movieapp.data.Repository
import com.recep.movieapp.model.response.MovieDetailResponse
import com.recep.movieapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    val movieDetailResponse: MutableLiveData<MovieDetailResponse?> = MutableLiveData()

    fun getMovieDetail(id: Int) {
        viewModelScope.launch {
            val response = repository.getMovieDetail(id)
            when (response) {
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    movieDetailResponse.value = response.data
                }
            }
        }
    }
}