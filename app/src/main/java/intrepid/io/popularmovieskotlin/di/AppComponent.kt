package intrepid.io.popularmovieskotlin.di

import dagger.Component
import intrepid.io.popularmovieskotlin.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetModule::class))
interface AppComponent {
    fun inject(activity: MainActivity)
}