package com.kaanyagan.wowiemax.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kaanyagan.wowiemax.data.entity.model.Actor
import com.kaanyagan.wowiemax.databinding.ActorListItemBinding


class MovieActorAdapter(private val context: Context, private val actors: List<Actor>) :
    RecyclerView.Adapter<MovieActorAdapter.CustomViewHolder>() {

    class CustomViewHolder(binding: ActorListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivActor = binding.ivActor
        val tvActorName = binding.tvActorName
        val tvCharacterName = binding.tvCharacterName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieActorAdapter.CustomViewHolder {
        return CustomViewHolder(
            ActorListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieActorAdapter.CustomViewHolder, position: Int) {
        val actors = actors[position]
        holder.ivActor.load(actors.image)
        holder.tvActorName.text = actors.name
        holder.tvCharacterName.text = actors.movieIds[0].characterName

    }

    override fun getItemCount(): Int {
        return actors.size
    }
}