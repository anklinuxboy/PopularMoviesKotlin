package intrepid.io.popularmovieskotlin.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import intrepid.io.popularmovieskotlin.data.MovieInfo
import intrepid.io.popularmovieskotlin.repository.MovieRepository

class MovieViewModel : ViewModel() {
    private val repository = MovieRepository()

    fun getMoviesData() : MutableLiveData<ArrayList<MovieInfo>> = repository.moviesData
}