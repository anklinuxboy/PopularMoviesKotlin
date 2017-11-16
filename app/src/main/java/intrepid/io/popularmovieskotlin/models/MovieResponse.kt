package intrepid.io.popularmovieskotlin.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

data class MovieResponse(val results: List<MovieInfo>)

@Entity(tableName = "movies")
data class MovieInfo(@PrimaryKey @ColumnInfo(name = "movie_id") var id: String,
                     @Ignore var vote_average: String,
                     @ColumnInfo(name = "movie_title") var title: String,
                     @Ignore var poster_path: String,
                     @ColumnInfo(name = "movie_overview") var overview: String,
                     var release_date: String,
                     @ColumnInfo(name = "movie_poster") var posterUrl: String,
                     @ColumnInfo(name = "movie_rating") var voterRating: String) {
    constructor() : this("", "", "", "", "", "", "", "")
}