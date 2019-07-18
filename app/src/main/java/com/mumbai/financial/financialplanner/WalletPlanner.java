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

public class WalletPlanner extends AppCompatActivity {
    private Button saveWallet;
    private EditText nameEditText, currentAmountEditText;
    private Spinner iconSpinner;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_planner);
        saveWallet = findViewById(R.id.saveWallet);
        nameEditText = findViewById(R.id.nameEditText);
        currentAmountEditText = findViewById(R.id.currentAmountEditText);
        iconSpinner = findViewById(R.id.iconSpinner);
        backButton = findViewById(R.id.backButton);

        String[] iconSpinnerArray = {"Bank", "Cash", "Credit-Card", "Gift-Card"};

        ArrayAdapter<String> iconSpinnerAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, iconSpinnerArray
        );
        iconSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        iconSpinner.setAdapter(iconSpinnerAdapter);

        saveWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = SaveWalletData();
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

    private boolean SaveWalletData() {
        boolean check = false;
        String description = nameEditText.getText().toString().trim();
        String icon = iconSpinner.getSelectedItem().toString();
        String amount = currentAmountEditText.getText().toString().trim();
        return check;
    }
}
