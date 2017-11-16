package intrepid.io.popularmovieskotlin;

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.GridView
import intrepid.io.popularmovieskotlin.adapters.GridViewAdapter
import intrepid.io.popularmovieskotlin.models.MovieInfo
import intrepid.io.popularmovieskotlin.viewmodels.MovieViewModel

class MainActivity : AppCompatActivity() {

    private var movies = ArrayList<MovieInfo>()
    lateinit var adapter: GridViewAdapter
    lateinit var gridView: GridView
    lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridView = findViewById(R.id.grid_view)
        adapter = GridViewAdapter(this, movies)
        gridView.adapter = adapter
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        movieViewModel.getMoviesData().observe(this, Observer<List<MovieInfo>> { moviesList: List<MovieInfo>? ->
            if (moviesList != null) {
                movies.clear()
                movies.addAll(moviesList)
                adapter.notifyDataSetChanged()
            }
        })
    }
}
