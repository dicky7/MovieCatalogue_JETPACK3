package com.example.moviecatalogue.view.ui.movie

import android.content.Intent
import android.graphics.Movie
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.databinding.FragmentMovieBinding
import com.example.moviecatalogue.view.activity.DetailActivity
import com.example.moviecatalogue.view.activity.DetailViewModel.Companion.MOVIE_DETAIL
import com.example.moviecatalogue.view.ui.viewModel.ViewModelFactory
import com.example.moviecatalogue.vo.Status

class MovieFragment : Fragment(), MovieAdapter.OnItemClickCallback {
    private lateinit var movieBinding: FragmentMovieBinding
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        movieBinding = FragmentMovieBinding.inflate(layoutInflater,container,false)
        return movieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){

            val factory = ViewModelFactory.getInstance(requireActivity())
            movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            movieAdapter = MovieAdapter()

            movieViewModel.getMovieModel().observe(viewLifecycleOwner, { movie ->
                if (movie != null) {
                    when (movie.status) {
                        Status.LOADING -> true.progressBar()
                        Status.SUCCESS -> {
                            false.progressBar()
                            with(movieAdapter) {
                                submitList(movie.data)
                                movieAdapter.setOnItemClickCallback(this@MovieFragment)
                                movieAdapter.notifyDataSetChanged()
                            }
                        }
                        Status.ERROR -> {
                            false.progressBar()
                            Toast.makeText(context, "Data gagal dimuat", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })
            recyclerViewer()
        }
    }

    private fun recyclerViewer(){
        movieBinding.rvMovies.apply{
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = movieAdapter
        }
    }

    override fun onItemClicked(id: String) {
        Intent(context, DetailActivity::class.java).also {
            it.putExtra(DetailActivity.EXTRA_MOVIE, id)
            it.putExtra(DetailActivity.EXTRA_DETAIL, MOVIE_DETAIL)
            context?.startActivity(it)
        }

    }
    private fun Boolean.progressBar() {
        movieBinding.progressBar.visibility = if (this) View.VISIBLE else View.GONE
    }
}