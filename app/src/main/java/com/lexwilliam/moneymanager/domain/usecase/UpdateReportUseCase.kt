package com.lexwilliam.moneymanager.domain.usecase

import com.lexwilliam.moneymanager.data.repository.ReportRepository
import com.lexwilliam.moneymanager.domain.model.Report
import javax.inject.Inject

class UpdateReportUseCase
@Inject constructor(
    private val reportRepository: ReportRepository
) {
    suspend operator fun invoke(report: Report) = reportRepository.updateReport(report)
}