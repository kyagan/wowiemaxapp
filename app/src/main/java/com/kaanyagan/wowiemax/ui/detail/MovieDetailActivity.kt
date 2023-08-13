package com.kaanyagan.wowiemax.ui.detail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.kaanyagan.wowiemax.R
import com.kaanyagan.wowiemax.data.Database
import com.kaanyagan.wowiemax.data.entity.model.Actor
import com.kaanyagan.wowiemax.data.entity.model.Movie
import com.kaanyagan.wowiemax.data.entity.state.MovieActorState
import com.kaanyagan.wowiemax.databinding.ActivityMovieDetailBinding
import com.kaanyagan.wowiemax.ui.adapter.MovieActorAdapter
import com.kaanyagan.wowiemax.ui.main.MainActivity
import com.kaanyagan.wowiemax.ui.player.PlayerActivity
import kotlinx.coroutines.launch

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private val viewModel: MovieActorViewModel by viewModels()
    private lateinit var actors: List<Actor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeMovieActorState()
        listeners()

        val movie = intent.getParcelableExtra<Movie>(MainActivity.MOVIE)

        movie?.let {
           binding.ivMovie.load(it.image)
            binding.tvCategory.text = "${movie.categories.joinToString( ", " )}"
            if (it.isNegativeExample) binding.ivNegativeExample.isVisible = true
            if (it.isViolence) binding.ivViolence.isVisible = true
            actors = Database.actors.filter {
                it.movieIds.filter { it.movieId == movie.id }.isNotEmpty()
            }
            binding.movie = movie
            binding.context = this@MovieDetailActivity
        }
    }

    private fun listeners() {
        binding.ivReturnBack.setOnClickListener {
            finish()
        }
        binding.ivPlay.setOnClickListener {
            startActivity(
                Intent(this,PlayerActivity::class.java)
            )
        }
        binding.ivShare.setOnClickListener {
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                val message = "${binding.tvMovieName.text} ${getString(R.string.share_message)}"
                shareIntent.putExtra(Intent.EXTRA_TEXT, message)
                startActivity(shareIntent)

            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "application was not found in this device", Toast.LENGTH_SHORT)
                    .show()
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=com.findwordgame.app")
                    )

                )
            }
        }
    }

    private fun observeMovieActorState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.movieActorState.collect {
                    when (it) {
                        MovieActorState.Idle -> {}
                        MovieActorState.Loading -> {
                            binding.pbActor.isVisible = true
                            binding.rvActor.isVisible = false
                        }
                        is MovieActorState.Result -> {
                            binding.pbActor.isVisible = false
                            binding.rvActor.isVisible = true

                            binding.rvActor.adapter =
                                MovieActorAdapter(this@MovieDetailActivity, actors)
                        }

                        is MovieActorState.Error -> {}
                    }
                }
            }
        }
    }
}