package com.example.tp_aplicaciones_i;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_aplicaciones_i.adapter.ExpenseAdapter;
import com.example.tp_aplicaciones_i.model.Expense;
import com.example.tp_aplicaciones_i.model.ExpenseGroup;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerViewExpenses;
    private TextView textViewEmpty;
    private ExpenseAdapter expenseAdapter;
    private BottomNavigationView bottomNavigation;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
        initializeViews();
        setupRecyclerView();
        setupBottomNavigation();
        setupFloatingActionButton();

        loadSampleData();
    }

    private void initializeViews() {
        recyclerViewExpenses = findViewById(R.id.recyclerViewExpenses);
        textViewEmpty = findViewById(R.id.textViewEmpty);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        fab = findViewById(R.id.fab);
    }

    private void setupRecyclerView() {
        expenseAdapter = new ExpenseAdapter();
        recyclerViewExpenses.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewExpenses.setAdapter(expenseAdapter);
    }

    private void setupBottomNavigation() {
        bottomNavigation.setSelectedItemId(R.id.nav_home);
        bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    // Already on home
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
                // TODO: Navigate to add expense activity
            }
        });
    }

    private void loadSampleData() {
        // Create sample expenses data
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Date currentDate = new Date();
        
        List<Expense> todayExpenses = Arrays.asList(
            new Expense("Burger King", 12.50, currentDate, "14:30"),
            new Expense("Starbucks", 5.75, currentDate, "10:15"),
            new Expense("Uber", 25.00, currentDate, "18:45"),
            new Expense("McDonald's", 15.00, currentDate, "20:30")
        );

        List<Expense> yesterdayExpenses = Arrays.asList(
            new Expense("Groceries", 45.80, currentDate, "16:20"),
            new Expense("Gas Station", 35.00, currentDate, "08:45")
        );

        List<ExpenseGroup> expenseGroups = Arrays.asList(
            new ExpenseGroup("Hoy", todayExpenses),
            new ExpenseGroup("Ayer", yesterdayExpenses)
        );

        expenseAdapter.setExpenseGroups(expenseGroups);
        
        // Show/hide empty state
        if (expenseGroups.isEmpty()) {
            textViewEmpty.setVisibility(View.VISIBLE);
            recyclerViewExpenses.setVisibility(View.GONE);
        } else {
            textViewEmpty.setVisibility(View.GONE);
            recyclerViewExpenses.setVisibility(View.VISIBLE);
        }
    }
}