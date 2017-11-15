package intrepid.io.popularmovieskotlin.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import intrepid.io.popularmovieskotlin.models.MovieInfo

@Dao
interface MovieDao {
    @Insert(onConflict = REPLACE)
    fun saveMovies(movies: List<MovieInfo>)

    @Query("SELECT * FROM movies")
    fun getSavedMovies(): LiveData<List<MovieInfo>>

    @Query("SELECT * FROM movies WHERE movie_id = :id")
    fun getMovieById(id: String) : MovieInfo

    @Update(onConflict = REPLACE)
    fun updateMovie(movie: MovieInfo)

    @Delete
    fun deleteMovie(movie: MovieInfo)
}