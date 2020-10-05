package intrepid.io.popularmovieskotlin.ui;

import android.os.Bundle
import android.widget.GridView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import intrepid.io.popularmovieskotlin.R
import intrepid.io.popularmovieskotlin.adapters.GridViewAdapter
import intrepid.io.popularmovieskotlin.models.MovieInfo
import intrepid.io.popularmovieskotlin.viewmodels.MovieViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var movies = ArrayList<MovieInfo>()
    private lateinit var adapter: GridViewAdapter
    private lateinit var gridView: GridView
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridView = findViewById(R.id.grid_view)
        adapter = GridViewAdapter(this, movies)
        gridView.adapter = adapter
        movieViewModel.getMoviesData().observe(this, { moviesList: List<MovieInfo>? ->
            if (moviesList != null) {
                movies.clear()
                movies.addAll(moviesList)
                adapter.notifyDataSetChanged()
            }
        })
    }
}
