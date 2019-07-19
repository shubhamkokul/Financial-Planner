package com.mumbai.financial.financialplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import planner.db.FinancialPlannerDataBase;

public class WelcomeScreen extends AppCompatActivity {
    private int SPLASH_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        Thread timer = new Thread() {
            public void run() {
                try {
                    FinancialPlannerDataBase financialPlannerDataBase = new FinancialPlannerDataBase(getApplicationContext(), 1);
                    sleep(SPLASH_TIME);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(WelcomeScreen.this, MainActivity.class);
                    startActivity(intent);

                }
            }
        };
        timer.start();
    }
}
