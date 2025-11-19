package com.lokeshdawkar.expensetracker.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lokeshdawkar.expensetracker.model.Expense
import com.lokeshdawkar.expensetracker.repository.ExpenseRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExpenseViewModel : ViewModel() {

    private val repo = ExpenseRepository()

    private val _expenses = MutableStateFlow<List<Expense>>(emptyList())
    val expenses: StateFlow<List<Expense>> = _expenses

    fun loadExpenses(userId: String, category: String? = null, date: String? = null) {
        viewModelScope.launch {
            _expenses.value = repo.getExpenses(userId, category, date)
        }
    }

    fun addExpense(expense: Expense, onDone: () -> Unit) {
        viewModelScope.launch {
            repo.addExpense(expense)
            onDone()
            loadExpenses(expense.userId)
        }
    }

    fun updateExpense(id: String, exp: Expense, onDone: () -> Unit) {
        viewModelScope.launch {
            repo.updateExpense(id, exp)
            onDone()
            loadExpenses(exp.userId)
        }
    }

    fun deleteExpense(id: String, userId: String) {
        viewModelScope.launch {
            repo.deleteExpense(id)
            loadExpenses(userId)
        }
    }
}
