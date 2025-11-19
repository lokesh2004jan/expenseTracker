package com.example.expensetracker.service;



import com.example.expensetracker.model.Expense;
import com.example.expensetracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private final ExpenseRepository repo;

    public ExpenseService(ExpenseRepository repo) {
        this.repo = repo;
    }

    public Expense addExpense(Expense expense) {
        return repo.save(expense);
    }

    public Expense updateExpense(String id, Expense expense) {
        expense.setId(id);
        return repo.save(expense);
    }

    public void deleteExpense(String id) {
        repo.deleteById(id);
    }

    public List<Expense> getExpenses(String userId, String category, String date) {
        List<Expense> expenses = repo.findByUserId(userId);

        if (category != null && !category.isEmpty())
            expenses = expenses.stream()
                    .filter(e -> e.getCategory().equalsIgnoreCase(category))
                    .collect(Collectors.toList());

        if (date != null && !date.isEmpty())
            expenses = expenses.stream()
                    .filter(e -> e.getDate().equals(date))
                    .collect(Collectors.toList());

        return expenses;
    }
}
