package com.lexwilliam.moneymanager.presentation.ui.report

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import com.lexwilliam.moneymanager.data.model.ReportType
import com.lexwilliam.moneymanager.domain.usecase.GetReportByIdUseCase
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
class ReportViewModel
@Inject constructor(
    private val getReportByIdUseCase: GetReportByIdUseCase,
    savedStateHandle: SavedStateHandle
): BaseViewModel() {

    override val coroutineExceptionHandler= CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
    }

    private var cacheJob: Job? = null

    override fun onCleared() {
        super.onCleared()
        cacheJob?.cancel()
    }

    private val reportIdByArgs = savedStateHandle.get<Int>("report_id")

    private val _state = MutableStateFlow(ReportViewState())
    val state = _state.asStateFlow()

    init {
        reportIdByArgs.let {
            cacheJob?.cancel()
            cacheJob = launchCoroutine {
                getReportByIdUseCase.invoke(it!!).collect { report ->
                    _state.value = _state.value.copy(report = report.toPresentation())
                }
            }
        }
    }
}

data class ReportViewState(
    val report: ReportPresentation = ReportPresentation(name = "", money = -1.0, color = "", reportType = ReportType.Default, timeAdded = 0)
)