package com.lexwilliam.domain.usecase

import com.lexwilliam.local.repository.WalletRepository
import javax.inject.Inject

class GetAllReportUseCase
@Inject constructor(
    private val walletRepository: WalletRepository
) {
    suspend operator fun invoke() = walletRepository.getAllReport()
}