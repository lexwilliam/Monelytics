package com.lexwilliam.local.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.lexwilliam.local.dao.WalletDao
import com.lexwilliam.local.mapper.toDomain
import com.lexwilliam.local.model.ReportResponse
import com.lexwilliam.local.model.WalletEntity
import com.lexwilliam.local.model.WalletResponse
import com.lexwilliam.moneymanager.domain.model.Report
import com.lexwilliam.moneymanager.domain.repository.IWalletRepository
import com.lexwilliam.moneymanager.domain.model.Wallet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class WalletRepository(
    private val walletDao: WalletDao
): IWalletRepository {

    private val WALLETS_COLLECTION = "wallets"
    private val REPORTS_COLLECTION = "reports"

    fun setupFirestore(): FirebaseFirestore {
        val firestore = FirebaseFirestore.getInstance()
        firestore.useEmulator("10.0.0.2", 8080)
        return firestore
    }

    override suspend fun getWalletsFromFirestore(): Flow<Long> = flow {
        var wallets: List<WalletResponse>? = null
        setupFirestore().collection(WALLETS_COLLECTION).get().addOnCompleteListener { task ->
            if(task.isSuccessful) {
                val document = task.result
                if(document != null) {
                    wallets = document.toObjects(WalletResponse::class.java)
                }
            }
        }
        wallets?.forEach { wallet ->
            walletDao.insertWallet(wallet = WalletEntity(wallet.name, wallet.iconId, wallet.timeAdded))
        }
        var reports: List<ReportResponse>? = null
        setupFirestore().collection(REPORTS_COLLECTION).get().addOnCompleteListener { task ->
            if(task.isSuccessful) {
                val document = task.result
                if(document != null) {
                    reports = document.toObjects(ReportResponse::class.java)
                }
            }
        }
        reports?.forEach { report ->
            walletDao.insertReport(report.toEntity())
        }
        emit(0)
    }

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
        val walletEntity = wallet.toEntity()
        setupFirestore().collection(WALLETS_COLLECTION)
            .document(walletEntity.name)
            .set(walletEntity)
            .addOnSuccessListener {
                Log.d("WalletRepo", "DocumentSnapshot added")
            }
            .addOnFailureListener { e ->
                Log.w("WalletRepo", "Error adding document", e)
            }
        val affectedRow = walletDao.insertWallet(walletEntity)
        emit(affectedRow)
    }

    override suspend fun insertReport(report: Report): Flow<Long> = flow {
        val reportEntity = report.toEntity()
            setupFirestore().collection(REPORTS_COLLECTION)
            .add(reportEntity.toResponse())
            .addOnSuccessListener { documentReference ->
                Log.d("WalletRepo", "DocumentSnapshot added with ID: $documentReference")
            }
            .addOnFailureListener { e ->
                Log.w("WalletRepo", "Error adding document", e)
            }
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