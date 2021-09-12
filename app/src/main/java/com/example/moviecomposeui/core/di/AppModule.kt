package com.example.moviecomposeui.core.di

import com.example.moviecomposeui.BuildConfig
import com.example.moviecomposeui.core.util.Constants
import com.example.moviecomposeui.feature.movieList.MovieRepository
import com.example.moviecomposeui.feature.movieList.MovieRepositoryImp
import com.example.moviecomposeui.feature.movieList.MovieService
import com.example.moviecomposeui.feature.movievideos.VideosRepository
import com.example.moviecomposeui.feature.movievideos.VideosRepositoryImp
import com.example.moviecomposeui.feature.movievideos.VideosService
import com.example.moviecomposeui.feature.tvlist.TvRepository
import com.example.moviecomposeui.feature.tvlist.TvRepositoryImp
import com.example.moviecomposeui.feature.tvlist.TvService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideOkhttp(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .addInterceptor(AddInterceptor())
        if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }
    @Provides
    @Singleton
    fun provideRetrofit(okhHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okhHttpClient)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(movieService: MovieService): MovieRepository = MovieRepositoryImp(movieService)

    @Provides
    @Singleton
    fun provideTvRepository(tvService: TvService): TvRepository = TvRepositoryImp(tvService)

    @Provides
    @Singleton
    fun provideVideoRepository(videosService: VideosService): VideosRepository = VideosRepositoryImp(videosService)


}