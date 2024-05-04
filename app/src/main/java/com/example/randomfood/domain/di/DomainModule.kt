package com.example.randomfood.domain.di

import com.example.randomfood.data.data_source.local.FavoriteFoodDao
import com.example.randomfood.data.data_source.remote.FreeMealApi
import com.example.randomfood.data.repository.DefaultFavoriteFoodRepository
import com.example.randomfood.data.repository.DefaultRandomFoodRepository
import com.example.randomfood.domain.repository.FavoriteFoodRepository
import com.example.randomfood.domain.repository.RandomFoodRepository
import com.example.randomfood.domain.use_case.GetRandomFoodUseCase
import com.example.randomfood.domain.use_case.RegisterFavoriteFoodUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun provideRandomFoodRepository(api: FreeMealApi): RandomFoodRepository {
        return DefaultRandomFoodRepository(api)
    }

    @Provides
    @Singleton
    fun provideFavoriteFoodRepository(dao: FavoriteFoodDao): FavoriteFoodRepository {
        return DefaultFavoriteFoodRepository(dao)
    }

    @Provides
    @Singleton
    fun provideGetRandomFoodUseCase(randomFoodRepository: RandomFoodRepository): GetRandomFoodUseCase {
        return GetRandomFoodUseCase(randomFoodRepository)
    }

    @Provides
    @Singleton
    fun provideRegisterFavoriteFoodUseCase(repository: FavoriteFoodRepository): RegisterFavoriteFoodUseCase {
        return RegisterFavoriteFoodUseCase(repository)
    }
}
