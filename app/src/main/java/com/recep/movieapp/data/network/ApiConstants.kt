package com.recep.movieapp.data.network

object ApiConstants {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "cd98ba0381c5becdeeaa50551d419718"


    const val IMAGES_URL = "https://image.tmdb.org/t/p/"
    const val IMAGE_POSTER_SIZE = "w500"
    const val POSTER_URL = "$IMAGES_URL$IMAGE_POSTER_SIZE"


    const val NOW_PLAYING = "movie/now_playing"
    const val UPCOMING = "movie/upcoming"
    const val MOVIE_DETAIL = "movie/{movie_id}"
}