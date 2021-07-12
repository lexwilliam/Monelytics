package com.lexwilliam.moneymanager.domain.repository

import com.lexwilliam.moneymanager.domain.model.Wallet
import kotlinx.coroutines.flow.Flow

interface IWalletRepository {

    suspend fun getWalletByName(itemName: String): Flow<Wallet>

    suspend fun getAllWallet(): Flow<List<Wallet>>

    suspend fun insertWallet(wallet: Wallet): Flow<Long>

    suspend fun updateWallet(wallet: Wallet): Flow<Int>

    suspend fun deleteWallet(wallet: Wallet): Flow<Int>
}