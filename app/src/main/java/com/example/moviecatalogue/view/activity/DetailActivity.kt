package com.example.moviecatalogue.view.activity

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviecatalogue.BuildConfig.IMAGE_URL
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ActivityDetailBinding
import com.example.moviecatalogue.model.data.entity.MovieEntity
import com.example.moviecatalogue.model.data.entity.TvEntity
import com.example.moviecatalogue.view.activity.DetailViewModel.Companion.MOVIE_DETAIL
import com.example.moviecatalogue.view.activity.DetailViewModel.Companion.TV_SHOW_DETAIL
import com.example.moviecatalogue.view.ui.viewModel.ViewModelFactory
import com.example.moviecatalogue.vo.Status

class DetailActivity : AppCompatActivity() , View.OnClickListener {
    companion object{
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_DETAIL = "extra_detail"
        private val TAG = DetailActivity::class.java.simpleName
    }
    private lateinit var detailBinding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private var detailId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        supportActionBar?.hide()
        detailBinding.tolbar.setNavigationOnClickListener { onBackPressed() }

        true.progressBar()

        val factor = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factor)[DetailViewModel::class.java]

        detailBinding.btnAddFavorite.setOnClickListener(this)
        val extras = intent.extras
        if (extras != null){
            val movieId = extras.getString(EXTRA_MOVIE)
            detailId = extras.getString(EXTRA_DETAIL)
            Log.d(TAG, movieId.toString())
            Log.d(TAG, detailId.toString())

            if (movieId != null && detailId != null){
                detailViewModel.setDataDetail(movieId, detailId.toString())
                    if (detailId == MOVIE_DETAIL){
                        movie()
                    }
                    else if(detailId == TV_SHOW_DETAIL){
                        tv()

                    }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_add_favorite -> {
                setFav()
            }
        }
    }

    private fun movie(){
        detailViewModel.getDetailMovie().observe(this,{
            when(it.status){
                Status.LOADING -> true.progressBar()
                Status.SUCCESS -> {
                    if (it.data != null) {
                        false.progressBar()
                        movieDetail(it.data)

                        val state = it.data.fav
                        setFavoriteState(state)
                        Log.d(TAG, state.toString())
                    }
                }
                Status.ERROR -> {
                    false.progressBar()
                    Toast.makeText(applicationContext, "Movie Detail Gagal diMuat", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun movieDetail(movie: MovieEntity) {
        detailBinding.apply {
            with(movie) {
                titleDetail.text = this.title
                releaseDetail.text = this.date
                overviewDetail.text = this.overview

                Glide.with(this@DetailActivity)
                    .load(IMAGE_URL + this.posterPath)
                    .into(detailBinding.imgDetail)
                false.progressBar()
            }
        }
    }

    private fun tv(){
        detailViewModel.getDetailTvShow().observe(this,{
            when(it.status){
                Status.LOADING -> true.progressBar()
                Status.SUCCESS -> {
                    if (it.data != null) {
                        false.progressBar()
                        tvDetail(it.data)

                        val state = it.data.fav
                        setFavoriteState(state)
                        Log.d(TAG, state.toString())
                    }
                }
                Status.ERROR -> {
                    false.progressBar()
                    Toast.makeText(applicationContext, "Tv Detail Gagal diMuat", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun tvDetail(tv: TvEntity) {
        detailBinding.apply {
            with(tv) {
                titleDetail.text = this.title
                releaseDetail.text = this.date
                overviewDetail.text = this.overview

                Glide.with(this@DetailActivity)
                    .load(IMAGE_URL + this.posterPath)
                    .into(detailBinding.imgDetail)
                false.progressBar()
            }
        }
    }

    private fun setFav() {
        if (detailId == MOVIE_DETAIL) {
            detailViewModel.setFavoriteMovie()
        }
        else if (detailId == TV_SHOW_DETAIL) {
            detailViewModel.setFavoriteTv()
        }
    }

    private fun setFavoriteState(state: Boolean) {
        if (state) {
            detailBinding.btnAddFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24))
        } else {
            detailBinding.btnAddFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24))
        }
    }

    private fun Boolean.progressBar() {
        detailBinding.progressBar.visibility = if (this) View.VISIBLE else View.GONE
    }
}