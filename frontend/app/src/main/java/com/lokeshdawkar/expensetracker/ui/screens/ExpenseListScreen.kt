package com.lokeshdawkar.expensetracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Assessment


import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lokeshdawkar.expensetracker.model.Expense
import com.lokeshdawkar.expensetracker.ui.viewmodel.ExpenseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseListScreen(
    vm: ExpenseViewModel,
    onAdd: () -> Unit,
    onEdit: (String) -> Unit,
    onDashboard: () -> Unit,
    windowSizeClass: WindowWidthSizeClass
) {
    val expenses by vm.expenses.collectAsState()

    val isTablet = windowSizeClass == WindowWidthSizeClass.Expanded

    val horizontalPadding = when (windowSizeClass) {
        WindowWidthSizeClass.Compact -> 16.dp
        WindowWidthSizeClass.Medium  -> 32.dp
        WindowWidthSizeClass.Expanded -> 80.dp
        else -> 16.dp
    }

    LaunchedEffect(Unit) { vm.loadExpenses("user123") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Expenses", style = MaterialTheme.typography.titleLarge) },
                actions = {
                    IconButton(onClick = onDashboard) {
                        Icon(Icons.Default.Assessment, contentDescription = "Dashboard")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAdd) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { padding ->

        Column(
            Modifier
                .padding(padding)
                .padding(horizontal = horizontalPadding, vertical = 16.dp)
                .fillMaxSize()
        ) {

            if (expenses.isEmpty()) {
                Text(
                    "No expenses yet.\nTap + to add one!",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 40.dp)
                )
                return@Column
            }

            if (isTablet) {
                LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
                    items(expenses) { e ->
                        ExpenseCard(e, onEdit, vm)
                    }
                })
            } else {
                LazyColumn {
                    items(expenses) { e ->
                        ExpenseCard(e, onEdit, vm)
                    }
                }
            }
        }
    }
}

@Composable
fun ExpenseCard(
    e: Expense,
    onEdit: (String) -> Unit,
    vm: ExpenseViewModel
) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(4.dp, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(Modifier.padding(20.dp)) {
            Text(e.description, style = MaterialTheme.typography.titleMedium)
            Text("₹${e.amount}", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
            Text("Category: ${e.category}")
            Text("Date: ${e.date}")

            Spacer(Modifier.height(8.dp))

            Row {
                TextButton(onClick = { onEdit(e.id!!) }) { Text("Edit") }
                TextButton(onClick = { vm.deleteExpense(e.id!!, "user123") }) {
                    Text("Delete", color = Color.Red)
                }
            }
        }
    }
}
