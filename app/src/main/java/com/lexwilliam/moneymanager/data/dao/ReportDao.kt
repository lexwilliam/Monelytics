package com.lexwilliam.moneymanager.data.dao

import androidx.room.*
import com.lexwilliam.moneymanager.data.model.ReportEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReportDao {

    @Query("SELECT * FROM report_table WHERE name LIKE :itemName")
    suspend fun getReportByName(itemName: String): ReportEntity

    @Query("SELECT * FROM report_table ORDER BY timeAdded")
    fun getAllReport(): Flow<List<ReportEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(report: ReportEntity): Long

    @Delete
    suspend fun delete(report: ReportEntity): Int

    @Update
    suspend fun update(report: ReportEntity): Int

}