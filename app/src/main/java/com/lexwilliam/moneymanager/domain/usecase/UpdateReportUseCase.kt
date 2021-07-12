package com.lexwilliam.moneymanager.domain.usecase

import com.lexwilliam.moneymanager.data.repository.WalletRepository
import com.lexwilliam.moneymanager.domain.model.Report
import com.lexwilliam.moneymanager.domain.model.Wallet
import javax.inject.Inject

class UpdateReportUseCase
@Inject constructor(
    private val walletRepository: WalletRepository
) {
    suspend operator fun invoke(wallet: Wallet) = walletRepository.updateWallet(wallet)
}