package com.lexwilliam.moneymanager.presentation.di

import com.google.firebase.firestore.FirebaseFirestore
import com.lexwilliam.moneymanager.data.dao.WalletDao
import com.lexwilliam.moneymanager.data.repository.WalletRepository
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