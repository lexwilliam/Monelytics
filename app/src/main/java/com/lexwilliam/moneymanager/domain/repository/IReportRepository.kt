package com.lexwilliam.moneymanager.domain.repository

import com.lexwilliam.moneymanager.data.dao.ReportDao
import com.lexwilliam.moneymanager.domain.model.Report
import kotlinx.coroutines.flow.Flow

interface IReportRepository {

    suspend fun getReportByName(itemName: String): Flow<Report>

    suspend fun getAllReport(): Flow<List<Report>>

    suspend fun insertReport(report: Report): Flow<Long>

    suspend fun updateReport(report: Report): Flow<Int>

    suspend fun deleteReport(report: Report): Flow<Int>
}