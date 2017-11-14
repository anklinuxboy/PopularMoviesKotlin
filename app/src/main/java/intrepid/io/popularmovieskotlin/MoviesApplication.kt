package intrepid.io.popularmovieskotlin

import android.app.Application
import com.facebook.stetho.Stetho
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
        Stetho.initializeWithDefaults(this)
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule())
                .build()
    }
}