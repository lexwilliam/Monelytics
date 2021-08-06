package com.lexwilliam.moneymanager.presentation.ui.analytic

import com.lexwilliam.moneymanager.domain.usecase.GetAllReportUseCase
import com.lexwilliam.moneymanager.presentation.base.BaseViewModel
import com.lexwilliam.moneymanager.presentation.mapper.toPresentation
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation
import com.lexwilliam.moneymanager.presentation.util.ExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class AnalyticViewModel
@Inject constructor(
    private val getAllReportUseCase: GetAllReportUseCase
): BaseViewModel() {

    override val coroutineExceptionHandler= CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
    }

    private var reportJob: Job? = null

    override fun onCleared() {
        super.onCleared()
        reportJob?.cancel()
    }

    private val _state = MutableStateFlow(AnalyticViewState())
    val state = _state.asStateFlow()

    init {
        reportJob?.cancel()
        reportJob = launchCoroutine {
            getAllReportUseCase.invoke().collect { results ->
                val reports = results.map { it.toPresentation() }
                _state.value = _state.value.copy(reports = reports)
            }
        }
    }
}

data class AnalyticViewState(
    val reports: List<ReportPresentation> = emptyList()
)