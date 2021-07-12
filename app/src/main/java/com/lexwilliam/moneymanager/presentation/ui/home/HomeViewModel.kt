package com.lexwilliam.moneymanager.presentation.ui.home

import com.lexwilliam.moneymanager.domain.usecase.GetAllReportUseCase
import com.lexwilliam.moneymanager.presentation.base.BaseViewModel
import com.lexwilliam.moneymanager.presentation.mapper.toPresentation
import com.lexwilliam.moneymanager.presentation.model.WalletPresentation
import com.lexwilliam.moneymanager.presentation.util.ExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val getAllReportUseCase: GetAllReportUseCase
): BaseViewModel() {

    override val coroutineExceptionHandler= CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
    }

    private var cacheJob: Job? = null

    override fun onCleared() {
        super.onCleared()
        cacheJob?.cancel()
    }

    private var _state = MutableStateFlow(HomeViewState())
    val state = _state.asStateFlow()

    init {
        cacheJob?.cancel()
        cacheJob = launchCoroutine {
            getAllReportUseCase.invoke().collect { results ->
                val reports = results.map { it.toPresentation() }
                _state.value = _state.value.copy(reports = reports)
            }
        }
    }
}

data class HomeViewState(
    val reports: List<WalletPresentation> = emptyList()
)