package com.lokeshdawkar.expensetracker.ui.screens



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lokeshdawkar.expensetracker.model.Expense
import com.lokeshdawkar.expensetracker.ui.viewmodel.ExpenseViewModel

@Composable
fun EditExpenseScreen(
    vm: ExpenseViewModel,
    id: String,
    onBack: () -> Unit,
    windowSizeClass: WindowWidthSizeClass
) {
    val expenses by vm.expenses.collectAsState()
    val expense = expenses.find { it.id == id } ?: return

    var amount by remember { mutableStateOf(expense.amount.toString()) }
    var description by remember { mutableStateOf(expense.description) }
    var date by remember { mutableStateOf(expense.date) }
    var category by remember { mutableStateOf(expense.category) }

    Column(Modifier.padding(20.dp)) {

        Text("Edit Expense", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(value = amount, onValueChange = { amount = it }, label = { Text("Amount") })
        OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
        OutlinedTextField(value = date, onValueChange = { date = it }, label = { Text("Date") })
        OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("Category") })

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                val updated = Expense(
                    id = expense.id,
                    amount = amount.toDouble(),
                    description = description,
                    date = date,
                    category = category,
                    userId = "user123"
                )
                vm.updateExpense(id, updated) { onBack() }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Update Expense")
        }
    }
}
