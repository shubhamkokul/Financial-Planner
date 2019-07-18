package com.mumbai.financial.financialplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class AddExpense extends AppCompatActivity {

    private ImageView backButton;
    private Spinner accountTypeSpinner, planTypeSpinner, categoryTypeSpinner, dateSpinner;
    private EditText amountEditText, noteEditText;
    private Button addExpensePlanButton, saveExpense;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        backButton = findViewById(R.id.backButton);
        accountTypeSpinner = findViewById(R.id.accountTypeSpinner);
        planTypeSpinner = findViewById(R.id.planTypeSpinner);
        categoryTypeSpinner = findViewById(R.id.categoryTypeSpinner);
        dateSpinner = findViewById(R.id.dateSpinner);
        amountEditText = findViewById(R.id.amountEditText);
        noteEditText = findViewById(R.id.noteEditText);
        addExpensePlanButton = findViewById(R.id.addExpensePlanButton);
        saveExpense = findViewById(R.id.saveExpense);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
