package es.rafapuig.movieapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
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


        private val binding = ItemMovieListBinding.bind(itemView)

        fun bindTo(movie: Movie) {
            binding.movieTitle.text = movie.title

            Glide.with(parent.context)
                .load(movie.posterURL)
                .placeholder(R.mipmap.ic_launcher)
                .fitCenter()
                .into(binding.moviePoster)
        }

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        //val layoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(parent)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

        }
    }
}