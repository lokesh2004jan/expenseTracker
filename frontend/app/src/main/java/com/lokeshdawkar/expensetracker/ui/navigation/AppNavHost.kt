package com.lokeshdawkar.expensetracker.ui.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lokeshdawkar.expensetracker.ui.screens.*
import com.lokeshdawkar.expensetracker.ui.viewmodel.ExpenseViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    vm: ExpenseViewModel,
    windowSizeClass: WindowWidthSizeClass
) {

    NavHost(navController, startDestination = "list") {

        composable("list") {
            ExpenseListScreen(
                vm = vm,
                onAdd = { navController.navigate("add") },
                onEdit = { id -> navController.navigate("edit/$id") },
                onDashboard = { navController.navigate("dashboard") },
                windowSizeClass = windowSizeClass
            )
        }

        composable("add") {
            AddExpenseScreen(
                vm = vm,
                onBack = { navController.popBackStack() },
                windowSizeClass = windowSizeClass
            )
        }

        composable("edit/{id}") { backStack ->
            val id = backStack.arguments?.getString("id") ?: ""
            EditExpenseScreen(
                vm = vm,
                id = id,
                onBack = { navController.popBackStack() },
                windowSizeClass = windowSizeClass
            )
        }

        composable("dashboard") {
            DashboardScreen(
                vm = vm,
                onBack = { navController.popBackStack() },
                windowSizeClass = windowSizeClass
            )
        }
    }
}
