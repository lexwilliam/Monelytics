package com.lexwilliam.moneymanager.presentation.di

import com.lexwilliam.moneymanager.data.dao.ReportDao
import com.lexwilliam.moneymanager.data.repository.ReportRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideReportRepository(reportDao: ReportDao): ReportRepository {
        return ReportRepository(reportDao)
    }

}