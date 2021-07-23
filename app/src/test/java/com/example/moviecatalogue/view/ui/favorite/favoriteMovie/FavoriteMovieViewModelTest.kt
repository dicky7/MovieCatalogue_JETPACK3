package com.example.moviecatalogue.view.ui.favorite.favoriteMovie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalogue.model.data.entity.MovieEntity
import com.example.moviecatalogue.model.data.remote.repository.DataRepository
import com.example.moviecatalogue.utils.DataMovie
import com.example.moviecatalogue.utils.DetailDataMov
import com.example.moviecatalogue.view.ui.movie.MovieViewModel
import com.example.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieViewModelTest{
    private lateinit var favoriteMovieViewModel: FavoriteMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedListMovie: PagedList<MovieEntity>

    @Before
    fun setUp() {
        favoriteMovieViewModel = FavoriteMovieViewModel(dataRepository)
    }

    @Test
    fun getFavoriteMovieModel(){
        val dataMove = pagedListMovie
        Mockito.`when`(dataMove.size).thenReturn(5)
        val listMovie = MutableLiveData<PagedList<MovieEntity>>()
        listMovie.value = dataMove

        Mockito.`when`(dataRepository.getMovieFav()).thenReturn(listMovie)
        val movie = favoriteMovieViewModel.getFavoriteMovie().value
        verify(dataRepository).getMovieFav()
        assertNotNull(movie)
        assertEquals(5, movie?.size)

        favoriteMovieViewModel.getFavoriteMovie().observeForever(observer)
        verify(observer).onChanged(dataMove)
    }
    @Test
    fun setFavListMovies() {
        favoriteMovieViewModel.setFavoriteMovie(DetailDataMov.getMovieDetail())
        verify(dataRepository).setMovieFav(DetailDataMov.getMovieDetail(), true)
        verifyNoMoreInteractions(dataRepository)
    }

}