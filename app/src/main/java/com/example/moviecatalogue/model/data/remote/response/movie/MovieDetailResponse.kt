package com.example.moviecatalogue.model.data.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("release_date")
    val date: String,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("overview")
    val overview: String
)