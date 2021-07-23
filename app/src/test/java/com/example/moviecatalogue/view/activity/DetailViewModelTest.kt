package com.example.moviecatalogue.view.activity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.model.data.entity.DetailEntity
import com.example.moviecatalogue.model.data.entity.MovieEntity
import com.example.moviecatalogue.model.data.entity.TvEntity
import com.example.moviecatalogue.model.data.remote.repository.DataRepository
import com.example.moviecatalogue.utils.DetailDataMov
import com.example.moviecatalogue.view.activity.DetailViewModel.Companion.MOVIE_DETAIL
import com.example.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest{
    private lateinit var detailViewModel: DetailViewModel

    private val dataMovie = DetailDataMov.getMovieDetail()
    private val dataMovieId = dataMovie.id

    private val dataTv = DetailDataMov.getTvDetail()
    private val dataTvId = dataTv.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvObserver: Observer<Resource<TvEntity>>

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel(dataRepository)
    }

    @Test
    fun getMovieDetails(){

        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = Resource.success(DetailDataMov.getMovieDetail())


        Mockito.`when`(dataRepository.getMovieDetail(dataMovieId)).thenReturn(movie)
        detailViewModel.setDataDetail(dataMovieId.toString(),MOVIE_DETAIL)


        detailViewModel.getDetailMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(movie.value)
    }

    @Test
    fun getTvDetails(){
        val tvDetailEntity = MutableLiveData<Resource<TvEntity>>()
        tvDetailEntity.value = Resource.success(DetailDataMov.getTvDetail())

        Mockito.`when`(dataRepository.getDetailTvShow(dataTvId)).thenReturn(tvDetailEntity)
        detailViewModel.setDataDetail(dataTvId.toString(), DetailViewModel.TV_SHOW_DETAIL)

        detailViewModel.getDetailTvShow().observeForever(tvObserver)
        verify(tvObserver).onChanged(tvDetailEntity.value)
    }

}