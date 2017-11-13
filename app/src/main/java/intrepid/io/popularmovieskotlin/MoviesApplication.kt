package intrepid.io.popularmovieskotlin

import android.app.Application
import intrepid.io.popularmovieskotlin.di.AppComponent
import intrepid.io.popularmovieskotlin.di.AppModule
import intrepid.io.popularmovieskotlin.di.DaggerAppComponent
import intrepid.io.popularmovieskotlin.di.NetModule

class MoviesApplication : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule())
                .build()
    }
}