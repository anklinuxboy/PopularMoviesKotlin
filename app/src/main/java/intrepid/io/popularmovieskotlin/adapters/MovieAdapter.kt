package intrepid.io.popularmovieskotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import intrepid.io.popularmovieskotlin.R
import intrepid.io.popularmovieskotlin.models.MovieWithInfo

class MovieAdapter(val movies: ArrayList<MovieWithInfo>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val poster: ImageView = itemView.findViewById(R.id.movie_image)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val overview: TextView = itemView.findViewById(R.id.overview)

        fun bind(movie: MovieWithInfo) {
            Picasso.with(poster.context)
                .load(movie.movieInfo.posterUrl)
                .into(poster)

            title.text = movie.movieInfo.title
            overview.text = movie.movieIdInfo.overview
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_poster, parent, false)

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size
}