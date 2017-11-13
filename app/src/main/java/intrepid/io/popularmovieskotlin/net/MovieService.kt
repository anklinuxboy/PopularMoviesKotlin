package intrepid.io.popularmovieskotlin.net

import intrepid.io.popularmovieskotlin.data.MovieResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie/{sortPref}")
    fun getMovies(sortPref: String, @Query("api_key") key: String) : Observable<MovieResponse>
}