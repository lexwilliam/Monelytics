package com.lexwilliam.moneymanager.presentation.ui.report

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lexwilliam.local.model.ReportType
import com.lexwilliam.moneymanager.model.ReportCategory
import com.lexwilliam.designsystem.component.ReportIcon
import com.lexwilliam.moneymanager.presentation.util.categoryList

@Composable
fun EditReportCategoryScreen(
    setCategory: (ReportCategory) -> Unit
) {
//    var isSearchBarShown by remember { mutableStateOf(false) }
    Column {
        var tabRowStatus by remember { mutableStateOf("Expense") }
        CategoryTabRow(status = tabRowStatus, setReportType = {
            tabRowStatus = it
            Log.d("TAG", tabRowStatus)
        })

        var category by remember { mutableStateOf(categoryList) }
        when(tabRowStatus) {
            "Expense" -> category = categoryList.filter { it.reportType == ReportType.Expense }
            "Income" -> category = categoryList.filter { it.reportType == ReportType.Income }
        }
        Log.d("TAG", category.toString())
        LazyColumn {
            items(items = category) { item ->
                CategoryRow(category = item, onCategorySelected = { category -> setCategory(category) })
                Divider()
            }
        }
    }
}

@Composable
fun CategoryTabRow(
    status: String,
    setReportType: (String) -> Unit
) {
    val categoryTabRow = listOf("Expense", "Income")
    var currentIndex by remember { mutableStateOf(categoryTabRow.indexOf(status)) }
    TabRow(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.background,
        selectedTabIndex = currentIndex
    ) {
        categoryTabRow.forEachIndexed { index, status ->
            Tab(
                selected = index == currentIndex,
                onClick = {
                    currentIndex = index
                    setReportType(status)
                }
            ) {
                Box(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = status, style = MaterialTheme.typography.subtitle1)
                }
            }
        }
    }
}

@Composable
fun CategoryRow(
    modifier: Modifier = Modifier,
    category: ReportCategory,
    onCategorySelected: (ReportCategory) -> Unit
) {
    Row(
        modifier = modifier
            .padding(24.dp)
            .clickable { onCategorySelected(category) },
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ReportIcon(iconId = category.iconId)
        Text(text = category.name, style = MaterialTheme.typography.subtitle1)
    }
}

@Preview
@Composable
fun EditReportCategoryPreveiw() {
    EditReportCategoryScreen(
        setCategory = {}
    )
}