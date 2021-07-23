package com.example.moviecatalogue.view.ui.favorite.favoriteMovie

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.BuildConfig
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ListItemBinding
import com.example.moviecatalogue.model.data.entity.MovieEntity

class FavoriteMovieAdapter: PagedListAdapter<MovieEntity, FavoriteMovieAdapter.FavoriteMovieViewHolder>(DIFF_CALLBACK) {


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
        private val TAG = FavoriteMovieAdapter::class.java.simpleName
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: FavoriteMovieAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun getSwipedData(swipedPosition: Int): MovieEntity? = getItem(swipedPosition)

    inner class FavoriteMovieViewHolder (private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movieEntity: MovieEntity){
            with(binding) {
                movieEntity.apply {
                    itemView.apply {
                        listItemTitle.text = title
                        listItemRelease.text = date
                        val idM = movieEntity.id
                        Log.d(TAG,idM.toString())

                        Glide.with(context)
                            .load(BuildConfig.IMAGE_URL + posterPath)
                            .transform(RoundedCorners(20))
                            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                            .error(R.drawable.ic_error)
                            .into(listItemImg)

                        setOnClickListener {
                            onItemClickCallback.onItemClicked(movieEntity.id.toString())
                            val id = onItemClickCallback.onItemClicked(movieEntity.id.toString())
                            Log.d(TAG,id.toString())
                        }
                    }
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieViewHolder {
        val listItemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteMovieViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: FavoriteMovieViewHolder, position: Int) {
        val movies = getItem(position)
        if (movies != null) {
            holder.bind(movies)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }
}