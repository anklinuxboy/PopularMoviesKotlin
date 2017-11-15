package intrepid.io.popularmovieskotlin.repository

import android.arch.lifecycle.MutableLiveData
import intrepid.io.popularmovieskotlin.BuildConfig
import intrepid.io.popularmovieskotlin.MoviesApplication
import intrepid.io.popularmovieskotlin.SORT_PREF
import intrepid.io.popularmovieskotlin.data.MovieInfo
import intrepid.io.popularmovieskotlin.data.MovieResponse
import intrepid.io.popularmovieskotlin.net.MovieService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieRepository {
    @Inject
    lateinit var movieService: MovieService

    var moviesData : MutableLiveData<ArrayList<MovieInfo>> = MutableLiveData()

    init {
        MoviesApplication.appComponent.inject(this)
        getMovies()
    }

    fun getMovies() {
        val movieApiCall = movieService.getMovies(SORT_PREF, BuildConfig.OPEN_TMDB_API_KEY)

        movieApiCall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { response: MovieResponse? ->
                    return@map response?.results
                }
                .subscribe({ movieList: ArrayList<MovieInfo>? ->
                    kotlin.run {
                        moviesData.value = movieList
                    }
                })
    }
}