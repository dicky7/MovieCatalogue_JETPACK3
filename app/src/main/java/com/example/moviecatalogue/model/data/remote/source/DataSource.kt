package com.example.moviecatalogue.model.data.remote.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.moviecatalogue.model.data.entity.TvEntity
import com.example.moviecatalogue.model.data.entity.DetailEntity
import com.example.moviecatalogue.model.data.entity.MovieEntity
import com.example.moviecatalogue.vo.Resource

interface DataSource {
    fun getMovie():  LiveData<Resource<PagedList<MovieEntity>>>
    fun getMovieDetail(id: Int): LiveData<Resource<MovieEntity>>
    fun setMovieFav(movieEntity: MovieEntity, state: Boolean)
    fun getMovieFav(): LiveData<PagedList<MovieEntity>>

    fun getTvShows(): LiveData<Resource<PagedList<TvEntity>>>
    fun getDetailTvShow(id: Int): LiveData<Resource<TvEntity>>
    fun setTvFav(tvEntity: TvEntity, state: Boolean)
    fun getTvFav(): LiveData<PagedList<TvEntity>>
}