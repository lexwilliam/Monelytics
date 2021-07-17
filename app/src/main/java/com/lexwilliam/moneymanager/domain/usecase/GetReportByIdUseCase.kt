package com.lexwilliam.moneymanager.domain.usecase

import com.lexwilliam.moneymanager.data.repository.WalletRepository
import javax.inject.Inject

class GetReportByIdUseCase
@Inject constructor(
    private val walletRepository: WalletRepository
) {
    suspend operator fun invoke(reportId: Int) = walletRepository.getReportById(reportId)
}