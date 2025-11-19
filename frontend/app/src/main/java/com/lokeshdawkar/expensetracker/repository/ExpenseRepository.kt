package com.lokeshdawkar.expensetracker.repository

import com.lokeshdawkar.expensetracker.model.Expense
import com.lokeshdawkar.expensetracker.remote.RetrofitInstance


class ExpenseRepository {
    private val api = RetrofitInstance.api

    suspend fun getExpenses(userId: String, category: String?, date: String?) =
        api.getExpenses(userId, category, date)

    suspend fun addExpense(expense: Expense) =
        api.addExpense(expense)

    suspend fun updateExpense(id: String, expense: Expense) =
        api.updateExpense(id, expense)

    suspend fun deleteExpense(id: String) =
        api.deleteExpense(id)
}
