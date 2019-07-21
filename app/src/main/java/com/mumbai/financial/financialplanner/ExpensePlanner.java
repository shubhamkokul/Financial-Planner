package com.mumbai.financial.financialplanner;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import planner.androidadapters.ExpenseIncomeMonthAdapter;
import planner.androidadapters.ExpenseIncomeYearAdapter;
import planner.androidadapters.PlanTypeAdapter;
import planner.db.FinancialDatabaseWriter;
import planner.db.modal.MonthModal;
import planner.db.modal.PlanTypeModal;
import planner.db.modal.YearModal;

public class ExpensePlanner extends AppCompatActivity {

    private static final String TAG = "ExpensePlanner";
    private Spinner planTypeSpinner, monthSpinner, yearSpinner;
    private Button saveExpense;
    private EditText descriptionEditText;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_planner);
        saveExpense = findViewById(R.id.saveExpense);
        planTypeSpinner = findViewById(R.id.planTypeSpinner);
        monthSpinner = findViewById(R.id.monthSpinner);
        yearSpinner = findViewById(R.id.yearSpinner);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        backButton = findViewById(R.id.backButton);

        SQLiteDatabase dbReader = new FinancialDatabaseWriter(getApplicationContext(), 1).getDatabaseReader();
        List<PlanTypeModal> planTypeModals = PlanTypeModal.returnAll(dbReader);
        PlanTypeAdapter planTypeAdapter = new PlanTypeAdapter(getApplicationContext(), planTypeModals);
        planTypeSpinner.setAdapter(planTypeAdapter);

        dbReader = new FinancialDatabaseWriter(getApplicationContext(), 1).getDatabaseReader();
        List<MonthModal> monthModals = MonthModal.returnAll(dbReader);
        ExpenseIncomeMonthAdapter expenseIncomeMonthAdapter = new ExpenseIncomeMonthAdapter(getApplicationContext(), monthModals);
        monthSpinner.setAdapter(expenseIncomeMonthAdapter);

        dbReader = new FinancialDatabaseWriter(getApplicationContext(), 1).getDatabaseReader();
        List<YearModal> yearModals = YearModal.returnAll(dbReader);
        ExpenseIncomeYearAdapter expenseIncomeYearAdapter = new ExpenseIncomeYearAdapter(getApplicationContext(), yearModals);
        yearSpinner.setAdapter(expenseIncomeYearAdapter);

        saveExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = SaveExpenseData();
                if (check) {

                } else {

                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public boolean SaveExpenseData() {
        boolean check = false;
        String selectedItem = planTypeSpinner.getSelectedItem().toString();
        if (selectedItem.equalsIgnoreCase("Monthly")) {
            String description = descriptionEditText.getText().toString().trim();
            String month = monthSpinner.getSelectedItem().toString();
            String year = yearSpinner.getSelectedItem().toString();

        } else if (selectedItem.equalsIgnoreCase("Other")) {

        }
        return check;
    }
}
