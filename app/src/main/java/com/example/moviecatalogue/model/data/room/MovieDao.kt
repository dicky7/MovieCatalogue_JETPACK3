package com.example.moviecatalogue.model.data.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.moviecatalogue.model.data.entity.MovieEntity
import com.example.moviecatalogue.model.data.entity.TvEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_entity")
    fun getMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_entity")
    fun getTv(): DataSource.Factory<Int, TvEntity>

    @Query("SELECT * FROM movie_entity WHERE fav = 1")
    fun getFavMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_entity WHERE fav = 1")
    fun getFavTv(): DataSource.Factory<Int, TvEntity>

    @Query("SELECT * FROM movie_entity WHERE id = :id")
    fun getMovieById(id: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM tv_entity WHERE id = :id")
    fun getTvById(id: Int): LiveData<TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(tvShow: List<TvEntity>)

    @Update
    fun updateMovie(movieEntity: MovieEntity)

    @Update
    fun updateTv(tvEntity: TvEntity)
}