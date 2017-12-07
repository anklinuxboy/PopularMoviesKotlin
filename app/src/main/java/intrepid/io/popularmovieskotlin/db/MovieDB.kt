package intrepid.io.popularmovieskotlin.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import intrepid.io.popularmovieskotlin.models.MovieInfo

@Database(entities = [MovieInfo::class], version = 1, exportSchema = false)
abstract class MovieDB : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
}