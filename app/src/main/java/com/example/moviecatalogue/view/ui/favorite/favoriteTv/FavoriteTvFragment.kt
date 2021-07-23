package com.example.moviecatalogue.view.ui.favorite.favoriteTv

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalogue.databinding.FragmentFavoriteMovieBinding
import com.example.moviecatalogue.databinding.FragmentFavoriteTvBinding
import com.example.moviecatalogue.view.activity.DetailActivity
import com.example.moviecatalogue.view.activity.DetailViewModel
import com.example.moviecatalogue.view.ui.favorite.favoriteMovie.FavoriteMovieAdapter
import com.example.moviecatalogue.view.ui.favorite.favoriteMovie.FavoriteMovieViewModel
import com.example.moviecatalogue.view.ui.viewModel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class FavoriteTvFragment : Fragment() , FavoriteTvAdapter.OnItemClickCallback {
    private lateinit var favoriteTvBinding: FragmentFavoriteTvBinding
    private lateinit var favoriteTvAdapter: FavoriteTvAdapter
    private lateinit var favoriteTvViewModel: FavoriteTvViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        favoriteTvBinding = FragmentFavoriteTvBinding.inflate(layoutInflater,container,false)
        return favoriteTvBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(favoriteTvBinding?.rvTvFavorite)
        if (activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            favoriteTvViewModel = ViewModelProvider(this, factory)[FavoriteTvViewModel::class.java]
            favoriteTvAdapter = FavoriteTvAdapter()

            favoriteTvAdapter = FavoriteTvAdapter()
            favoriteTvAdapter.setOnItemClickCallback(this)

            favoriteTvViewModel.getFavoriteTv().observe(viewLifecycleOwner, {
                if (it != null) {
                    favoriteTvAdapter.submitList(it)


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
                val tvEntity = favoriteTvAdapter.getSwipedData(swipedPosition)
                tvEntity?.let { favoriteTvViewModel.setFavoriteTv(it) }

                val snackBar = Snackbar.make(requireView(), "Undo", Snackbar.LENGTH_LONG)
                snackBar.setAction("Ok") { _ ->
                    tvEntity?.let { favoriteTvViewModel.setFavoriteTv(it) }
                }
                snackBar.show()
            }
        }
    })

    private fun recyclerViewer(){
        favoriteTvBinding.rvTvFavorite.apply{
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = favoriteTvAdapter
        }
    }

    override fun onItemClicked(id: String) {
        Intent(context, DetailActivity::class.java).also {
            it.putExtra(DetailActivity.EXTRA_MOVIE, id)
            it.putExtra(DetailActivity.EXTRA_DETAIL, DetailViewModel.TV_SHOW_DETAIL)
            context?.startActivity(it)
        }

    }



}