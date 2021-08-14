package com.lexwilliam.moneymanager.di

import com.lexwilliam.local.dao.WalletDao
import com.lexwilliam.local.repository.WalletRepository
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
    fun provideReportRepository(walletDao: WalletDao): WalletRepository {
        return WalletRepository(walletDao)
    }

}