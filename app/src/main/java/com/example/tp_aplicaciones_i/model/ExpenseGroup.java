package com.example.tp_aplicaciones_i.model;

import java.util.List;

public class ExpenseGroup {
    private String date;
    private List<Expense> expenses;

    public ExpenseGroup(String date, List<Expense> expenses) {
        this.date = date;
        this.expenses = expenses;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }
}