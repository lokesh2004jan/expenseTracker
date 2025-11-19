package com.lokeshdawkar.expensetracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lokeshdawkar.expensetracker.model.Expense
import com.lokeshdawkar.expensetracker.ui.viewmodel.ExpenseViewModel

@Composable
fun AddExpenseScreen(
    vm: ExpenseViewModel,
    onBack: () -> Unit,
    windowSizeClass: WindowWidthSizeClass
) {
    var amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    val formWidth = when (windowSizeClass) {
        WindowWidthSizeClass.Compact -> Modifier.fillMaxWidth()
        WindowWidthSizeClass.Medium  -> Modifier.width(400.dp)
        WindowWidthSizeClass.Expanded -> Modifier.width(600.dp)
        else -> Modifier.fillMaxWidth()
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(formWidth) {

            Text("Add New Expense", style = MaterialTheme.typography.headlineMedium)

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(amount, { amount = it }, label = { Text("Amount") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(description, { description = it }, label = { Text("Description") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(date, { date = it }, label = { Text("Date (YYYY-MM-DD)") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(category, { category = it }, label = { Text("Category") }, modifier = Modifier.fillMaxWidth())

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = {
                    val exp = Expense(
                        amount = amount.toDouble(),
                        description = description,
                        date = date,
                        category = category,
                        userId = "user123"
                    )
                    vm.addExpense(exp) { onBack() }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Save Expense")
            }
        }
    }
}
