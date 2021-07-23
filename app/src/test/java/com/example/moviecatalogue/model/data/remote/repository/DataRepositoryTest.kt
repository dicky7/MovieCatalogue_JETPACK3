package com.example.moviecatalogue.model.data.remote.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.model.data.LocalDataSource
import com.example.moviecatalogue.model.data.entity.MovieEntity
import com.example.moviecatalogue.model.data.entity.TvEntity
import com.example.moviecatalogue.model.data.remote.source.RemoteDataSource
import com.example.moviecatalogue.utils.*
import com.example.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class DataRepositoryTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)

    private val dataRepository = FakeDataRepository(remote,local,appExecutors)

    private val movieResponse = DataMovie.getMovieRemote()
    private val movieId = movieResponse[0].id
    private val movieDetail = DetailDataMov.getMovieDetailRemote()

    private val tvShowResponse = DataMovie.getTvRemote()
    private val tvId = tvShowResponse[0].id
    private val tvDetail = DetailDataMov.getTvDetailRemote()

    @Test
    fun getAllMovie() {
        val sourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getDataMovie()).thenReturn(sourceFactory)
        dataRepository.getMovie()

        val movieEntity = Resource.success(PagedListUtilTest.mockPagedList(DataMovie.generateDataMovie()))
        verify(local).getDataMovie()
        assertNotNull(movieEntity.data)
        assertEquals(movieResponse.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getAllMovieDetail() {
        val sourceFactory = MutableLiveData<MovieEntity>()
        sourceFactory.value = DetailDataMov.getMovieDetail()
        Mockito.`when`(local.getMovieById(movieId)).thenReturn(sourceFactory)

        val movieDetailEntity = LiveDataTestUtil.getValue(dataRepository.getMovieDetail(movieId))
        verify(local).getMovieById(movieId)
        assertNotNull(movieDetailEntity)
        assertEquals(movieDetail.id, movieDetailEntity.data?.id)
    }

    @Test
    fun setMovieFavorite() {
        dataRepository.setMovieFav(DetailDataMov.getMovieDetail(), true)
        verify(local).updateFavMovie(DetailDataMov.getMovieDetail(), true)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun getMovieFavorite() {
        val sourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getFavMovie()).thenReturn(sourceFactory)
        dataRepository.getMovieFav()

        val movieEntity = Resource.success(PagedListUtilTest.mockPagedList(DataMovie.generateDataMovie()))
        verify(local).getFavMovie()
        assertNotNull(movieEntity)
        assertEquals(movieResponse.size, movieEntity.data?.size)
    }

    @Test
    fun getAllTv() {
        val sourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        Mockito.`when`(local.getDataTv()).thenReturn(sourceFactory)
        dataRepository.getTvShows()

        val tvEntity = Resource.success(PagedListUtilTest.mockPagedList(DataMovie.generateDataTv()))
        verify(local).getDataTv()
        assertNotNull(tvEntity.data)
        assertEquals(tvShowResponse.size.toLong(), tvEntity.data?.size?.toLong())
    }



    @Test
    fun getAllTvDetail() {
        val sourceFactory = MutableLiveData<TvEntity>()
        sourceFactory.value = DetailDataMov.getTvDetail()
        Mockito.`when`(local.getTvById(tvId)).thenReturn(sourceFactory)

        val tvDetailEntity = LiveDataTestUtil.getValue(dataRepository.getDetailTvShow(tvId))
        verify(local).getTvById(tvId)
        assertNotNull(tvDetailEntity)
        assertEquals(tvDetail.id, tvDetailEntity.data?.id)
    }

    @Test
    fun setTvFavorite() {
        dataRepository.setTvFav(DetailDataMov.getTvDetail(), true)
        verify(local).updateFavTv(DetailDataMov.getTvDetail(), true)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun getTvFavorite() {
        val sourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        Mockito.`when`(local.getFavTv()).thenReturn(sourceFactory)
        dataRepository.getTvFav()

        val tvEntity = Resource.success(PagedListUtilTest.mockPagedList(DataMovie.generateDataTv()))
        verify(local).getFavTv()
        assertNotNull(tvEntity)
        assertEquals(tvShowResponse.size, tvEntity.data?.size)
    }

}