package com.kaanyagan.wowiemax.ui.search

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kaanyagan.wowiemax.R
import com.kaanyagan.wowiemax.data.Database
import com.kaanyagan.wowiemax.data.entity.state.SearchMovieListState
import com.kaanyagan.wowiemax.databinding.ActivitySearchBinding
import com.kaanyagan.wowiemax.showAlert
import com.kaanyagan.wowiemax.ui.adapter.MovieListAdapter
import com.kaanyagan.wowiemax.ui.detail.MovieDetailActivity
import com.kaanyagan.wowiemax.ui.main.MainActivity
import kotlinx.coroutines.launch

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private val viewModel:SearchViewModel by viewModels()
    lateinit var adapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listeners()
        observeMovieList()
    }

    private fun listeners(){
        binding.ivBackArrow.setOnClickListener{
            finish()
        }
        binding.ivCleanSearch.setOnClickListener{
            binding.etSearch.text.clear()
            binding.emptyAnimation.isVisible = false
            binding.rvMovies.isVisible = true
        }
        binding.ivCloseKeyboard.setOnClickListener{
            var imm: InputMethodManager =  getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.ivCloseKeyboard.windowToken, 0)
        }
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                println("beforeTextChanged")
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(!s.isNullOrEmpty()){
                    val filteredList = Database.movies.filter {
                        getString(it.name).contains(s.toString(), ignoreCase = true)
                    }
                    if (filteredList.isNullOrEmpty()){
                        binding.emptyAnimation.isVisible = true
                        binding.rvMovies.isVisible = false
                    }
                    else{
                        binding.emptyAnimation.isVisible = false
                        binding.rvMovies.isVisible = true
                    }
                    adapter.updateList(filteredList)
                }else {
                    adapter.updateList(Database.movies)
                }
                adapter.notifyDataSetChanged()
            }

            override fun afterTextChanged(s: Editable?) {
                println("afterTextChanged")
            }
        })
    }
    private fun observeMovieList(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.searchMovieListState.collect{
                    when(it){
                        SearchMovieListState.Idle->{}

                        SearchMovieListState.Loading->{
                            binding.progressBar.isVisible =true
                            binding.rvMovies.isVisible=false
                        }
                        is SearchMovieListState.Result->{
                            binding.progressBar.isVisible=false
                            binding.rvMovies.isVisible =true
                            adapter = MovieListAdapter(this@SearchActivity,Database.movies){
                                val intent = Intent(this@SearchActivity,MovieDetailActivity::class.java)
                                intent.putExtra(MainActivity.MOVIE, it)
                                startActivity(intent)
                            }
                            binding.rvMovies.adapter=adapter
                        }
                        is SearchMovieListState.Error->{
                            binding.rvMovies.isVisible=false
                        }
                    }
                }
            }
        }
    }
}