package intrepid.io.popularmovieskotlin.ui;

import android.os.Bundle
import android.widget.GridView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import intrepid.io.popularmovieskotlin.R
import intrepid.io.popularmovieskotlin.adapters.GridViewAdapter
import intrepid.io.popularmovieskotlin.models.MovieInfo
import intrepid.io.popularmovieskotlin.viewmodels.MovieViewModel

class MainActivity : AppCompatActivity() {

    private var movies = ArrayList<MovieInfo>()
    lateinit var adapter: GridViewAdapter
    lateinit var gridView: GridView
    private val movieViewModel by viewModels<MovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridView = findViewById(R.id.grid_view)
        adapter = GridViewAdapter(this, movies)
        gridView.adapter = adapter
        movieViewModel.getMoviesData().observe(this) { moviesList: List<MovieInfo>? ->
            if (moviesList != null) {
                movies.clear()
                movies.addAll(moviesList)
                adapter.notifyDataSetChanged()
            }
        }
    }
}
