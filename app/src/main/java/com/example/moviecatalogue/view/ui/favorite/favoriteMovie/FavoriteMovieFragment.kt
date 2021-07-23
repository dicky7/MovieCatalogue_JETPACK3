package com.example.moviecatalogue.view.ui.favorite.favoriteMovie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.FragmentFavoriteMovieBinding
import com.example.moviecatalogue.view.activity.DetailActivity
import com.example.moviecatalogue.view.activity.DetailViewModel
import com.example.moviecatalogue.view.ui.movie.MovieAdapter
import com.example.moviecatalogue.view.ui.movie.MovieViewModel
import com.example.moviecatalogue.view.ui.viewModel.ViewModelFactory
import com.example.moviecatalogue.vo.Status
import com.google.android.material.snackbar.Snackbar

class FavoriteMovieFragment : Fragment(), FavoriteMovieAdapter.OnItemClickCallback {
    private lateinit var favoriteMovieBinding: FragmentFavoriteMovieBinding
    private lateinit var favoriteMovieAdapter: FavoriteMovieAdapter
    private lateinit var favoriteMovieViewModel: FavoriteMovieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        favoriteMovieBinding = FragmentFavoriteMovieBinding.inflate(layoutInflater,container,false)
        return favoriteMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(favoriteMovieBinding?.rvMoviesFavorite)
        if (activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            favoriteMovieViewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]
            favoriteMovieAdapter = FavoriteMovieAdapter()

            favoriteMovieAdapter = FavoriteMovieAdapter()
            favoriteMovieAdapter.setOnItemClickCallback(this)

            favoriteMovieViewModel.getFavoriteMovie().observe(viewLifecycleOwner, {
                if (it != null) {
                    favoriteMovieAdapter.submitList(it)


                }
            })
            recyclerViewer()
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val movieEntity = favoriteMovieAdapter.getSwipedData(swipedPosition)
                movieEntity?.let { favoriteMovieViewModel.setFavoriteMovie(it) }

                val snackBar = Snackbar.make(requireView(), "Undo", Snackbar.LENGTH_LONG)
                snackBar.setAction("Ok") { _ ->
                    movieEntity?.let { favoriteMovieViewModel.setFavoriteMovie(it) }
                }
                snackBar.show()
            }
        }
    })

    private fun recyclerViewer(){
        favoriteMovieBinding.rvMoviesFavorite.apply{
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = favoriteMovieAdapter
        }
    }

    override fun onItemClicked(id: String) {
        Intent(context, DetailActivity::class.java).also {
            it.putExtra(DetailActivity.EXTRA_MOVIE, id)
            it.putExtra(DetailActivity.EXTRA_DETAIL, DetailViewModel.MOVIE_DETAIL)
            context?.startActivity(it)
        }

    }
}