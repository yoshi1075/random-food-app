package com.example.randomfood.data.di

import android.content.Context
import androidx.room.Room
import com.example.randomfood.data.data_source.local.FavoriteFoodDao
import com.example.randomfood.data.data_source.local.FavoriteFoodDatabase
import com.example.randomfood.data.data_source.remote.FreeMealApi
import com.example.randomfood.data.util.Constants.BASE_URL
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    @Provides
    @Singleton
    fun provideMoshi(): MoshiConverterFactory {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @Singleton
    fun provideRetrofit(moshiConverterFactory: MoshiConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getClient())
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideFreeMealApi(retrofit: Retrofit): FreeMealApi {
        return retrofit.create(FreeMealApi::class.java)
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideFavoriteFoodDatabase(@ApplicationContext context: Context): FavoriteFoodDatabase {
        return Room.databaseBuilder(context, FavoriteFoodDatabase::class.java, "favorite_food")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideFavoriteFoodDao(db: FavoriteFoodDatabase) = db.dao
}
