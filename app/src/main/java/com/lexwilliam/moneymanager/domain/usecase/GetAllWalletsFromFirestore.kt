package com.lexwilliam.moneymanager.domain.usecase

import com.lexwilliam.moneymanager.data.repository.WalletRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class GetAllWalletsFromFirestore
@Inject constructor(
    private val walletRepository: WalletRepository
) {
    @ExperimentalCoroutinesApi
    suspend operator fun invoke() = walletRepository.getWalletsFromFirestore()
}