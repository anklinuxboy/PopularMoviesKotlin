package intrepid.io.popularmovieskotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import intrepid.io.popularmovieskotlin.R
import intrepid.io.popularmovieskotlin.models.MovieInfo

class GridViewAdapter(val context: Context, val movies: ArrayList<MovieInfo>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val movie = getItem(position)
        var imageView: ImageView
        val view: View?
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_poster, parent, false)
            imageView = view.findViewById(R.id.movie_image)
        } else {
            imageView = convertView.findViewById(R.id.movie_image)
        }

        Picasso.with(context)
                .load(movie.posterUrl)
                .into(imageView)

        return imageView
    }

    override fun getItem(position: Int): MovieInfo = movies[position]

    override fun getItemId(position: Int): Long = 0

    override fun getCount(): Int = movies.size
}