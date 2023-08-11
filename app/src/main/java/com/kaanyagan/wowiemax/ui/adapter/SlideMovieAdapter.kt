package com.kaanyagan.wowiemax.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kaanyagan.wowiemax.R
import com.kaanyagan.wowiemax.TimeConverter
import com.kaanyagan.wowiemax.data.entity.model.Movie
import com.kaanyagan.wowiemax.databinding.SlideMovieItemBinding

class SlideMovieAdapter(val context: Context, val movies:List<Movie>, val onClick:(movie: Movie)->Unit): RecyclerView.Adapter<SlideMovieAdapter.MyViewHolder>() {

    class MyViewHolder(binding: SlideMovieItemBinding):RecyclerView.ViewHolder(binding.root) {
        val ivPosterFront = binding.ivPosterFront
        val ivPosterBack = binding.ivPosterBack
        val tvMovieName = binding.tvMovieName
        val tvMovieDetails = binding.tvMovieDetails
        val ivStar = binding.ivStar
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

        val duration = TimeConverter.convertMinutesToTime(movie.duration.toInt())
        val ageLimit = movie.ageLimit
        val categories = movie.categories.joinToString( ", " )

        when(movie.numberStar)
        {
            1 -> holder.ivStar.setImageResource(R.drawable.star_1)
            2 -> holder.ivStar.setImageResource(R.drawable.star_2)
            3 -> holder.ivStar.setImageResource(R.drawable.star_3)
            4 -> holder.ivStar.setImageResource(R.drawable.star_4)
            5 -> holder.ivStar.setImageResource(R.drawable.star_5)
            else -> holder.ivStar.setImageResource(R.drawable.star_1)
        }
        holder.tvMovieDetails.text = "+${ageLimit} • ${duration} • ${categories}"

        holder.itemView.setOnClickListener{
            onClick(movie)
        }
    }
}