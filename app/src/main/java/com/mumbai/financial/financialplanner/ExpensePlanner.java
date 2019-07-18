package com.mumbai.financial.financialplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

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

        String[] planTypeSpinnerArray = {"Monthly", "Other"};
        String[] monthSpinnerArray = {"January", "February", "March", "April", "May", "June", "July"
                , "August", "September", "October", "November", "December"};
        String[] yearSpinnerArray = {"2019", "2020", "2021", "2022", "2023", "2024"};

        ArrayAdapter<String> planSpinnerAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, planTypeSpinnerArray
        );
        planSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        planTypeSpinner.setAdapter(planSpinnerAdapter);

        ArrayAdapter<String> monthSpinnerAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, monthSpinnerArray
        );
        monthSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthSpinnerAdapter);

        ArrayAdapter<String> yearSpinnerAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, yearSpinnerArray
        );
        yearSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearSpinnerAdapter);

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
