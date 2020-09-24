package intrepid.io.popularmovieskotlin.db

import androidx.room.Database
import androidx.room.RoomDatabase
import intrepid.io.popularmovieskotlin.models.MovieInfo

@Database(entities = [MovieInfo::class], version = 1, exportSchema = false)
abstract class MovieDB : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
}
