package com.lexwilliam.moneymanager.domain.usecase

import com.lexwilliam.moneymanager.data.repository.WalletRepository
import javax.inject.Inject

class GetReportByNameUseCase
@Inject constructor(
    private val walletRepository: WalletRepository
) {
    suspend operator fun invoke(itemName: String) = walletRepository.getWalletByName(itemName)
}