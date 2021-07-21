package com.lexwilliam.moneymanager.presentation.ui.profile

import com.lexwilliam.moneymanager.presentation.base.BaseViewModel
import com.lexwilliam.moneymanager.presentation.util.ExceptionHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

class ProfileViewModel
@Inject constructor(

): BaseViewModel() {

    override val coroutineExceptionHandler= CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
    }

}