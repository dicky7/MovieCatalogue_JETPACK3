package com.example.moviecatalogue.view.ui.favorite.favoriteTv

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
import com.example.moviecatalogue.model.data.entity.TvEntity
import com.example.moviecatalogue.view.ui.favorite.favoriteMovie.FavoriteMovieAdapter

class FavoriteTvAdapter : PagedListAdapter<TvEntity, FavoriteTvAdapter.FavoriteTvViewHolder>(DIFF_CALLBACK) {


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvEntity>() {
            override fun areItemsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem == newItem
            }
        }
        private val TAG = FavoriteMovieAdapter::class.java.simpleName
    }

    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: FavoriteTvAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun getSwipedData(swipedPosition: Int): TvEntity? = getItem(swipedPosition)

    inner class FavoriteTvViewHolder (private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(tvEntity: TvEntity){
            with(binding) {
                tvEntity.apply {
                    itemView.apply {
                        listItemTitle.text = title
                        listItemRelease.text = date
                        val idM = tvEntity.id
                        Log.d(TAG,idM.toString())

                        Glide.with(context)
                            .load(BuildConfig.IMAGE_URL + posterPath)
                            .transform(RoundedCorners(20))
                            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                            .error(R.drawable.ic_error)
                            .into(listItemImg)

                        setOnClickListener {
                            onItemClickCallback.onItemClicked(tvEntity.id.toString())
                            val id = onItemClickCallback.onItemClicked(tvEntity.id.toString())
                            Log.d(TAG,id.toString())
                        }
                    }
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTvViewHolder {
        val listItemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteTvViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: FavoriteTvViewHolder, position: Int) {
        val tv = getItem(position)
        if (tv != null) {
            holder.bind(tv)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }
}
