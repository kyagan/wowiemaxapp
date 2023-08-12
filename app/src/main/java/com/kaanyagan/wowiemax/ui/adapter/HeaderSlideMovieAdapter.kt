package com.kaanyagan.wowiemax.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kaanyagan.wowiemax.R
import com.kaanyagan.wowiemax.TimeConverter
import com.kaanyagan.wowiemax.data.entity.model.Movie
import com.kaanyagan.wowiemax.databinding.HeaderSlideListItemBinding


class HeaderSlideMovieAdapter(val context: Context, val movies:List<Movie>, val onClick:(movie: Movie)->Unit): RecyclerView.Adapter<HeaderSlideMovieAdapter.MyViewHolder>() {


    class MyViewHolder(binding: HeaderSlideListItemBinding): RecyclerView.ViewHolder(binding.root) {
        val ivPoster = binding.ivPoster
        val tvMovieName = binding.tvMovieName
        val tvMovieDetails = binding.tvMovieDetails


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            HeaderSlideListItemBinding.inflate(LayoutInflater.from(context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = movies[position]
        holder.ivPoster.load(movie.image)
        holder.tvMovieName.setText(movie.name)
        val duration = TimeConverter.convertMinutesToTime(movie.duration.toInt())
        holder.tvMovieDetails.text = "+${movie.ageLimit} • ${movie.categories.joinToString( ", " )} • ${duration} "

        holder.itemView.setOnClickListener{
            onClick(movie)
        }
    }
}