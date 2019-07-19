package com.mumbai.financial.financialplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

public class AddIncome extends AppCompatActivity {

    private ImageView backButton;
    private Spinner accountTypeSpinner, planTypeSpinner, categoryTypeSpinner, dateSpinner;
    private EditText amountEditText;
    private Button addIncomePlanButton, saveIncome;
    private Switch planSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        backButton = findViewById(R.id.backButton);
        accountTypeSpinner = findViewById(R.id.accountTypeSpinner);
        planTypeSpinner = findViewById(R.id.planTypeSpinner);
        categoryTypeSpinner = findViewById(R.id.categoryTypeSpinner);
        dateSpinner = findViewById(R.id.dateSpinner);
        amountEditText = findViewById(R.id.amountEditText);
        planSwitch = findViewById(R.id.planSwitch);
        addIncomePlanButton = findViewById(R.id.addIncomePlanButton);
        saveIncome = findViewById(R.id.saveIncome);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
