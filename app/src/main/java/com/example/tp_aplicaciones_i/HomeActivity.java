package com.example.tp_aplicaciones_i;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout expenseContainer;
    private TextView textViewEmpty;
    private BottomNavigationView bottomNavigation;
    private FloatingActionButton fab;
    private Map<String, Map<String, Double>> expenseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
        initializeViews();
        initializeData();
        setupBottomNavigation();
        setupFloatingActionButton();

        loadSampleData();
    }

    private void initializeViews() {
        expenseContainer = findViewById(R.id.recyclerViewExpenses);
        textViewEmpty = findViewById(R.id.textViewEmpty);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        fab = findViewById(R.id.fab);
    }

    private void initializeData() {
        expenseData = new HashMap<>();
    }

    private void setupBottomNavigation() {
        bottomNavigation.setSelectedItemId(R.id.nav_home);
        bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    return true;
                } else if (itemId == R.id.nav_analysis) {
                    Toast.makeText(HomeActivity.this, "Análisis - En desarrollo", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemId == R.id.nav_profile) {
                    Toast.makeText(HomeActivity.this, "Perfil - En desarrollo", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
    }

    private void setupFloatingActionButton() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Agregar nuevo gasto - En desarrollo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadSampleData() {
        Map<String, Double> todayExpenses = new HashMap<>();
        todayExpenses.put("Burger King", 12.50);
        todayExpenses.put("Starbucks", 5.75);
        todayExpenses.put("Uber", 25.00);
        todayExpenses.put("McDonald's", 15.00);
        todayExpenses.put("Farmacia", 8.90);
        todayExpenses.put("Supermercado", 32.45);
        todayExpenses.put("Netflix", 9.99);

        Map<String, Double> yesterdayExpenses = new HashMap<>();
        yesterdayExpenses.put("Groceries", 45.80);
        yesterdayExpenses.put("Gas Station", 35.00);
        yesterdayExpenses.put("Pizza Hut", 18.75);
        yesterdayExpenses.put("Cine", 12.00);
        yesterdayExpenses.put("Taxi", 8.50);

        Map<String, Double> thisWeekExpenses = new HashMap<>();
        thisWeekExpenses.put("Rent", 800.00);
        thisWeekExpenses.put("Internet", 45.00);
        thisWeekExpenses.put("Electricity", 85.30);
        thisWeekExpenses.put("Water", 25.60);
        thisWeekExpenses.put("Phone Bill", 30.00);
        thisWeekExpenses.put("Gym", 40.00);

        Map<String, Double> lastWeekExpenses = new HashMap<>();
        lastWeekExpenses.put("Clothing Store", 89.99);
        lastWeekExpenses.put("Amazon", 65.50);
        lastWeekExpenses.put("Restaurant", 42.30);
        lastWeekExpenses.put("Coffee Shop", 4.25);
        lastWeekExpenses.put("Parking", 15.00);
        lastWeekExpenses.put("Books", 28.75);

        expenseData.put("Hoy", todayExpenses);
        expenseData.put("Ayer", yesterdayExpenses);
        expenseData.put("Esta Semana", thisWeekExpenses);
        expenseData.put("Semana Pasada", lastWeekExpenses);

        displayExpenses();
    }

    private void displayExpenses() {
        expenseContainer.removeAllViews();
        DecimalFormat currencyFormat = new DecimalFormat("$#,##0.00");

        if (expenseData.isEmpty()) {
            textViewEmpty.setVisibility(View.VISIBLE);
            expenseContainer.setVisibility(View.GONE);
            return;
        }

        textViewEmpty.setVisibility(View.GONE);
        expenseContainer.setVisibility(View.VISIBLE);

        for (Map.Entry<String, Map<String, Double>> dayEntry : expenseData.entrySet()) {
            String dayName = dayEntry.getKey();
            Map<String, Double> expenses = dayEntry.getValue();

            double dayTotal = 0;
            for (double amount : expenses.values()) {
                dayTotal += amount;
            }

            View dayHeaderView = getLayoutInflater().inflate(R.layout.item_day_header, expenseContainer, false);
            TextView tvDayName = dayHeaderView.findViewById(R.id.tvDayName);
            
            tvDayName.setText(dayName);
            
            expenseContainer.addView(dayHeaderView);

            String[] hours = {"10:30", "14:15", "16:45", "18:20", "09:10", "12:30", "20:15"};
            int hourIndex = 0;
            
            for (Map.Entry<String, Double> expense : expenses.entrySet()) {
                View expenseView = getLayoutInflater().inflate(R.layout.item_expense, expenseContainer, false);
                TextView tvExpenseName = expenseView.findViewById(R.id.tvExpenseName);
                TextView tvExpenseAmount = expenseView.findViewById(R.id.tvExpenseAmount);
                TextView tvExpenseHour = expenseView.findViewById(R.id.tvExpenseHour);
                
                tvExpenseName.setText(expense.getKey());
                tvExpenseAmount.setText(currencyFormat.format(expense.getValue()));
                tvExpenseHour.setText(hours[hourIndex % hours.length]);
                hourIndex++;
                
                expenseContainer.addView(expenseView);
            }
        }
    }
}