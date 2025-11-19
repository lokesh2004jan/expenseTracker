package com.lokeshdawkar.expensetracker.remote



import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: ExpenseApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.101.10.40:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExpenseApi::class.java)
    }
}
