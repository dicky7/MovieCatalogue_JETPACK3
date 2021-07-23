package com.example.moviecatalogue.model.data.remote.response.movie


import com.google.gson.annotations.SerializedName

data class MovieRemote(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("release_date")
    val date: String

)