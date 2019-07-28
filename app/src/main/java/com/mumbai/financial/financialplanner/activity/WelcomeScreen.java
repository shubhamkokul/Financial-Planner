package com.mumbai.financial.financialplanner.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mumbai.financial.financialplanner.R;

import planner.utility.PublicVariable;
import planner.db.FinancialPlannerDataBase;
import planner.db.businesspopulate.PopulateInitialTables;

public class WelcomeScreen extends AppCompatActivity {
    private int SPLASH_TIME = 1000;
    private String prefName = PublicVariable.MyPREFERENCES;
    private SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sharedpreferences = getSharedPreferences(prefName, MODE_PRIVATE);
                    boolean initialTable = sharedpreferences.getBoolean("initialtables", false);
                    if(initialTable){

                    } else {
                        FinancialPlannerDataBase financialPlannerDataBase = new FinancialPlannerDataBase(getApplicationContext(), 1);
                        new PopulateInitialTables(getApplicationContext());
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putBoolean("initialtables", true);
                        editor.commit();
                    }
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
