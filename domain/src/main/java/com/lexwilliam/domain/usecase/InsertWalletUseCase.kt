package com.lexwilliam.domain.usecase

import com.lexwilliam.local.repository.WalletRepository
import com.lexwilliam.domain.model.Wallet
import javax.inject.Inject

class InsertWalletUseCase
@Inject constructor(
    private val walletRepository: WalletRepository
) {
    suspend operator fun invoke(wallet: Wallet) = walletRepository.insertWallet(wallet)
}