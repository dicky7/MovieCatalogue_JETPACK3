package com.example.moviecatalogue.view.ui.favorite.favoriteMovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogue.model.data.entity.MovieEntity
import com.example.moviecatalogue.model.data.remote.repository.DataRepository
import com.example.moviecatalogue.vo.Resource

class FavoriteMovieViewModel(private val mDataDataRepository: DataRepository): ViewModel() {
    fun getFavoriteMovie() = mDataDataRepository.getMovieFav()
    fun setFavoriteMovie(movieEntity: MovieEntity){
        val newState = !movieEntity.fav
        mDataDataRepository.setMovieFav(movieEntity, newState)
    }
}