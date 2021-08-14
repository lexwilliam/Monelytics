package com.lexwilliam.domain.usecase

import com.lexwilliam.local.repository.WalletRepository
import javax.inject.Inject

class GetWalletByIdUseCase
@Inject constructor(
    private val walletRepository: WalletRepository
) {
    suspend operator fun invoke(walletName: String) = walletRepository.getWalletWithReportById(walletName)
}