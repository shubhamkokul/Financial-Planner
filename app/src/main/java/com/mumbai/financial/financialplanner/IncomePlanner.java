package com.mumbai.financial.financialplanner;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.List;

import planner.androidadapters.ExpenseIncomeMonthAdapter;
import planner.androidadapters.ExpenseIncomeYearAdapter;
import planner.db.FinancialDatabaseWriter;
import planner.db.modal.MonthModal;
import planner.db.modal.YearModal;

public class IncomePlanner extends AppCompatActivity {
    private static final String TAG = "IncomePlanner";
    private Spinner monthSpinner, yearSpinner;
    private Button saveIncome;
    private EditText descriptionEditText;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_planner);
        monthSpinner = findViewById(R.id.monthSpinner);
        yearSpinner = findViewById(R.id.yearSpinner);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        saveIncome = findViewById(R.id.saveIncome);
        backButton = findViewById(R.id.backButton);

        SQLiteDatabase dbReader = new FinancialDatabaseWriter(getApplicationContext(), 1).getDatabaseReader();
        List<MonthModal> monthModals = MonthModal.returnAll(dbReader);
        ExpenseIncomeMonthAdapter expenseIncomeMonthAdapter = new ExpenseIncomeMonthAdapter(getApplicationContext(), monthModals);
        monthSpinner.setAdapter(expenseIncomeMonthAdapter);

        dbReader = new FinancialDatabaseWriter(getApplicationContext(), 1).getDatabaseReader();
        List<YearModal> yearModals = YearModal.returnAll(dbReader);
        ExpenseIncomeYearAdapter expenseIncomeYearAdapter = new ExpenseIncomeYearAdapter(getApplicationContext(), yearModals);
        yearSpinner.setAdapter(expenseIncomeYearAdapter);

        saveIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = SaveIncomeData();
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

    public boolean SaveIncomeData() {
        boolean check = false;
            String description = descriptionEditText.getText().toString().trim();
            String month = monthSpinner.getSelectedItem().toString();
            String year = yearSpinner.getSelectedItem().toString();
        return check;
    }
}
