package com.example.moviecatalogue.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviecatalogue.R
import com.example.moviecatalogue.adapter.SectionPagerAdapter
import com.example.moviecatalogue.databinding.ActivityMainBinding
import com.example.moviecatalogue.view.ui.favorite.FavoriteActivity
import com.example.moviecatalogue.view.ui.movie.MovieFragment

import com.example.moviecatalogue.view.ui.tvShow.TvShowFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabFav.setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
        }

        val listFragment = listOf(MovieFragment(), TvShowFragment())
        val tittletabs = listOf("Movie", "Tv Show")

        binding.viewPager.adapter = SectionPagerAdapter(listFragment,this.supportFragmentManager, lifecycle)
        TabLayoutMediator(binding.tabs,binding.viewPager){ tablay,position->
            tablay.text = tittletabs[position]
        }.attach()


    }

}