package intrepid.io.popularmovieskotlin.repository

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import intrepid.io.popularmovieskotlin.*
import intrepid.io.popularmovieskotlin.db.MovieDao
import intrepid.io.popularmovieskotlin.models.MovieInfo
import intrepid.io.popularmovieskotlin.models.MovieResponse
import intrepid.io.popularmovieskotlin.models.MovieWithInfo
import intrepid.io.popularmovieskotlin.net.MovieService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MovieRepository {
    @Inject
    lateinit var movieService: MovieService

    @Inject
    lateinit var movieDao: MovieDao

    private val compositeDisposable = CompositeDisposable()

    var moviesData: MediatorLiveData<List<MovieWithInfo>> = MediatorLiveData()

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
            .map { response: MovieResponse? ->
                return@map response?.results
            }
            .flatMapIterable { it }
            .flatMap({ t -> movieService.getMovieInfo(t.id, BuildConfig.OPEN_TMDB_API_KEY) }
            ) { t1, t2 -> MovieWithInfo(t1, t2) }
            .toList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { setMovies(it) },
                { Timber.e(it.localizedMessage) }
            )
        )
    }

    private fun fetchMoviesFromDB() {
//        compositeDisposable.add(Observable.fromCallable { movieDao.getSavedMovies() }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                moviesData.addSource(it, moviesData::setValue)
//            })
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

    private fun setMovies(movieList: List<MovieWithInfo>) {
        for (movie in movieList) {
            movie.movieInfo.posterUrl = POSTER_URL + movie.movieInfo.poster_path
            movie.movieInfo.voterRating = movie.movieInfo.vote_average + RATING
        }
        val liveData = MutableLiveData<List<MovieWithInfo>>()
        liveData.value = movieList
        moviesData.addSource(liveData, moviesData::setValue)
    }
}
