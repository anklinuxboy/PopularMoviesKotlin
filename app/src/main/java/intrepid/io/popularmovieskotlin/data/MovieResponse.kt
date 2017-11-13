package intrepid.io.popularmovieskotlin.data

import intrepid.io.popularmovieskotlin.POSTER_URL
import intrepid.io.popularmovieskotlin.RATING

data class MovieResponse(val results: List<MovieInfo>)

data class MovieInfo(val vote_average: String,
                     val title: String,
                     val poster_path: String,
                     val overview: String,
                     val release_date: String) {
    val posterUrl : String = POSTER_URL + poster_path
    val voterRating : String = vote_average + RATING
}