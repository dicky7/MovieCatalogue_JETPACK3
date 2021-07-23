package com.example.moviecatalogue.di

import android.content.Context
import com.example.moviecatalogue.model.data.LocalDataSource
import com.example.moviecatalogue.model.data.remote.repository.DataRepository
import com.example.moviecatalogue.model.data.remote.source.RemoteDataSource
import com.example.moviecatalogue.model.data.room.MovieDatabase
import com.example.moviecatalogue.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): DataRepository {
        val database = MovieDatabase.getInstance(context)
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        val remoteDataSource = RemoteDataSource.getInstance()
        return DataRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}