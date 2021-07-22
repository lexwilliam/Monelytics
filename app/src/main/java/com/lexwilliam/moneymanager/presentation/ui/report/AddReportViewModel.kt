package com.lexwilliam.moneymanager.presentation.ui.report

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.lexwilliam.moneymanager.domain.usecase.GetWalletByIdUseCase
import com.lexwilliam.moneymanager.domain.usecase.InsertReportUseCase
import com.lexwilliam.moneymanager.presentation.base.BaseViewModel
import com.lexwilliam.moneymanager.presentation.mapper.toDomain
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation
import com.lexwilliam.moneymanager.presentation.util.ExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
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

    override fun onCleared() {
        super.onCleared()
        cacheJob?.cancel()
    }

    private val walletIdFromArgs = savedStateHandle.get<String>("wallet_name")

    var walletName = MutableStateFlow("")

    init {
        walletIdFromArgs.let {
            walletName.value = it!!
        }
    }

    fun insertReport(report: ReportPresentation) {
        cacheJob?.cancel()
        cacheJob = launchCoroutine {
            insertReportUseCase.invoke(report.toDomain()).collect {
                if(it == -1L) {
                    Log.d("TAG", "Update Failed")
                } else {
                    Log.d("TAG", "Update Successful")
                }
            }
        }
    }
}