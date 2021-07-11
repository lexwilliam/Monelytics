package com.lexwilliam.moneymanager.data.repository

import com.lexwilliam.moneymanager.data.dao.ReportDao
import com.lexwilliam.moneymanager.data.mapper.toDomain
import com.lexwilliam.moneymanager.data.mapper.toEntity
import com.lexwilliam.moneymanager.domain.repository.IReportRepository
import com.lexwilliam.moneymanager.domain.model.Report
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class ReportRepository(
    private val reportDao: ReportDao
): IReportRepository {
    override suspend fun getReportByName(itemName: String): Flow<Report> = flow {
        val item = reportDao.getReportByName(itemName).toDomain()
        emit(item)
    }

    override suspend fun getAllReport(): Flow<List<Report>> = flow {
        reportDao.getAllReport().collect {
            emit( it.map { it.toDomain() })
        }
    }

    override suspend fun insertReport(report: Report): Flow<Long> = flow {
        val affectedRow = reportDao.insert(report.toEntity())
        emit(affectedRow)
    }

    override suspend fun updateReport(report: Report): Flow<Int> = flow {
        val affectedRow = reportDao.update(report.toEntity())
        emit(affectedRow)
    }

    override suspend fun deleteReport(report: Report): Flow<Int> = flow {
        val affectedRow = reportDao.delete(report.toEntity())
        emit(affectedRow)
    }


}