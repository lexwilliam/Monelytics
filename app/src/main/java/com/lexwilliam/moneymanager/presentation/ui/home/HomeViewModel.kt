package com.lexwilliam.moneymanager.presentation.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lexwilliam.moneymanager.domain.model.Wallet
import com.lexwilliam.moneymanager.domain.usecase.GetAllReportUseCase
import com.lexwilliam.moneymanager.domain.usecase.GetAllWalletUseCase
import com.lexwilliam.moneymanager.presentation.base.BaseViewModel
import com.lexwilliam.moneymanager.presentation.mapper.toPresentation
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation
import com.lexwilliam.moneymanager.presentation.model.WalletPresentation
import com.lexwilliam.moneymanager.presentation.util.ExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val getAllWalletUseCase: GetAllWalletUseCase,
    private val getAllReportUseCase: GetAllReportUseCase
): BaseViewModel() {

    override val coroutineExceptionHandler= CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
    }

    private var _state = MutableStateFlow(HomeViewState())
    val state = _state.asStateFlow()

    private var walletJob: Job? = null
    private var reportJob: Job? = null

    override fun onCleared() {
        super.onCleared()
        walletJob?.cancel()
        reportJob?.cancel()
    }

    init {
        walletJob?.cancel()
        walletJob = launchCoroutine {
            getAllWalletUseCase.invoke().collect { results ->
                if(results.isEmpty()) {
                    Log.d("TAG", "Get All Wallet Failed")
                } else {
                    Log.d("TAG", "Get All Wallet Success")
                    val wallets = results.map { it.toPresentation() }
                    _state.value = _state.value.copy(wallets = wallets)
                }
            }
        }
        reportJob?.cancel()
        reportJob = launchCoroutine {
            getAllReportUseCase.invoke().collect { results ->
                if(results.isEmpty()) {
                    Log.d("TAG", "Get All Wallet Failed")
                } else {
                    Log.d("TAG", "Get All Wallet Success")
                    val reports = results.map { it.toPresentation() }
                    _state.value = _state.value.copy(reports = reports)
                }
            }
        }
    }
}

data class HomeViewState(
    val wallets: List<WalletPresentation> = emptyList(),
    val reports: List<ReportPresentation> = emptyList(),
    val isLoading: Boolean = false
)