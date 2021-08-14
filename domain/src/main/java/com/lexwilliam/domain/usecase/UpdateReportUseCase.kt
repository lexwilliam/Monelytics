package com.lexwilliam.domain.usecase

import com.lexwilliam.local.repository.WalletRepository
import com.lexwilliam.domain.model.Report
import javax.inject.Inject

class UpdateReportUseCase
@Inject constructor(
    private val walletRepository: WalletRepository
) {
    suspend operator fun invoke(report: Report) = walletRepository.updateReport(report)
}