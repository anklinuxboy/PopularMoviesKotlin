package intrepid.io.popularmovieskotlin.net

import intrepid.io.popularmovieskotlin.models.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/{sortPref}")
    fun getMovies(@Path("sortPref") sortPref: String, @Query("api_key") key: String): Observable<MovieResponse>
}