package com.recep.movieapp.utils

sealed class Resource<out R> {
    data class Loading(val isLoading: Boolean) : Resource<Nothing>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()
}