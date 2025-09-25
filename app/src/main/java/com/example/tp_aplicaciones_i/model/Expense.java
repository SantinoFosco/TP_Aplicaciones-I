package com.example.tp_aplicaciones_i.model;

import java.util.Date;

public class Expense {
    private String name;
    private double amount;
    private Date date;
    private String time;

    public Expense(String name, double amount, Date date, String time) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}