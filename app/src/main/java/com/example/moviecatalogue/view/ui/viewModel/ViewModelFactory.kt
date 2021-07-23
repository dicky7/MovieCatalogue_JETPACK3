package com.example.moviecatalogue.view.ui.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.moviecatalogue.view.ui.tvShow.TvViewModel
import com.example.moviecatalogue.model.data.remote.repository.DataRepository
import com.example.moviecatalogue.view.activity.DetailViewModel
import com.example.moviecatalogue.view.ui.movie.MovieViewModel
import com.example.moviecatalogue.di.Injection
import com.example.moviecatalogue.view.ui.favorite.favoriteMovie.FavoriteMovieViewModel
import com.example.moviecatalogue.view.ui.favorite.favoriteTv.FavoriteTvViewModel

class ViewModelFactory private constructor(private val mDataDataRepository: DataRepository): ViewModelProvider.NewInstanceFactory() {
    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance (context: Context): ViewModelFactory =
            instance?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))

            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(TvViewModel::class.java)->{
                TvViewModel(mDataDataRepository) as T
            }
            modelClass.isAssignableFrom(MovieViewModel::class.java)->{
                MovieViewModel(mDataDataRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java)->{
                DetailViewModel(mDataDataRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java)->{
                FavoriteMovieViewModel(mDataDataRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteTvViewModel::class.java)->{
                FavoriteTvViewModel(mDataDataRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}