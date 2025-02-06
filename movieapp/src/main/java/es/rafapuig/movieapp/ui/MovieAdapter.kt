package es.rafapuig.movieapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.rafapuig.movieapp.R
import es.rafapuig.movieapp.databinding.ItemMovieListBinding
import es.rafapuig.movieapp.domain.model.Movie


class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(private val binding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.movieTitle.text = movie.title

            Glide.with(itemView.context)
                .load(movie.posterURL)
                .placeholder(R.mipmap.ic_launcher)
                .fitCenter()
                .into(binding.moviePoster)
        }

    }


    private val movies = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieListBinding.inflate(layoutInflater)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size


    fun addMovies(movieList: List<Movie>) {
        val size = itemCount
        movies.clear()
        notifyItemRangeRemoved(0, size)
        movies.addAll(movieList)
        notifyItemRangeInserted(0, movieList.size)
    }

}