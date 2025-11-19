package com.lokeshdawkar.expensetracker.model


data class Expense(
    val id: String? = null,
    val amount: Double,
    val description: String,
    val date: String,
    val category: String,
    val userId: String
)
