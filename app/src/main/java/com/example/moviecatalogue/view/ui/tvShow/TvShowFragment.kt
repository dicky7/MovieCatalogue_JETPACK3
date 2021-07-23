package com.example.moviecatalogue.view.ui.tvShow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.view.activity.DetailActivity
import com.example.moviecatalogue.databinding.FragmentTvShowBinding
import com.example.moviecatalogue.view.activity.DetailViewModel
import com.example.moviecatalogue.view.activity.DetailViewModel.Companion.TV_SHOW_DETAIL
import com.example.moviecatalogue.view.ui.movie.MovieAdapter
import com.example.moviecatalogue.view.ui.movie.MovieViewModel
import com.example.moviecatalogue.view.ui.viewModel.ViewModelFactory
import com.example.moviecatalogue.vo.Status


class TvShowFragment : Fragment(), TvShowAdapter.OnItemClickCallback {

    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding
    private lateinit var tvViewModel: TvViewModel
    private lateinit var tvAdapter: TvShowAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            tvViewModel = ViewModelProvider(this, factory)[TvViewModel::class.java]
            tvAdapter = TvShowAdapter()

            tvViewModel.getTvModel().observe(viewLifecycleOwner, {tvShows ->
                if (tvShows != null) {
                    when (tvShows.status) {
                        Status.LOADING -> true.progressBar()
                        Status.SUCCESS -> {
                            false.progressBar()
                            with(tvAdapter) {
                                submitList(tvShows.data)
                                tvAdapter.setOnItemClickCallback(this@TvShowFragment)
                                tvAdapter.notifyDataSetChanged()
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

    override fun onItemClicked(id: String) {
        Intent(context, DetailActivity::class.java).also {
            it.putExtra(DetailActivity.EXTRA_MOVIE, id)
            it.putExtra(DetailActivity.EXTRA_DETAIL, TV_SHOW_DETAIL)

            context?.startActivity(it)
        }
    }

    private fun Boolean.progressBar() {
        fragmentTvShowBinding.progressBar.visibility = if (this) View.VISIBLE else View.GONE
    }

    private fun recyclerViewer(){
        fragmentTvShowBinding.rvTvShow.apply{
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = tvAdapter
        }
    }

}