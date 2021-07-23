package com.example.moviecatalogue.view.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalogue.model.data.entity.MovieEntity
import com.example.moviecatalogue.model.data.remote.repository.DataRepository
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
class MovieViewModelTest{
    private lateinit var movieViewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var pagedListMovie: PagedList<MovieEntity>

    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(dataRepository)
    }

    @Test
    fun getMovieModel(){
        val dataMove = Resource.success(pagedListMovie)
        Mockito.`when`(dataMove.data?.size).thenReturn(5)
        val listMovie = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        listMovie.value = dataMove

        Mockito.`when`(dataRepository.getMovie()).thenReturn(listMovie)
        val movie = movieViewModel.getMovieModel().value?.data
        verify(dataRepository).getMovie()
        assertNotNull(movie)
        assertEquals(5, movie?.size)

        movieViewModel.getMovieModel().observeForever(observer)
        verify(observer).onChanged(dataMove)
    }

}