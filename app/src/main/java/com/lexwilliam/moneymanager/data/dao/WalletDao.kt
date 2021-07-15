package com.lexwilliam.moneymanager.data.dao

import androidx.room.*
import com.lexwilliam.moneymanager.data.model.ReportEntity
import com.lexwilliam.moneymanager.data.model.WalletEntity
import com.lexwilliam.moneymanager.data.model.WalletWithReport
import kotlinx.coroutines.flow.Flow

@Dao
interface WalletDao {

    @Transaction
    @Query("SELECT * FROM wallet ORDER BY walletId")
    fun getAllWalletWithReport(): Flow<List<WalletWithReport>>

    @Transaction
    @Query("SELECT * FROM wallet WHERE walletId LIKE :id")
    suspend fun getWalletWithReportById(id: Int): WalletWithReport

    @Query("SELECT * FROM wallet_report ORDER BY timeAdded")
    fun getAllReport(): Flow<List<ReportEntity>>

    @Query("SELECT * FROM wallet_report WHERE reportId IS :reportId")
    suspend fun getReportById(reportId: Int): ReportEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWallet(wallet: WalletEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReport(report: ReportEntity): Long

    @Delete
    suspend fun deleteWallet(wallet: WalletEntity): Int

    @Delete
    suspend fun deleteReport(report: ReportEntity): Int

    @Update
    suspend fun updateWallet(wallet: WalletEntity): Int

    @Update
    suspend fun updateReport(report: ReportEntity): Int

}