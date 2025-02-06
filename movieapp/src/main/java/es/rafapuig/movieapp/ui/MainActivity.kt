package es.rafapuig.movieapp.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import es.rafapuig.movieapp.R
import es.rafapuig.movieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel : MovieViewModel by viewModels { MovieViewModel.Factory }

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val movieAdapter by lazy { MovieAdapter() }

    private val movieListAdapter by lazy { MovieListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //binding.movieList.adapter = movieAdapter
        binding.movieList.adapter = movieListAdapter

        viewModel.fetchMovies()

        viewModel.movies.observe(this) { movies ->
            //movieAdapter.addMovies(movies)
            movieListAdapter.submitList(movies)
        }

        viewModel.loading.observe(this) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }

    }
}