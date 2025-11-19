package com.example.expensetracker.controller;


import com.example.expensetracker.model.Expense;
import com.example.expensetracker.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@CrossOrigin("*")
public class ExpenseController {

    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    @PostMapping
    public Expense add(@RequestBody Expense expense) {
        return service.addExpense(expense);
    }

    @PutMapping("/{id}")
    public Expense update(@PathVariable String id, @RequestBody Expense expense) {
        return service.updateExpense(id, expense);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        service.deleteExpense(id);
        return "Deleted Successfully";
    }

    @GetMapping
    public List<Expense> list(
            @RequestParam String userId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String date
    ) {
        return service.getExpenses(userId, category, date);
    }
}
