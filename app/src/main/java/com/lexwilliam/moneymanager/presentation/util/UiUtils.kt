package com.lexwilliam.moneymanager.presentation.util

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lexwilliam.moneymanager.R
import com.lexwilliam.moneymanager.data.model.ReportType
import com.lexwilliam.moneymanager.presentation.model.ReportCategory
import com.lexwilliam.moneymanager.presentation.model.WalletPresentation
import com.lexwilliam.moneymanager.presentation.ui.theme.BrightYellow
import com.lexwilliam.moneymanager.presentation.ui.theme.ForestGreen
import com.lexwilliam.moneymanager.presentation.ui.theme.Mint
import com.lexwilliam.moneymanager.presentation.ui.theme.YellowGreen
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

val cardWidth = 200.dp
val cardHeight = 270.dp

val thisMonth = convertLongToTime(System.currentTimeMillis(), "MMM yyyy", false)

val walletIconResources: List<Int> = listOf(
    R.drawable.account_balance_black_24dp,
    R.drawable.account_filled_balance_wallet_black_24dp,
    R.drawable.savings_black_24dp,
    R.drawable.store_black_24dp,
    R.drawable.shopping_bag_black_24dp,
    R.drawable.shopping_basket_black_24dp
)

fun fromColor(color: Color): String {
    return "${color.red}/${color.blue}/${color.green}"
}

fun convertStringToColor(str: String): Color {
    val arr = str.split("/")
    return Color(arr[0].toFloat(), arr[1].toFloat(), arr[2].toFloat())
}

data class IncomeExpenseSummary(
    val income: Double,
    val expense: Double,
    val total: Double = income + expense
)

fun getThisMonthSummary(wallets: List<WalletPresentation>): IncomeExpenseSummary {
    var income = +0.0
    var expense = -0.0
    wallets.forEach { wallet ->
        val groupedReport = wallet.reports.groupBy { convertLongToTime(it.timeAdded, "MMM yyyy", false) }
        groupedReport.forEach { month , reports ->
            if(month == thisMonth) {
                reports.forEach { report ->
                    when(report.reportType) {
                        ReportType.Income -> income += report.money
                        ReportType.Expense -> expense -= report.money
                        ReportType.Default -> Log.d("TAG", "${report.reportId} has no report type")
                    }
                }
            }
        }
    }
    return IncomeExpenseSummary(income, expense)
}

@SuppressLint("SimpleDateFormat")
fun convertLongToTime(time: Long, dateFormat: String, todayYesterday: Boolean = true): String {
    val date = Date(time)
    val simpleFormat = SimpleDateFormat(dateFormat)
    val result = simpleFormat.format(date)
    if(todayYesterday) {
        val formatter = DateTimeFormatter.ofPattern(dateFormat)
        val today = LocalDate.now().format(formatter)
        val yesterday = LocalDate.now().minus(1, ChronoUnit.DAYS).format(formatter)
        when(result) {
            today -> return "Today"
            yesterday -> return "Yesterday"
        }
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

val categoryList = listOf(
    ReportCategory("Bills & Utilities", Color.LightGray, ReportType.Expense),
    ReportCategory("Shopping", Color.Blue, ReportType.Expense),
    ReportCategory("Food", Color.Yellow, ReportType.Expense),
    ReportCategory("Transportation", Color.Black, ReportType.Expense),
    ReportCategory("Gifts & Donations", Color.Red, ReportType.Expense),
    ReportCategory("Travel", Color.Cyan, ReportType.Expense),
    ReportCategory("Family", Color.Magenta, ReportType.Expense),
    ReportCategory("Education", Color.Gray, ReportType.Expense),
    ReportCategory("Investment", Color.DarkGray, ReportType.Expense),
    ReportCategory("Business", Mint, ReportType.Expense),
    ReportCategory("Insurance", YellowGreen, ReportType.Expense),
    ReportCategory("Fees & Charges", Color(185,84,45), ReportType.Expense),
    ReportCategory("Withdrawal", Color(179,247,148), ReportType.Expense),
    ReportCategory("Other Expense", Color(165,250,231), ReportType.Expense),
    ReportCategory("Award", ForestGreen, ReportType.Income),
    ReportCategory("Interest Money", BrightYellow, ReportType.Income),
    ReportCategory("Salary", Color(111,194,139), ReportType.Income),
    ReportCategory("Gifts", Color.Green, ReportType.Income),
    ReportCategory("Selling", Color(49,77,173), ReportType.Income),
    ReportCategory("Other Income", Color(134,156,113), ReportType.Income)
)