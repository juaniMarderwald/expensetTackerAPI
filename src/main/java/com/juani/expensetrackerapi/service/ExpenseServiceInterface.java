package com.juani.expensetrackerapi.service;

import com.juani.expensetrackerapi.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;

public interface ExpenseServiceInterface {
    public Page<Expense> getAllExpenses(Pageable page);

    public Expense getExpenseById(Long id);

    public void deleteExpenseById(Long id);

    public Expense saveExpenseDetails(Expense expense);

    public Expense updateExpenseDetails(Long id, Expense expense);

    List<Expense> readByCategory(String category, Pageable page);

    List<Expense> readByName(String keyword, Pageable page);

    List<Expense> readByDate(Date startDate, Date endDate, Pageable page);
}
