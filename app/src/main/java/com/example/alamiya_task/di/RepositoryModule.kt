package com.example.alamiya_task.di

import com.example.alamiya_task.core.connectivity_observer.NetworkState
import com.example.alamiya_task.data.Repository.PrayerRepositoryImp
import com.example.alamiya_task.data.source.Database.PrayerDatabase
import com.example.alamiya_task.data.source.RemoteData.ApiInterface
import com.example.alamiya_task.domin.repository.PrayerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(api: ApiInterface, db: PrayerDatabase , networkState: NetworkState): PrayerRepository {
        return PrayerRepositoryImp(api, db , networkState)
    }

    @Provides
    @Singleton
    fun provideNetworkState(): NetworkState {
        return NetworkState
    }

}