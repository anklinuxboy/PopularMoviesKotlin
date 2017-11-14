package intrepid.io.popularmovieskotlin.di

import android.app.Application
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import intrepid.io.popularmovieskotlin.API_BASE_URL
import intrepid.io.popularmovieskotlin.net.MovieService
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule {

    @Provides
    @Singleton
    fun provideCache(applicaton: Application) : Cache {
        val cacheSize = 15 * 1024 * 1024
        return Cache(applicaton.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache) : OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addNetworkInterceptor(StethoInterceptor())
        return client.cache(cache).build()
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(API_BASE_URL)
                .client(client)
                .build()
    }

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit) : MovieService =
            retrofit.create(MovieService::class.java)
}