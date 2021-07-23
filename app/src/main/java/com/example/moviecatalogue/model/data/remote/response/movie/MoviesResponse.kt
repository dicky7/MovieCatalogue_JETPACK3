package com.example.moviecatalogue.model.data.remote.response.movie


import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("results")
    val results: List<MovieRemote>,
)