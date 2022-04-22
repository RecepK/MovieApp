package com.recep.movieapp.data.network

import com.recep.movieapp.data.network.ApiConstants.MOVIE_DETAIL
import com.recep.movieapp.data.network.ApiConstants.NOW_PLAYING
import com.recep.movieapp.data.network.ApiConstants.UPCOMING
import com.recep.movieapp.model.response.MovieDetailResponse
import com.recep.movieapp.model.response.NowPlayingResponse
import com.recep.movieapp.model.response.UpcomingResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET(NOW_PLAYING)
    suspend fun getNowPlaying(
        @Query("api_key") key: String = ApiConstants.API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): NowPlayingResponse

    @GET(UPCOMING)
    suspend fun getUpcoming(
        @Query("api_key") key: String = ApiConstants.API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): UpcomingResponse

    @GET(MOVIE_DETAIL)
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String = ApiConstants.API_KEY,
    ): MovieDetailResponse
}