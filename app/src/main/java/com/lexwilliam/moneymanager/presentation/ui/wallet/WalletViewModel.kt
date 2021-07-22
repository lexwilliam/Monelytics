package com.lexwilliam.moneymanager.presentation.ui.wallet

import androidx.lifecycle.SavedStateHandle
import com.lexwilliam.moneymanager.domain.usecase.GetWalletByIdUseCase
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
class WalletViewModel
@Inject constructor(
    private val getWalletByIdUseCase: GetWalletByIdUseCase,
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

    private val walletNameFromArgs = savedStateHandle.get<String>("wallet_name")

    private val _state = MutableStateFlow(WalletViewState())
    val state = _state.asStateFlow()

    init {
        cacheJob?.cancel()
        cacheJob = launchCoroutine {
            walletNameFromArgs.let {
                if(it != "") {
                    getWalletByIdUseCase.invoke(it!!).collect { result ->
                        val wallet = result.toPresentation()
                        _state.value = _state.value.copy(wallet = wallet)
                    }
                }
            }
        }
    }
}

data class WalletViewState(
    val wallet: WalletPresentation = WalletPresentation(name = "", reports = emptyList())
)