package com.example.moviecatalogue.view.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviecatalogue.R
import com.example.moviecatalogue.adapter.FavoriteActionPager
import com.example.moviecatalogue.databinding.ActivityFavoriteBinding
import com.example.moviecatalogue.databinding.ActivityMainBinding
import com.example.moviecatalogue.view.ui.favorite.favoriteMovie.FavoriteMovieFragment
import com.example.moviecatalogue.view.ui.favorite.favoriteTv.FavoriteTvFragment
import com.example.moviecatalogue.view.ui.movie.MovieFragment
import com.example.moviecatalogue.view.ui.tvShow.TvShowFragment
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteActivity : AppCompatActivity() {
    private lateinit var favoriteActivityMainBinding: ActivityFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoriteActivityMainBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(favoriteActivityMainBinding.root)

        val listFragment = listOf(FavoriteMovieFragment(), FavoriteTvFragment())
        val tittletabs = listOf("Movie", "Tv Show")

        favoriteActivityMainBinding.favoriteViewPager.adapter = FavoriteActionPager(listFragment,this.supportFragmentManager, lifecycle)
        TabLayoutMediator(favoriteActivityMainBinding.favortieTabs,favoriteActivityMainBinding.favoriteViewPager){ tablay,position->
            tablay.text = tittletabs[position]
        }.attach()

    }
}