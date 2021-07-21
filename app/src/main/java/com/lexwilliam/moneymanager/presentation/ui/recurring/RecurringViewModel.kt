package com.lexwilliam.moneymanager.presentation.ui.recurring

import com.lexwilliam.moneymanager.presentation.base.BaseViewModel
import com.lexwilliam.moneymanager.presentation.util.ExceptionHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

class RecurringViewModel
@Inject constructor(

): BaseViewModel() {

    override val coroutineExceptionHandler= CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
    }

}