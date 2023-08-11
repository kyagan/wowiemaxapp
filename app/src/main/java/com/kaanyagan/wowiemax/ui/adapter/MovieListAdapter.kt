package com.example.movieproject

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kaanyagan.wowiemax.databinding.MovieListItemBinding
import com.kaanyagan.wowiemax.data.entity.model.Movie


class MovieListAdapter(val context: Context, val movies:List<Movie>, val onClick:(movie: Movie)->Unit):
    RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    class MovieViewHolder(binding: MovieListItemBinding): RecyclerView.ViewHolder(binding.root){
        val iwPoster = binding.iwPoster
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieListAdapter.MovieViewHolder {
        return MovieViewHolder(
            MovieListItemBinding.inflate(LayoutInflater.from(context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: MovieListAdapter.MovieViewHolder, position: Int) {

        val movie = movies[position]

        holder.iwPoster.load(movie.image)

        holder.itemView.setOnClickListener{
            onClick(movie)
        }

    }

    override fun getItemCount(): Int {
        return movies.size
    }



}