package com.lexwilliam.moneymanager.domain.usecase

import com.lexwilliam.moneymanager.data.repository.ReportRepository
import javax.inject.Inject

class GetAllReportUseCase
@Inject constructor(
    private val reportRepository: ReportRepository
) {
    suspend operator fun invoke() = reportRepository.getAllReport()
}