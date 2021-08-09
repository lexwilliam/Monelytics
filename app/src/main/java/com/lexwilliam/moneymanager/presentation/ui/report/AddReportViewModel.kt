package com.lexwilliam.moneymanager.presentation.ui.report

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import com.google.firebase.firestore.FirebaseFirestore
import com.lexwilliam.moneymanager.data.mapper.toEntity
import com.lexwilliam.moneymanager.data.model.ReportType
import com.lexwilliam.moneymanager.domain.usecase.InsertReportUseCase
import com.lexwilliam.moneymanager.presentation.base.BaseViewModel
import com.lexwilliam.moneymanager.presentation.mapper.toDomain
import com.lexwilliam.moneymanager.presentation.model.ReportCategory
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation
import com.lexwilliam.moneymanager.presentation.util.ExceptionHandler
import com.lexwilliam.moneymanager.presentation.util.categoryList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class AddReportViewModel
@Inject constructor(
    private val insertReportUseCase: InsertReportUseCase,
    savedStateHandle: SavedStateHandle
): BaseViewModel() {

    override val coroutineExceptionHandler= CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
    }

    private var cacheJob: Job? = null
    private var initJob: Job? = null

    override fun onCleared() {
        super.onCleared()
        cacheJob?.cancel()
    }

    private val walletNameFromArgs = savedStateHandle.get<String>("wallet_name")

    private var _state = MutableStateFlow(AddReportViewState())
    val state = _state.asStateFlow()

    init {
        initJob?.cancel()
        initJob = launchCoroutine {
            walletNameFromArgs.let {
                _state.value = _state.value.copy(walletName = it!!)
            }
        }
    }

    fun insertReport(report: ReportPresentation) {
        cacheJob?.cancel()
        cacheJob = launchCoroutine {
            insertReportUseCase.invoke(report.toDomain()).collect {
                if(it == -1L) {
                    Log.d("TAG", "Insert Failed")
                } else {
                    Log.d("TAG", "Insert Successful")
                }
            }
        }
    }
}

data class AddReportViewState(
    val walletName: String = ""
)