package com.recep.movieapp.data

import com.recep.movieapp.data.network.Api
import com.recep.movieapp.model.response.MovieDetailResponse
import com.recep.movieapp.model.response.NowPlayingResponse
import com.recep.movieapp.model.response.UpcomingResponse
import com.recep.movieapp.utils.Resource
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: Api
) : BaseRepository() {

    suspend fun getNowPlaying(): Resource<NowPlayingResponse> {
        return invoke { api.getNowPlaying() }
    }

    suspend fun getUpcoming(page: Int): Resource<UpcomingResponse> {
        return invoke { api.getUpcoming(page = page) }
    }

    suspend fun getMovieDetail(movieId: Int): Resource<MovieDetailResponse> {
        return invoke { api.getMovieDetail(movieId = movieId) }
    }
}