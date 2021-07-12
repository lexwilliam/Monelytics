package com.lexwilliam.moneymanager.data.repository

import com.lexwilliam.moneymanager.data.dao.WalletDao
import com.lexwilliam.moneymanager.data.mapper.toDomain
import com.lexwilliam.moneymanager.data.mapper.toEntity
import com.lexwilliam.moneymanager.domain.repository.IWalletRepository
import com.lexwilliam.moneymanager.domain.model.Wallet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class WalletRepository(
    private val walletDao: WalletDao
): IWalletRepository {
    override suspend fun getWalletByName(itemName: String): Flow<Wallet> = flow {
        val item = walletDao.getReportByName(itemName).toDomain()
        emit(item)
    }

    override suspend fun getAllWallet(): Flow<List<Wallet>> = flow {
        walletDao.getAllReport().collect {
            emit( it.map { it.toDomain() })
        }
    }

    override suspend fun insertWallet(wallet: Wallet): Flow<Long> = flow {
        val affectedRow = walletDao.insert(wallet.toEntity())
        emit(affectedRow)
    }

    override suspend fun updateWallet(wallet: Wallet): Flow<Int> = flow {
        val affectedRow = walletDao.update(wallet.toEntity())
        emit(affectedRow)
    }

    override suspend fun deleteWallet(wallet: Wallet): Flow<Int> = flow {
        val affectedRow = walletDao.delete(wallet.toEntity())
        emit(affectedRow)
    }


}