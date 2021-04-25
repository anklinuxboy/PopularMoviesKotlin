package intrepid.io.popularmovieskotlin.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

data class MovieResponse(val results: List<MovieInfo>)

// This is bad.
// Refactor this
@Entity(tableName = "movies")
data class MovieInfo(
    @PrimaryKey @ColumnInfo(name = "movie_id") var id: String,
    @Ignore var vote_average: String,
    @ColumnInfo(name = "movie_title") var title: String,
    @Ignore var poster_path: String,
    @ColumnInfo(name = "movie_overview") var overview: String,
    var release_date: String,
    @ColumnInfo(name = "movie_poster") var posterUrl: String,
    @ColumnInfo(name = "movie_rating") var voterRating: String
) {
    constructor() : this("", "", "", "", "", "", "", "")
}

class MovieIdInfo {
    var adult: Boolean = false
    var overview: String = ""
    var revenue: Int = -1
}

data class MovieWithInfo(val movieInfo: MovieInfo, val movieIdInfo: MovieIdInfo)