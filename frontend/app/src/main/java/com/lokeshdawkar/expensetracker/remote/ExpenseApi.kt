package com.lokeshdawkar.expensetracker.remote

import com.lokeshdawkar.expensetracker.model.Expense
import retrofit2.http.*

interface ExpenseApi {

    @GET("expenses")
    suspend fun getExpenses(
        @Query("userId") userId: String,
        @Query("category") category: String? = null,
        @Query("date") date: String? = null
    ): List<Expense>

    @POST("expenses")
    suspend fun addExpense(@Body expense: Expense): Expense

    @PUT("expenses/{id}")
    suspend fun updateExpense(
        @Path("id") id: String,
        @Body expense: Expense
    ): Expense

    @DELETE("expenses/{id}")
    suspend fun deleteExpense(@Path("id") id: String)
}
