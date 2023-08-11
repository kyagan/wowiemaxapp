package com.kaanyagan.wowiemax.ui.slideMovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.kaanyagan.wowiemax.data.entity.state.SlideMovieState
import com.kaanyagan.wowiemax.databinding.ActivitySlideMovieBinding
import com.kaanyagan.wowiemax.ui.adapter.SlideMovieAdapter
import kotlinx.coroutines.launch

class SlideMovieActivity : AppCompatActivity() {
    lateinit var binding:ActivitySlideMovieBinding
    private val viewModel:SlideMovieViewModel by viewModels()
    lateinit var adapter: SlideMovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySlideMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getSlideMovie()
        observeSlideMovieState()

    }

    private fun observeSlideMovieState() {
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.slideMovieState.collect{
                    when(it){
                        is SlideMovieState.Idle->{}
                        is SlideMovieState.Loading->{}
                        is SlideMovieState.Success->{
                            adapter = SlideMovieAdapter(this@SlideMovieActivity, it.movies){
                                binding.vpMovie.adapter = adapter
                                binding.vpMovie.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                                binding.indicator.setViewPager(binding.vpMovie)
                            }

                        }
                        is SlideMovieState.Error->{}
                        else -> {}
                    }
                }
            }
        }
    }
}