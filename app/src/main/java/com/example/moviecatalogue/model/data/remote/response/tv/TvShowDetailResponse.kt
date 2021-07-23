package com.example.moviecatalogue.model.data.remote.response.tv


import android.net.Network
import com.google.gson.annotations.SerializedName

data class TvShowDetailResponse(

    @SerializedName("first_air_date")
    val firstAirDate: String,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,

)