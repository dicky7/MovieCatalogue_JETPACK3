package com.example.moviecatalogue.model.data.remote.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviecatalogue.model.data.LocalDataSource
import com.example.moviecatalogue.model.data.NetworkBoundResource
import com.example.moviecatalogue.model.data.entity.TvEntity
import com.example.moviecatalogue.model.data.entity.MovieEntity
import com.example.moviecatalogue.model.data.remote.response.ApiResponse
import com.example.moviecatalogue.model.data.remote.source.RemoteDataSource
import com.example.moviecatalogue.model.data.remote.source.DataSource
import com.example.moviecatalogue.model.data.remote.response.movie.MovieRemote
import com.example.moviecatalogue.model.data.remote.response.movie.MovieDetailResponse
import com.example.moviecatalogue.model.data.remote.response.tv.TvRemote
import com.example.moviecatalogue.model.data.remote.response.tv.TvShowDetailResponse
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.vo.Resource

class DataRepository private constructor(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource, private val appExecutors: AppExecutors) : DataSource {
    companion object {
        @Volatile
        private var instance: DataRepository? = null
        fun getInstance(remoteData: RemoteDataSource,localDataSource: LocalDataSource, appExecutors: AppExecutors,): DataRepository =
            instance ?: synchronized(this) {
                instance ?: DataRepository(remoteData,localDataSource,appExecutors).apply { instance = this }
            }
    }

    override fun getMovie(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieRemote>>(appExecutors = appExecutors) {
            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun loadFromDb(): LiveData<PagedList<MovieEntity>> {
                val conf = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getDataMovie(), conf).build()
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieRemote>>> =
                remoteDataSource.getMovies()

            override fun saveCallResult(data: List<MovieRemote>) {
                val listMovie = ArrayList<MovieEntity>()
                for (movieData in data) {
                    movieData.apply {
                        val movie = MovieEntity(
                            id = id,
                            title = title,
                            overview = overview,
                            posterPath = posterPath,
                            date = date,
                            fav = false
                        )
                        listMovie.add(movie)
                    }
                }
                localDataSource.insertMovie(listMovie)
            }
        }.asLiveData()

    }

    override fun getMovieDetail(id: Int): LiveData<Resource<MovieEntity>> {
            return object : NetworkBoundResource<MovieEntity, MovieDetailResponse>(appExecutors) {
                override fun shouldFetch(data: MovieEntity?): Boolean = data == null
                override fun loadFromDb(): LiveData<MovieEntity> = localDataSource.getMovieById(id)
                override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> =
                    remoteDataSource.getDetailMovie(id.toString())

                override fun saveCallResult(movieDetail: MovieDetailResponse) {
                    movieDetail.apply {
                        val dataDetailMovie = MovieEntity(
                            id = id,
                            title = title,
                            date = date,
                            posterPath = posterPath,
                            overview = overview,
                            fav = false
                        )
                        localDataSource.updateFavMovie(dataDetailMovie, false)
                    }
                }
            }.asLiveData()
        }

    override fun getMovieFav(): LiveData<PagedList<MovieEntity>> {
        val conf = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavMovie(), conf).build()
    }

    override fun setMovieFav(movie: MovieEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.updateFavMovie(movie, state)
        }
    }

    override fun getTvShows():  LiveData<Resource<PagedList<TvEntity>>> {
        return object : NetworkBoundResource<PagedList<TvEntity>, List<TvRemote>>(appExecutors) {
            override fun shouldFetch(data: PagedList<TvEntity>?): Boolean = data == null || data.isEmpty()
            override fun loadFromDb(): LiveData<PagedList<TvEntity>> {
                val conf = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getDataTv(), conf).build()
            }

            override fun createCall(): LiveData<ApiResponse<List<TvRemote>>> =
                remoteDataSource.getTvShows()

            override fun saveCallResult(data: List<TvRemote>) {
                val listTv = ArrayList<TvEntity>()
                for (dataTv in data) {
                    dataTv.apply {
                        val tvShow = TvEntity(
                            id = id,
                            title = title,
                            date = date,
                            overview = overview,
                            posterPath = posterPath,
                            fav = false
                        )
                        listTv.add(tvShow)
                    }

                }
                localDataSource.insertTv(listTv)
            }
        }.asLiveData()
    }

    override fun getDetailTvShow(id: Int): LiveData<Resource<TvEntity>> {
        return object : NetworkBoundResource<TvEntity, TvShowDetailResponse>(appExecutors) {

            override fun shouldFetch(tvEntity: TvEntity?): Boolean = tvEntity == null

            override fun loadFromDb(): LiveData<TvEntity> = localDataSource.getTvById(id)

            override fun createCall(): LiveData<ApiResponse<TvShowDetailResponse>> =
                remoteDataSource.getDetailTvShow(id.toString())

            override fun saveCallResult(dataTv: TvShowDetailResponse) {
                dataTv.apply {
                    val dataDetailTv = TvEntity(
                        id = id,
                        title = name,
                        date = firstAirDate,
                        posterPath = posterPath,
                        overview = overview,
                        fav = false
                    )
                    localDataSource.updateFavTv(dataDetailTv, false)
                }
            }
        }.asLiveData()
    }
    override fun setTvFav(tvEntity: TvEntity, state: Boolean) =
        appExecutors.diskIO().execute {
            localDataSource.updateFavTv(tvEntity, state)
        }

    override fun getTvFav(): LiveData<PagedList<TvEntity>> {
        val conf = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavTv(), conf).build()
    }

}
