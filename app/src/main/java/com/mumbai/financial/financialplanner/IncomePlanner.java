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

        String[] monthSpinnerArray = {"January", "February", "March", "April", "May", "June", "July"
                , "August", "September", "October", "November", "December"};
        String[] yearSpinnerArray = {"2019", "2020", "2021", "2022", "2023", "2024"};

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
