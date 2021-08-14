package com.lexwilliam.login

import androidx.lifecycle.viewModelScope
import com.lexwilliam.domain.usecase.GetAllWalletsFromFirestore
import com.lexwilliam.moneymanager.presentation.base.BaseViewModel
import com.lexwilliam.moneymanager.presentation.util.ExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val getAllWalletsFromFirestore: GetAllWalletsFromFirestore
): BaseViewModel() {
    override val coroutineExceptionHandler= CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
    }

    fun fetchDataFromFirestore() {
        viewModelScope.launch {
            getAllWalletsFromFirestore.invoke().collect {}
        }
    }
}