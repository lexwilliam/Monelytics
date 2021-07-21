package com.lexwilliam.moneymanager.presentation.ui.analytic

import com.lexwilliam.moneymanager.presentation.base.BaseViewModel
import com.lexwilliam.moneymanager.presentation.util.ExceptionHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

class AnalyticViewModel
@Inject constructor(

): BaseViewModel() {

    override val coroutineExceptionHandler= CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
    }

}