package com.example.daggerhiltmvvmblog.di

import com.example.daggerhiltmvvmblog.repository.MainRepository
import com.example.daggerhiltmvvmblog.retrofit.BlogRetrofit
import com.example.daggerhiltmvvmblog.retrofit.NetworkMapper
import com.example.daggerhiltmvvmblog.room.BlogDao
import com.example.daggerhiltmvvmblog.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        retrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
        ): MainRepository{
        return MainRepository(blogDao,retrofit,cacheMapper,networkMapper)
    }
}