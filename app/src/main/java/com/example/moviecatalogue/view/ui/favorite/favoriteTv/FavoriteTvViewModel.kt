package com.example.moviecatalogue.view.ui.favorite.favoriteTv

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.data.entity.MovieEntity
import com.example.moviecatalogue.model.data.entity.TvEntity
import com.example.moviecatalogue.model.data.remote.repository.DataRepository

class FavoriteTvViewModel (private val mDataDataRepository: DataRepository): ViewModel() {
    fun getFavoriteTv() = mDataDataRepository.getTvFav()
    fun setFavoriteTv(tvEntity: TvEntity){
        val newState = !tvEntity.fav
        mDataDataRepository.setTvFav(tvEntity, newState)
    }
}