package com.example.moviecatalogue.view.ui.favorite.favoriteTv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalogue.model.data.entity.TvEntity
import com.example.moviecatalogue.model.data.remote.repository.DataRepository
import com.example.moviecatalogue.utils.DetailDataMov
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
class FavoriteTvViewModelTest{

    private lateinit var favoriteTvViewModel: FavoriteTvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TvEntity>>

    @Mock
    private lateinit var pagedListTv: PagedList<TvEntity>

    @Before
    fun setUp() {
        favoriteTvViewModel = FavoriteTvViewModel(dataRepository)
    }

    @Test
    fun getFavoriteTvModel(){
        val dataTv = pagedListTv
        Mockito.`when`(dataTv.size).thenReturn(5)
        val listTv = MutableLiveData<PagedList<TvEntity>>()
        listTv.value = dataTv

        Mockito.`when`(dataRepository.getTvFav()).thenReturn(listTv)
        val movie = favoriteTvViewModel.getFavoriteTv().value
        verify(dataRepository).getTvFav()
        assertNotNull(movie)
        assertEquals(5, movie?.size)

        favoriteTvViewModel.getFavoriteTv().observeForever(observer)
        verify(observer).onChanged(dataTv)
    }
    @Test
    fun setFavListTv() {
        favoriteTvViewModel.setFavoriteTv(DetailDataMov.getTvDetail())
        verify(dataRepository).setTvFav(DetailDataMov.getTvDetail(), true)
        verifyNoMoreInteractions(dataRepository)
    }

}