package com.kaanyagan.wowiemax.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kaanyagan.wowiemax.data.entity.model.Movie
import com.kaanyagan.wowiemax.databinding.SearchMovieListItemBinding

class SearchMovieListAdapter(private val context: Context, private var movies: List<Movie>) :
    RecyclerView.Adapter<SearchMovieListAdapter.CustomViewHolder>() {

    class CustomViewHolder(binding: SearchMovieListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivMovie = binding.ivMovie

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchMovieListAdapter.CustomViewHolder {
        val binding = SearchMovieListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchMovieListAdapter.CustomViewHolder, position: Int) {
        val movies = movies[position]

        holder.ivMovie.load(movies.image)
    }

    fun updateList(filteredList: List<Movie>) {
        movies = filteredList
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}