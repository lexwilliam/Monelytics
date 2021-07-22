package com.lexwilliam.moneymanager.data.repository

import android.util.Log
import com.lexwilliam.moneymanager.data.dao.WalletDao
import com.lexwilliam.moneymanager.data.mapper.toDomain
import com.lexwilliam.moneymanager.data.mapper.toEntity
import com.lexwilliam.moneymanager.domain.model.Report
import com.lexwilliam.moneymanager.domain.repository.IWalletRepository
import com.lexwilliam.moneymanager.domain.model.Wallet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class WalletRepository(
    private val walletDao: WalletDao
): IWalletRepository {
    override suspend fun getWalletWithReportById(walletName: String): Flow<Wallet> = flow {
        walletDao.getWalletWithReportByName(walletName).collect {
            emit( it.toDomain() )
        }
    }

    override suspend fun getAllReport(): Flow<List<Report>> = flow {
        walletDao.getAllReport().collect {
            emit( it.map { it.toDomain() })
        }
    }

    override suspend fun getAllWalletWithReport(): Flow<List<Wallet>> = flow {
        walletDao.getAllWalletWithReport().collect {
            emit( it.map { it.toDomain() })
        }
    }

    override suspend fun getReportById(reportId: Int): Flow<Report> = flow {
        walletDao.getReportById(reportId).collect {
            emit( it.toDomain() )
        }
    }

    override suspend fun insertWallet(wallet: Wallet): Flow<Long> = flow {
        val affectedRow = walletDao.insertWallet(wallet.toEntity())
        emit(affectedRow)
    }

    override suspend fun insertReport(report: Report): Flow<Long> = flow {
        val affectedRow = walletDao.insertReport(report.toEntity())
        emit(affectedRow)
    }

    override suspend fun updateWallet(wallet: Wallet): Flow<Int> = flow {
        val affectedRow = walletDao.updateWallet(wallet.toEntity())
        emit(affectedRow)
    }

    override suspend fun updateReport(report: Report): Flow<Int> = flow {
        val affectedRow = walletDao.updateReport(report.toEntity())
        emit(affectedRow)
    }

    override suspend fun deleteWallet(wallet: Wallet): Flow<Int> = flow {
        val affectedRow = walletDao.deleteWallet(wallet.toEntity())
        emit(affectedRow)
    }

    override suspend fun deleteReport(report: Report): Flow<Int> = flow {
        val affectedRow = walletDao.deleteReport(report.toEntity())
        emit(affectedRow)
    }

}