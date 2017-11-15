package intrepid.io.popularmovieskotlin.di

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import intrepid.io.popularmovieskotlin.db.MovieDB
import intrepid.io.popularmovieskotlin.db.MovieDao
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    fun provideMovieDB(application: Application): MovieDB =
            Room.databaseBuilder(application, MovieDB::class.java, "movies").build()

    @Provides
    @Singleton
    fun provideMovieDao(movieDB: MovieDB): MovieDao = movieDB.getMovieDao()
}