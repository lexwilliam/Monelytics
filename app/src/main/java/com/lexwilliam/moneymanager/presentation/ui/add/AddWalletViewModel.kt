package com.lexwilliam.moneymanager.presentation.ui.add

import android.util.Log
import com.lexwilliam.moneymanager.domain.usecase.InsertReportUseCase
import com.lexwilliam.moneymanager.domain.usecase.InsertWalletUseCase
import com.lexwilliam.moneymanager.presentation.base.BaseViewModel
import com.lexwilliam.moneymanager.presentation.mapper.toDomain
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation
import com.lexwilliam.moneymanager.presentation.model.WalletPresentation
import com.lexwilliam.moneymanager.presentation.util.ExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class AddWalletViewModel
@Inject constructor(
    private val insertWalletUseCase: InsertWalletUseCase,
    private val insertReportUseCase: InsertReportUseCase
): BaseViewModel() {

    override val coroutineExceptionHandler= CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
    }

    private var cacheJob: Job? = null

    override fun onCleared() {
        super.onCleared()
        cacheJob?.cancel()
    }

    fun insertWallet(
        wallet: WalletPresentation
    ) {
        cacheJob?.cancel()
        cacheJob = launchCoroutine {
            insertWalletUseCase.invoke(wallet.toDomain()).collect {
                if(it == -1L) {
                    Log.d("TAG", "Insert Failed")
                } else {
                    Log.d("TAG", "Insert Successful")
                }
            }
        }
    }
}