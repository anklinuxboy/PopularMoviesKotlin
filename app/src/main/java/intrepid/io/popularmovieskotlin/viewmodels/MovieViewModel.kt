package intrepid.io.popularmovieskotlin.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import intrepid.io.popularmovieskotlin.models.MovieInfo
import intrepid.io.popularmovieskotlin.repository.MovieRepository

class MovieViewModel : ViewModel() {
    private val repository = MovieRepository()

    fun getMoviesData(): LiveData<List<MovieInfo>> = repository.moviesData
}