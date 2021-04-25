package intrepid.io.popularmovieskotlin.ui;

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import intrepid.io.popularmovieskotlin.R
import intrepid.io.popularmovieskotlin.adapters.MovieAdapter
import intrepid.io.popularmovieskotlin.models.MovieWithInfo
import intrepid.io.popularmovieskotlin.viewmodels.MovieViewModel

class MainActivity : AppCompatActivity() {

    private var movies = ArrayList<MovieWithInfo>()
    private lateinit var adapter: MovieAdapter
    private lateinit var recyclerView: RecyclerView
    private val movieViewModel by viewModels<MovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.list_view)
        adapter = MovieAdapter(movies)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        movieViewModel.getMoviesData().observe(this) { moviesList: List<MovieWithInfo>? ->
            if (moviesList != null) {
                movies.clear()
                movies.addAll(moviesList)
                adapter.notifyDataSetChanged()
            }
        }
    }
}
