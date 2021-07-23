 package com.example.moviecatalogue.utils

import com.example.moviecatalogue.model.data.entity.DetailEntity
import com.example.moviecatalogue.model.data.entity.MovieEntity
import com.example.moviecatalogue.model.data.entity.TvEntity
import com.example.moviecatalogue.model.data.remote.response.movie.Genre
import com.example.moviecatalogue.model.data.remote.response.movie.MovieDetailResponse
import com.example.moviecatalogue.model.data.remote.response.tv.TvShowDetailResponse

object DetailDataMov {
    fun getMovieDetail(): MovieEntity{
        return MovieEntity(
            632357,
            "The Unholy",
            "/6wxfWZxQcuv2QgxIQKj0eYTdKTv.jpg",
            "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
            "2021-03-31"
        )
    }
    fun getTvDetail(): TvEntity{
        return TvEntity(
            63174,
            "Lucifer",
            "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
            "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
            "2016-01-25"
        )
    }

    fun getMovieDetailRemote(): MovieDetailResponse{
        return MovieDetailResponse(
            id= 632357,
            title = "The Unholy",
            date = "2021-03-31",
            genres = listOf(
                Genre(id = 27, name = "Horor"),
            ),
            posterPath = "/6wxfWZxQcuv2QgxIQKj0eYTdKTv.jpg",
            overview = "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister."

        )
    }
    fun getTvDetailRemote(): TvShowDetailResponse{
        return TvShowDetailResponse(
            id = 63174,
            name = "Lucifer",
            firstAirDate = "2016-01-25",
            genres = listOf(
                com.example.moviecatalogue.model.data.remote.response.tv.Genre(id = 80, name = "Crime"),
                com.example.moviecatalogue.model.data.remote.response.tv.Genre(id = 10765, name = "Sci-Fi & Fantasy"),
            ),
                posterPath = "/h48Dpb7ljv8WQvVdyFWVLz64h4G.jpg",
                overview = "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape."
        )
    }


}