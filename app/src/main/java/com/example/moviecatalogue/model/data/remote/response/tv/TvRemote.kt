package com.example.moviecatalogue.model.data.remote.response.tv


import com.google.gson.annotations.SerializedName

data class TvRemote(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("first_air_date")
    val date: String,
    @SerializedName("overview")
    val overview: String

    )