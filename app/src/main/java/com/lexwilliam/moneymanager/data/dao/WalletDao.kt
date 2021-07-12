package com.lexwilliam.moneymanager.data.dao

import androidx.room.*
import com.lexwilliam.moneymanager.data.model.WalletEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WalletDao {

    @Query("SELECT * FROM wallet WHERE name LIKE :name")
    suspend fun getReportByName(name: String): WalletEntity

    @Query("SELECT * FROM wallet ORDER BY id")
    fun getAllReport(): Flow<List<WalletEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(report: WalletEntity): Long

    @Delete
    suspend fun delete(report: WalletEntity): Int

    @Update
    suspend fun update(report: WalletEntity): Int

}