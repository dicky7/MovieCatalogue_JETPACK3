package com.example.moviecatalogue.view.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogue.model.data.entity.MovieEntity
import com.example.moviecatalogue.model.data.remote.repository.DataRepository
import com.example.moviecatalogue.utils.DataMovie
import com.example.moviecatalogue.vo.Resource

class MovieViewModel(private val mDataDataRepository: DataRepository): ViewModel() {
    fun getMovieModel():LiveData<Resource<PagedList<MovieEntity>>> = mDataDataRepository.getMovie()
}