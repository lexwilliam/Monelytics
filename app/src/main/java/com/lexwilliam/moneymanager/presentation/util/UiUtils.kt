package com.lexwilliam.moneymanager.presentation.util

import android.annotation.SuppressLint
import androidx.compose.ui.unit.dp
import com.lexwilliam.moneymanager.data.model.ReportType
import com.lexwilliam.moneymanager.presentation.model.WalletPresentation
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

val cardWidth = 200.dp
val cardHeight = 230.dp

val thisMonth = convertLongToTime(System.currentTimeMillis(), "MMM yyyy")

@SuppressLint("SimpleDateFormat")
fun convertLongToTime(time: Long, dateFormat: String): String {
    val date = Date(time)
    val simpleFormat = SimpleDateFormat(dateFormat)
    val result = simpleFormat.format(date)
    val formatter = DateTimeFormatter.ofPattern(dateFormat)
    val today = LocalDate.now().format(formatter)
    val tomorrow = LocalDate.now().plus(1, ChronoUnit.DAYS).format(formatter)
    when(result) {
        today -> return "Today"
        tomorrow -> return "Tomorrow"
    }
    return result
}

fun walletTotalBalance(wallet: WalletPresentation): Double {
    var totalMoney = 0.0
    wallet.reports.forEach {
        totalMoney += it.money
    }
    return totalMoney
}

fun allWalletTotalBalance(wallets: List<WalletPresentation>): Double {
    var totalBalance = 0.0
    wallets.forEach { wallet ->
        wallet.reports.forEach { report ->
            totalBalance += report.money
        }
    }
    return totalBalance
}

fun getWalletIncome(wallet: WalletPresentation): Double {
    var totalMoney = 0.0
    wallet.reports.forEach { report ->
        if(report.reportType == ReportType.Income) {
            totalMoney += report.money
        }
    }
    return totalMoney
}

fun getWalletExpense(wallet: WalletPresentation): Double {
    var totalMoney = 0.0
    wallet.reports.forEach { report ->
        if(report.reportType == ReportType.Expense) {
            totalMoney += report.money
        }
    }
    return totalMoney
}

fun convertDoubleToMoneyFormat(num: Double): String {
    val numberFormat = NumberFormat.getCurrencyInstance()
    return numberFormat.format(num)
}