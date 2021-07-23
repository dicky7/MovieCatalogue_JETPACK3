package com.example.moviecatalogue.model.api

import com.example.moviecatalogue.model.data.remote.response.movie.MovieDetailResponse
import com.example.moviecatalogue.model.data.remote.response.movie.MoviesResponse
import com.example.moviecatalogue.model.data.remote.response.tv.TvShowDetailResponse
import com.example.moviecatalogue.model.data.remote.response.tv.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitEndPoint {
    @GET("discover/movie")
    fun getListMovie(
            @Query("api_key") apiKey: String
    ) : Call<MoviesResponse>

    @GET("movie/{id}")
    fun getMovieDetail(
            @Path("id") id: String,
            @Query("api_key") apiKey: String
    ) : Call<MovieDetailResponse>

    @GET("discover/tv")
    fun getListTv(
        @Query("api_key") apiKey: String
    ) : Call<TvShowResponse>

    @GET("tv/{id}")
    fun getTvDetail(
            @Path("id") id: String,
            @Query("api_key") apiKey: String
    ) : Call<TvShowDetailResponse>
}