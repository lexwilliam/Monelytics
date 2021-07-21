package com.lexwilliam.moneymanager.presentation.ui.wallet

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
    private val insertWalletUseCase: InsertWalletUseCase
): BaseViewModel() {

    override val coroutineExceptionHandler= CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
    }

    private var walletJob: Job? = null
    private var reportJob: Job? = null

    override fun onCleared() {
        super.onCleared()
        walletJob?.cancel()
        reportJob?.cancel()
    }

    fun insertWallet(
        wallet: WalletPresentation
    ) {
        walletJob?.cancel()
        walletJob = launchCoroutine {
            insertWalletUseCase.invoke(wallet.toDomain()).collect {
                if (it == -1L) {
                    Log.d("TAG", "Insert Failed")
                } else {
                    Log.d("TAG", "Insert Successful")
                }
            }
        }
    }
}