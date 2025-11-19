package com.lokeshdawkar.expensetracker

import com.lokeshdawkar.expensetracker.ui.navigation.AppNavHost
import com.lokeshdawkar.expensetracker.ui.viewmodel.ExpenseViewModel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.lokeshdawkar.expensetracker.ui.navigation.AppNavHost


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            val navController = rememberNavController()
            val vm: ExpenseViewModel = viewModel()

            AppNavHost(
                navController = navController,
                vm = vm,
                windowSizeClass = windowSizeClass.widthSizeClass
            )
        }
    }
}


