package com.example.moviecatalogue.view.ui.tvShow

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
import com.example.moviecatalogue.model.data.entity.TvEntity
import com.example.moviecatalogue.databinding.ListItemBinding
import com.example.moviecatalogue.model.data.entity.MovieEntity
import com.example.moviecatalogue.view.ui.movie.MovieAdapter


class TvShowAdapter: PagedListAdapter<TvEntity,TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvEntity>() {
            override fun areItemsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean =
                oldItem == newItem
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: TvShowAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val listItemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tv = getItem(position)
        if (tv != null) {
            holder.bind(tv)
        }
    }


    inner class TvShowViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvEntity: TvEntity) {
            with(binding) {
                tvEntity.apply {
                    itemView.apply {
                        listItemTitle.text = title
                        listItemRelease.text = date

                        Glide.with(context)
                            .load(BuildConfig.IMAGE_URL + posterPath)
                            .transform(RoundedCorners(20))
                            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                            .error(R.drawable.ic_error)
                            .into(listItemImg)
                        itemView.setOnClickListener { onItemClickCallback.onItemClicked(tvEntity.id.toString()) }
                    }
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }
}