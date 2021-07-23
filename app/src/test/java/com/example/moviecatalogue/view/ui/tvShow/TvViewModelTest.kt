package com.example.moviecatalogue.view.ui.tvShow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalogue.model.data.entity.MovieEntity
import com.example.moviecatalogue.model.data.entity.TvEntity
import com.example.moviecatalogue.model.data.remote.repository.DataRepository
import com.example.moviecatalogue.utils.DataMovie
import com.example.moviecatalogue.view.ui.movie.MovieViewModel
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
class TvViewModelTest{
    private lateinit var tvViewModel: TvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvEntity>>>

    @Mock
    private lateinit var pagedListTv: PagedList<TvEntity>

    @Before
    fun setUp() {
        tvViewModel = TvViewModel(dataRepository)
    }

    @Test
    fun getTvModel(){
        val dataTv = Resource.success(pagedListTv)
        Mockito.`when`(dataTv.data?.size).thenReturn(5)
        val listTv = MutableLiveData<Resource<PagedList<TvEntity>>>()
        listTv.value = dataTv

        Mockito.`when`(dataRepository.getTvShows()).thenReturn(listTv)
        val tvs = tvViewModel.getTvModel().value?.data
        verify(dataRepository).getTvShows()
        assertNotNull(tvs)
        assertEquals(5, tvs?.size)

        tvViewModel.getTvModel().observeForever(observer)
        verify(observer).onChanged(dataTv)
    }

}