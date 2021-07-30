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
): ViewModel() {

    private var _state = MutableStateFlow(HomeViewState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val walletsFlow = getAllWalletUseCase.invoke()
            val reportsFlow = getAllReportUseCase.invoke()
            combine(walletsFlow, reportsFlow) { wallets, reports ->
                val walletsPresentation = wallets.map { it.toPresentation() }
                val reportsPresentation = reports.map { it.toPresentation() }
                Log.d("TAG", walletsPresentation.toString())
                HomeViewState(
                    wallets = walletsPresentation,
                    reports = reportsPresentation
                )
            }
        }
    }
}

data class HomeViewState(
    val wallets: List<WalletPresentation> = emptyList(),
    val reports: List<ReportPresentation> = emptyList(),
    val isLoading: Boolean = false
)