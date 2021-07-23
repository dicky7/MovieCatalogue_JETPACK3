package com.example.moviecatalogue.model.data.remote.response.tv


import com.google.gson.annotations.SerializedName

data class TvShowResponse(
    @SerializedName("results")
    val results: List<TvRemote>,

    )