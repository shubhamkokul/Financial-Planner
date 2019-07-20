package com.mumbai.financial.financialplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class IncomeDetailView extends AppCompatActivity {
    private TextView position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_detail_view);
        Bundle intent = getIntent().getExtras();
        int i = intent.getInt("position");
        position = findViewById(R.id.position);
        position.setText(i+"");
    }
}