package intrepid.io.popularmovieskotlin.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import intrepid.io.popularmovieskotlin.data.MovieInfo

class MovieViewModel : ViewModel() {
    private lateinit var moviesLiveData: LiveData<ArrayList<MovieInfo>>
}