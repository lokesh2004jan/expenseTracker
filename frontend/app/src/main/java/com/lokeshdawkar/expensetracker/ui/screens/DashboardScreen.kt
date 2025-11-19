package com.lokeshdawkar.expensetracker.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lokeshdawkar.expensetracker.ui.viewmodel.ExpenseViewModel

@Composable
fun DashboardScreen(vm: ExpenseViewModel, onBack: () -> Unit, windowSizeClass: WindowWidthSizeClass) {

    val expenses by vm.expenses.collectAsState()

    val total = expenses.sumOf { it.amount }
    val categoryTotals = expenses.groupBy { it.category }
        .mapValues { it.value.sumOf { e -> e.amount } }

    Column(Modifier.padding(20.dp)) {

        Text("Dashboard", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(10.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(Modifier.padding(20.dp)) {
                Text("Total Spending", style = MaterialTheme.typography.titleMedium)
                Text("₹$total", style = MaterialTheme.typography.headlineMedium)
            }
        }

        Spacer(Modifier.height(20.dp))

        Text("Category Breakdown", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(12.dp))

        categoryTotals.forEach { (cat, amt) ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(cat, style = MaterialTheme.typography.titleMedium)
                    Text("₹$amt", style = MaterialTheme.typography.titleMedium)
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Back")
        }
    }
}
