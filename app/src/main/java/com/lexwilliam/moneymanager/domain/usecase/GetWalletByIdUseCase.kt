package com.lexwilliam.moneymanager.domain.usecase

import com.lexwilliam.moneymanager.data.repository.WalletRepository
import javax.inject.Inject

class GetWalletByIdUseCase
@Inject constructor(
    private val walletRepository: WalletRepository
) {
    suspend operator fun invoke(walletId: Int) = walletRepository.getWalletWithReportById(walletId)
}