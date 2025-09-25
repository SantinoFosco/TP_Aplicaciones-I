package com.example.tp_aplicaciones_i.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tp_aplicaciones_i.R;
import com.example.tp_aplicaciones_i.model.Expense;
import com.example.tp_aplicaciones_i.model.ExpenseGroup;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    
    private static final int TYPE_DATE_HEADER = 0;
    private static final int TYPE_EXPENSE = 1;
    
    private List<Object> items;
    private DecimalFormat currencyFormat;
    
    public ExpenseAdapter() {
        this.items = new ArrayList<>();
        this.currencyFormat = new DecimalFormat("$#,##0.00");
    }
    
    public void setExpenseGroups(List<ExpenseGroup> expenseGroups) {
        items.clear();
        for (ExpenseGroup group : expenseGroups) {
            items.add(group); // Add the header
            items.addAll(group.getExpenses()); // Add all expenses for this date
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof ExpenseGroup) {
            return TYPE_DATE_HEADER;
        } else {
            return TYPE_EXPENSE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        
        if (viewType == TYPE_DATE_HEADER) {
            View view = inflater.inflate(R.layout.item_date_header, parent, false);
            return new DateHeaderViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_expense, parent, false);
            return new ExpenseViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DateHeaderViewHolder) {
            ExpenseGroup group = (ExpenseGroup) items.get(position);
            ((DateHeaderViewHolder) holder).bind(group);
        } else if (holder instanceof ExpenseViewHolder) {
            Expense expense = (Expense) items.get(position);
            ((ExpenseViewHolder) holder).bind(expense, currencyFormat);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class DateHeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewDate;

        public DateHeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.textViewDate);
        }

        public void bind(ExpenseGroup group) {
            textViewDate.setText(group.getDate());
        }
    }

    static class ExpenseViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewAmount;
        private TextView textViewTime;

        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewExpenseName);
            textViewAmount = itemView.findViewById(R.id.textViewExpenseAmount);
            textViewTime = itemView.findViewById(R.id.textViewExpenseTime);
        }

        public void bind(Expense expense, DecimalFormat currencyFormat) {
            textViewName.setText(expense.getName());
            textViewAmount.setText(currencyFormat.format(expense.getAmount()));
            textViewTime.setText(expense.getTime());
        }
    }
}