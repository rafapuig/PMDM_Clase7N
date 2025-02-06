package es.rafapuig.movieapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import es.rafapuig.movieapp.R
import es.rafapuig.movieapp.databinding.ItemMovieListBinding
import es.rafapuig.movieapp.domain.model.Movie

class MovieListAdapter : ListAdapter<Movie, MovieListAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    class MovieViewHolder(private val parent: ViewGroup) :
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_movie_list, parent, false)
        ) {


        //private val binding = ItemMovieListBinding.bind(itemView)

        private val movieTitle : TextView = itemView.findViewById(R.id.movie_title)
        //private val movieTitle: TextView = binding.movieTitle
        private val moviePoster : ImageView = itemView.findViewById(R.id.movie_poster)
        //private val moviePoster: ImageView = binding.moviePoster

        fun bindTo(movie: Movie) {
            movieTitle.text = movie.title
            Glide.with(parent.context)
                .load(movie.posterURL)
                .placeholder(R.mipmap.ic_launcher)
                .fitCenter()
                .into(moviePoster)
        }

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(parent)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id
        }
    }
}