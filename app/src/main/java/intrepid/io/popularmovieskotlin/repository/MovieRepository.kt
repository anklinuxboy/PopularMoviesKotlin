package intrepid.io.popularmovieskotlin.repository

import androidx.lifecycle.MediatorLiveData
import intrepid.io.popularmovieskotlin.*
import intrepid.io.popularmovieskotlin.db.MovieDao
import intrepid.io.popularmovieskotlin.models.MovieInfo
import intrepid.io.popularmovieskotlin.models.MovieResponse
import intrepid.io.popularmovieskotlin.net.MovieService
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
    lateinit var movieDao: MovieDao

    private val compositeDisposable = CompositeDisposable()

    var moviesData: MediatorLiveData<List<MovieInfo>> = MediatorLiveData()

    init {
        MoviesApplication.appComponent.inject(this)
        moviesData.value = null
        fetchMovies()
    }

    private fun fetchMovies() {
        fetchMoviesFromApi()
    }

    private fun fetchMoviesFromApi() {
        val movieApiCall = movieService.getMovies(SORT_PREF, BuildConfig.OPEN_TMDB_API_KEY)

        compositeDisposable.add(movieApiCall.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { response: MovieResponse? ->
                return@map response?.results
            }
            .subscribe(
                { movieList: List<MovieInfo>? ->
                    kotlin.run {
                        setPosterUrlRating(movieList)
                        insertMoviesInDB(movieList)
                    }
                },
                {
                    Timber.e(it.localizedMessage)
                }))
    }

    private fun fetchMoviesFromDB() {
        compositeDisposable.add(Observable.fromCallable { movieDao.getSavedMovies() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                moviesData.addSource(it, moviesData::setValue)
            })
    }

    private fun insertMoviesInDB(moviesList: List<MovieInfo>?) {
        compositeDisposable.add(Observable.fromCallable {
            movieDao.saveMovies(moviesList!!)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                fetchMoviesFromDB()
            })
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
