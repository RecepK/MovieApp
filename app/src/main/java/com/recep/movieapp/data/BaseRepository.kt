package com.recep.movieapp.data

import com.recep.movieapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

abstract class BaseRepository {
    fun <T> invokeWithFlow(request: () -> Response<T>): Flow<Resource<T>> = flow<Resource<T>> {
        val response = request.invoke()

        when (response.isSuccessful) {
            false -> {
                emit(Resource.Error(message = "Fail Response Exception"))
            }
            true -> {
                response.body()?.let {
                    emit(Resource.Success(data = it))
                } ?: run {
                    emit(Resource.Error(message = "Null Data Exception"))
                }
            }
        }
    }.onStart {
        emit(Resource.Loading(true))
    }.catch {
        emit(Resource.Error(message = "Service Response Exception"))
    }.onCompletion {
        //emit(Resource.Loading)
    }.flowOn(Dispatchers.IO)


    suspend fun <T> invoke(request: suspend () -> T): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = request.invoke()
                Resource.Success(response)
            } catch (error: Throwable) {
                when (error) {
                    is HttpException -> {
                        Resource.Error(message = "HttpException Exception")
                    }
                    else -> {
                        Resource.Error(message = "Data Exception")
                    }
                }
            }
        }
    }
}