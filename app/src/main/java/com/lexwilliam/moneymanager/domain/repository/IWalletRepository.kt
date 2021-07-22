package com.lexwilliam.moneymanager.domain.repository

import com.lexwilliam.moneymanager.domain.model.Report
import com.lexwilliam.moneymanager.domain.model.Wallet
import kotlinx.coroutines.flow.Flow

interface IWalletRepository {

    suspend fun getAllWalletWithReport(): Flow<List<Wallet>>

    suspend fun getWalletWithReportById(walletName: String): Flow<Wallet>

    suspend fun getAllReport(): Flow<List<Report>>

    suspend fun getReportById(reportId: Int): Flow<Report>

    suspend fun insertWallet(wallet: Wallet): Flow<Long>

    suspend fun insertReport(report: Report): Flow<Long>

    suspend fun updateWallet(wallet: Wallet): Flow<Int>

    suspend fun updateReport(report: Report): Flow<Int>

    suspend fun deleteWallet(wallet: Wallet): Flow<Int>

    suspend fun deleteReport(report: Report): Flow<Int>

}