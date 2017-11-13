package intrepid.io.popularmovieskotlin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView
import intrepid.io.popularmovieskotlin.adapters.GridViewAdapter
import intrepid.io.popularmovieskotlin.data.MovieInfo
import intrepid.io.popularmovieskotlin.net.MovieService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var movieService: MovieService
    var movies : List<MovieInfo> = ArrayList()
    lateinit var adapter: GridViewAdapter
    lateinit var gridView : GridView

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
                .subscribe()
    }
}
