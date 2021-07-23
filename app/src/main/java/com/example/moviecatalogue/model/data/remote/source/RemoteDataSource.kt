package com.example.moviecatalogue.model.data.remote.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.BuildConfig.API_KEY
import com.example.moviecatalogue.model.data.remote.response.movie.MovieRemote
import com.example.moviecatalogue.model.data.remote.response.movie.MovieDetailResponse
import com.example.moviecatalogue.model.data.remote.response.movie.MoviesResponse
import com.example.moviecatalogue.model.data.remote.response.tv.TvRemote
import com.example.moviecatalogue.model.data.remote.response.tv.TvShowDetailResponse
import com.example.moviecatalogue.model.data.remote.response.tv.TvShowResponse
import com.example.moviecatalogue.model.api.RetrofitClient
import com.example.moviecatalogue.model.data.remote.response.ApiResponse
import com.example.moviecatalogue.utils.EspressoIdlingResource

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    companion object {
        const val TAG = "Remote Data Source"

        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(): RemoteDataSource = instance ?: synchronized(this) {
            RemoteDataSource().apply { instance = this }
        }
    }

    fun getMovies(): LiveData<ApiResponse<List<MovieRemote>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<MovieRemote>>>()
        RetrofitClient.getApiService().getListMovie(API_KEY)
            .enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                EspressoIdlingResource.decrement()
                result.value = ApiResponse.success(response.body()?.results as List<MovieRemote>)
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
                Log.e(TAG, "Failure ${t.message}")

            }
        })
        return result
    }

    fun getDetailMovie(id: String): LiveData<ApiResponse<MovieDetailResponse>>  {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<MovieDetailResponse>>()
        RetrofitClient.getApiService().getMovieDetail(id, API_KEY)
            .enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(call: Call<MovieDetailResponse>, response: Response<MovieDetailResponse>) {
                EspressoIdlingResource.decrement()
                result.value = ApiResponse.success(response.body() as MovieDetailResponse)

            }
            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
                Log.e(TAG, "getDetailMovies onFailure : ${t.message}")
            }
        })
        return result
    }

    fun getTvShows(): LiveData<ApiResponse<List<TvRemote>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<TvRemote>>>()
        RetrofitClient.getApiService().getListTv(API_KEY)
            .enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                EspressoIdlingResource.decrement()
                result.value = ApiResponse.success(response.body()?.results as List<TvRemote>)


            }
            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
                Log.e(TAG, "Failure ${t.message}")
            }
        })
        return result
    }

    fun getDetailTvShow(id: String): LiveData<ApiResponse<TvShowDetailResponse>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<TvShowDetailResponse>>()
        RetrofitClient.getApiService().getTvDetail(id, API_KEY)
            .enqueue(object : Callback<TvShowDetailResponse> {
            override fun onResponse(call: Call<TvShowDetailResponse>, response: Response<TvShowDetailResponse>) {
                EspressoIdlingResource.decrement()
                result.value = ApiResponse.success(response.body() as TvShowDetailResponse)
            }
            override fun onFailure(call: Call<TvShowDetailResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
                Log.e(TAG, "getDetailsTVShows onFailure : ${t.message}")
            }
        })
        return result
    }

}