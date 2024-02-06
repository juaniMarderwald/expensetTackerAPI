package com.juani.expensetrackerapi.controller;

import com.juani.expensetrackerapi.entity.Expense;
import com.juani.expensetrackerapi.service.implementation.ExpenseService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("expenses")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @GetMapping("")
    public List<Expense> getAllExpenses(Pageable page){
        return expenseService.getAllExpenses(page).toList();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable("id") Long id){
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("")
    public String deleteExpenseById(@RequestParam("id")Long id){
        expenseService.deleteExpenseById(id);
        return "Deleted correctly";
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("")
    public Expense saveExpenseDetails(@Valid @RequestBody Expense expense){
        return expenseService.saveExpenseDetails(expense) ;
    }

    @PutMapping("/{id}")
    public Expense updateExpenseDetails(@RequestBody Expense expense, @PathVariable Long id){
        return expenseService.updateExpenseDetails(id,expense);
    }

    @GetMapping("/category")
    public List<Expense> getExpenseByCategory(@RequestParam String category, Pageable page){
        return expenseService.readByCategory(category, page);
    }

    @GetMapping("/name")
    public List<Expense> getExpensesByName(@RequestParam String keyword, Pageable page){
        return expenseService.readByName(keyword,page);
    }

    @GetMapping("/date")
    public List<Expense> getExpensesByDates(@RequestParam(required = false) Date startDate,
                                            @RequestParam(required = false) Date endDate,
                                            Pageable page){
        return expenseService.readByDate(startDate, endDate, page);
    }
}
