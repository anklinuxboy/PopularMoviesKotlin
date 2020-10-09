package intrepid.io.popularmovieskotlin.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import intrepid.io.popularmovieskotlin.db.MovieDB
import intrepid.io.popularmovieskotlin.db.MovieDao
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideMovieDB(@ApplicationContext appCtx: Context): MovieDB =
        Room.databaseBuilder(appCtx, MovieDB::class.java, "movies").build()

    @Provides
    @Singleton
    fun provideMovieDao(movieDB: MovieDB): MovieDao = movieDB.getMovieDao()
}
