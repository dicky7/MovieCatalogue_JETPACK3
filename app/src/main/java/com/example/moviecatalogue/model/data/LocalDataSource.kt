package com.example.moviecatalogue.model.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.model.data.entity.MovieEntity
import com.example.moviecatalogue.model.data.entity.TvEntity
import com.example.moviecatalogue.model.data.room.MovieDao

class LocalDataSource private constructor(private val movieDao: MovieDao){
    companion object{
        private var INSTANCE: LocalDataSource? = null
        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao).apply {
                INSTANCE = this
            }
    }
    fun getDataMovie(): DataSource.Factory<Int, MovieEntity> = movieDao.getMovie()

    fun getFavMovie(): DataSource.Factory<Int, MovieEntity> = movieDao.getFavMovie()

    fun getMovieById(id: Int): LiveData<MovieEntity> = movieDao.getMovieById(id)

    fun insertMovie(movie: List<MovieEntity>) = movieDao.insertMovie(movie)

    fun getDataTv(): DataSource.Factory<Int, TvEntity> = movieDao.getTv()

    fun getFavTv(): DataSource.Factory<Int, TvEntity> = movieDao.getFavTv()

    fun getTvById(id: Int): LiveData<TvEntity> = movieDao.getTvById(id)

    fun insertTv(tv: List<TvEntity>) = movieDao.insertTv(tv)

    fun updateFavMovie(movie: MovieEntity, newState: Boolean) {
        movie.fav = newState
        movieDao.updateMovie(movie)
    }

    fun updateFavTv(tv: TvEntity, newState: Boolean) {
        tv.fav = newState
        movieDao.updateTv(tv)
    }


}