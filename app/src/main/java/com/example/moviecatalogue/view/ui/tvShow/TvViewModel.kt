package com.example.moviecatalogue.view.ui.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList

import com.example.moviecatalogue.model.data.entity.TvEntity
import com.example.moviecatalogue.model.data.remote.repository.DataRepository
import com.example.moviecatalogue.vo.Resource


class TvViewModel(private val mDataDataRepository: DataRepository): ViewModel() {
    fun getTvModel():LiveData<Resource<PagedList<TvEntity>>> = mDataDataRepository.getTvShows()
}