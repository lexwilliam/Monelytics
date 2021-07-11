package com.lexwilliam.moneymanager.domain.usecase

import com.lexwilliam.moneymanager.data.repository.ReportRepository
import javax.inject.Inject

class GetReportByNameUseCase
@Inject constructor(
    private val reportRepository: ReportRepository
) {
    suspend operator fun invoke(itemName: String) = reportRepository.getReportByName(itemName)
}