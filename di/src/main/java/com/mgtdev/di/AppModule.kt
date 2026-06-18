package com.mgtdev.di

import com.mgtdev.data.DataSource
import com.mgtdev.data.RepositoryApiImpl
import com.mgtdev.domain.RepositoryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepositoryApi(
        localSource: DataSource
    ): RepositoryApi = RepositoryApiImpl(localSource)
}