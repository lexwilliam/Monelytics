package com.lexwilliam.moneymanager.presentation.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lexwilliam.moneymanager.presentation.model.WalletPresentation
import com.lexwilliam.moneymanager.presentation.util.convertDoubleToMoney
import com.lexwilliam.moneymanager.presentation.util.getThisMonthSummary
import com.lexwilliam.moneymanager.presentation.util.thisMonth

@Composable
fun ThisMonthIncomeExpenseTable(
    wallet: WalletPresentation
) {
}

@Composable
fun AllWalletSummary(
    modifier: Modifier = Modifier,
    wallets: List<WalletPresentation>
) {
    Column(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val summary = getThisMonthSummary(wallets)
        Text(text = thisMonth, style = MaterialTheme.typography.subtitle1)
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .weight(1f),
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .weight(3f),
                text = "Income"
            )
            Box(
                modifier = Modifier
                    .weight(2f),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(text = "+${convertDoubleToMoney(summary.income)}")
            }
        }
        Divider()
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .weight(1f),
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .weight(3f),
                text = "Expense"
            )
            Box(
                modifier = Modifier
                    .weight(2f),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(text = convertDoubleToMoney(summary.expense))
            }
        }
        Divider()
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .weight(1f),
                imageVector = Icons.Default.Check,
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .weight(3f),
                text = "Total"
            )
            Box(
                modifier = Modifier
                    .weight(2f),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(text = convertDoubleToMoney(summary.total))
            }
        }
    }
}