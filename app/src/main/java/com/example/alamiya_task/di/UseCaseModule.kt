package com.example.alamiya_task.di

import com.example.alamiya_task.domin.repository.PrayerRepository
import com.example.alamiya_task.domin.use_case.GetPrayerTimesUseCase
import com.example.alamiya_task.domin.use_case.GetQiblaUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {


    @Provides
    @Singleton
    fun provideGetPrayerTimesUseCase(repository: PrayerRepository): GetPrayerTimesUseCase {
        return GetPrayerTimesUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideGetQibla(repository: PrayerRepository): GetQiblaUseCase {
        return GetQiblaUseCase(repository)
    }

}