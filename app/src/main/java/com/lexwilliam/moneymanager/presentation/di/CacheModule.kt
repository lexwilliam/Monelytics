package com.lexwilliam.moneymanager.presentation.di

import android.content.Context
import androidx.room.Room
import com.lexwilliam.moneymanager.data.ReportDatabase
import com.lexwilliam.moneymanager.data.dao.ReportDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideReportDb(@ApplicationContext context : Context): ReportDatabase {
        return Room
            .databaseBuilder(
                context,
                ReportDatabase::class.java,
                "report_database"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideReportDAO(reportDatabase: ReportDatabase): ReportDao {
        return reportDatabase.reportDao()
    }

}