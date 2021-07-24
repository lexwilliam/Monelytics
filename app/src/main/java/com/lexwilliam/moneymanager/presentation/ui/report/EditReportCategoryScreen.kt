package com.lexwilliam.moneymanager.presentation.ui.report

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.lexwilliam.moneymanager.data.model.ReportType
import com.lexwilliam.moneymanager.presentation.model.ReportCategory
import com.lexwilliam.moneymanager.presentation.util.categoryList

@Composable
fun EditReportCategoryScreen(
    navToAddReport: (String) -> Unit
) {
//    var isSearchBarShown by remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            Text("Select Report Category") },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Search, contentDescription = null)
            }
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }
        }
    )

    var tabRowStatus by remember { mutableStateOf("Expense") }
    CategoryTabRow(status = tabRowStatus, setReportType = { tabRowStatus = it })

    var categoryList by remember { mutableStateOf(categoryList) }
    when(tabRowStatus) {
        "Expense" -> categoryList = categoryList.filter { it.reportType == ReportType.Expense }
        "Income" -> categoryList = categoryList.filter { it.reportType == ReportType.Income }
    }
    LazyColumn {
        items(items = categoryList) { item ->
            CategoryRow(category = item, onCategorySelected = { category -> navToAddReport(category) })
            Divider()
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
    ScrollableTabRow(
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
    onCategorySelected: (String) -> Unit
) {
    Row(
        modifier = modifier
            .padding(24.dp)
            .clickable { onCategorySelected(category.name) },
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier
            .clip(CircleShape)
            .background(category.color)
            .height(IntrinsicSize.Max))
        Text(text = category.name, style = MaterialTheme.typography.h6)
    }
}