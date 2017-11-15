package intrepid.io.popularmovieskotlin.di

import dagger.Component
import intrepid.io.popularmovieskotlin.MainActivity
import intrepid.io.popularmovieskotlin.repository.MovieRepository
import intrepid.io.popularmovieskotlin.viewmodels.MovieViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetModule::class))
interface AppComponent {
    fun inject(movieRepository: MovieRepository)
}