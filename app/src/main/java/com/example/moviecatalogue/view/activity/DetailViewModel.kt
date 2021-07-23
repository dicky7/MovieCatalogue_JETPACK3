package com.example.moviecatalogue.view.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.data.entity.MovieEntity
import com.example.moviecatalogue.model.data.entity.TvEntity
import com.example.moviecatalogue.model.data.remote.repository.DataRepository
import com.example.moviecatalogue.vo.Resource


class DetailViewModel(private val mDataRepository: DataRepository): ViewModel() {
    companion object{
        const val TV_SHOW_DETAIL = "tvShowDetail"
        const val MOVIE_DETAIL = "movieDetail"
    }
    private lateinit var tvDetail: LiveData<Resource<TvEntity>>
    private lateinit var movieDetail: LiveData<Resource<MovieEntity>>

    fun setDataDetail(movieId: String, detailId: String){
        when(detailId){
            TV_SHOW_DETAIL ->{
                tvDetail = mDataRepository.getDetailTvShow(movieId.toInt())
            }
            MOVIE_DETAIL ->{
                movieDetail = mDataRepository.getMovieDetail(movieId.toInt())
            }
        }
    }

    fun setFavoriteMovie() {
        val resource = movieDetail.value
        if (resource?.data != null) {
            val newState = !resource.data.fav
            mDataRepository.setMovieFav(resource.data, newState)
        }
    }

    fun setFavoriteTv() {
        val resource = tvDetail.value
        if (resource?.data != null) {
            val newState = !resource.data.fav
            mDataRepository.setTvFav(resource.data, newState)
        }
    }

    fun getDetailTvShow() = tvDetail
    fun getDetailMovie() = movieDetail
}