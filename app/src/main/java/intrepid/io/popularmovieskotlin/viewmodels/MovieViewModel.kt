package intrepid.io.popularmovieskotlin.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import intrepid.io.popularmovieskotlin.models.MovieInfo
import intrepid.io.popularmovieskotlin.repository.MovieRepository
import javax.inject.Inject

class MovieViewModel : ViewModel() {
    private val repository = MovieRepository()

    fun getMoviesData(): LiveData<List<MovieInfo>> = repository.moviesData
}
