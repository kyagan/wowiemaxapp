package com.kaanyagan.wowiemax.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kaanyagan.wowiemax.R
import com.kaanyagan.wowiemax.data.entity.model.Movie
import com.kaanyagan.wowiemax.databinding.SlideMovieItemBinding

class SlideMovieAdapter(val context: Context, val movies:List<Movie>, val onClick:(movie: Movie)->Unit): RecyclerView.Adapter<SlideMovieAdapter.MyViewHolder>() {

    class MyViewHolder(binding: SlideMovieItemBinding):RecyclerView.ViewHolder(binding.root) {
        val ivPosterFront = binding.ivPosterFront
        val ivPosterBack = binding.ivPosterBack
        val tvMovieName = binding.tvMovieName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SlideMovieItemBinding.inflate(LayoutInflater.from(context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = movies[position]
        holder.ivPosterBack.load(movie.image)
        holder.ivPosterFront.load(movie.image)
        holder.tvMovieName.setText(movie.name)

        holder.itemView.setOnClickListener{
            onClick(movie)
        }
    }
}