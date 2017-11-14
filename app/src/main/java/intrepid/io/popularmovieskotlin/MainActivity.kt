package intrepid.io.popularmovieskotlin;

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.GridView
import intrepid.io.popularmovieskotlin.adapters.GridViewAdapter
import intrepid.io.popularmovieskotlin.data.MovieInfo
import intrepid.io.popularmovieskotlin.data.MovieResponse
import intrepid.io.popularmovieskotlin.net.MovieService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var movieService: MovieService
    var movies = ArrayList<MovieInfo>()
    lateinit var adapter: GridViewAdapter
    lateinit var gridView: GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MoviesApplication.appComponent.inject(this)

        gridView = findViewById(R.id.grid_view)
        adapter = GridViewAdapter(this, movies)
        gridView.adapter = adapter

        val movieApiCall = movieService.getMovies(SORT_PREF, BuildConfig.OPEN_TMDB_API_KEY)

        movieApiCall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { response: MovieResponse? ->
                    return@map response?.results
                }
                .subscribe({ movieList: ArrayList<MovieInfo>? ->
                    kotlin.run {
                        movies.clear()
                        movies.addAll(movieList!!)
                        adapter.notifyDataSetChanged()
                    }
                })
    }
}
