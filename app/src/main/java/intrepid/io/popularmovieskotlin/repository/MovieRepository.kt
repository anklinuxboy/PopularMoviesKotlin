package intrepid.io.popularmovieskotlin.repository

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import intrepid.io.popularmovieskotlin.*
import intrepid.io.popularmovieskotlin.db.MovieDao
import intrepid.io.popularmovieskotlin.models.MovieInfo
import intrepid.io.popularmovieskotlin.models.MovieResponse
import intrepid.io.popularmovieskotlin.net.MovieService
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MovieRepository {
    @Inject
    lateinit var movieService: MovieService
    @Inject
    lateinit var movieDao : MovieDao

    val compositeDisposable = CompositeDisposable()

    var moviesData: MutableLiveData<List<MovieInfo>> = MutableLiveData()

    init {
        MoviesApplication.appComponent.inject(this)
        getMovies()
    }

    private fun getMovies() {
        getMoviesFromDB()
    }

    private fun getMoviesFromApi() {
        val movieApiCall = movieService.getMovies(SORT_PREF, BuildConfig.OPEN_TMDB_API_KEY)

        movieApiCall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { response: MovieResponse? ->
                    return@map response?.results
                }
                .subscribe({ movieList: List<MovieInfo>? ->
                    kotlin.run {
                        setPosterUrlRating(movieList)
                        moviesData.value = movieList
                        insertMoviesInDB(movieList)
                    }
                })
    }

    private fun getMoviesFromDB() {
        compositeDisposable.add(Observable.fromCallable {movieDao.getSavedMovies()}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it == null) {
                        Log.d("test", "test movies not loaded from DB")
                        getMoviesFromApi()
                    } else {
                        moviesData.value = it.value
                        Log.d("test", "test movies loaded from db")
                    }
                })
    }

    private fun insertMoviesInDB(moviesList: List<MovieInfo>?) {
        compositeDisposable.add(Observable.fromCallable { movieDao.saveMovies(moviesList!!)}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }

    private fun setPosterUrlRating(movieList: List<MovieInfo>?) {
        if (movieList != null) {
            for (movie in movieList) {
                movie.posterUrl = POSTER_URL + movie.poster_path
                movie.voterRating = movie.vote_average + RATING
            }
        }
    }
}