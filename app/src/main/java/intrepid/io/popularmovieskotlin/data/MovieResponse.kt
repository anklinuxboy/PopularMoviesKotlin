package intrepid.io.popularmovieskotlin.data

import intrepid.io.popularmovieskotlin.POSTER_URL
import intrepid.io.popularmovieskotlin.RATING

data class MovieResponse(val results: ArrayList<MovieInfo>)

data class MovieInfo(val vote_average: String,
                     val title: String,
                     val poster_path: String,
                     val overview: String,
                     val release_date: String) {
    lateinit var posterUrl : String
    lateinit var voterRating : String
}