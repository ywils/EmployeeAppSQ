package com.android.employeeapp.di

import com.android.employeeapp.data.network.EmployeeAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideEmployeeApi(
        retrofitBuilder: Retrofit.Builder
    ) : EmployeeAPI {
        return retrofitBuilder
            .build()
            .create(EmployeeAPI::class.java)
    }

}