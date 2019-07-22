package com.mumbai.financial.financialplanner;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import planner.androidadapters.ExpenseIncomeMonthAdapter;
import planner.androidadapters.ExpenseIncomeYearAdapter;
import planner.db.FinancialDatabaseWriter;
import planner.db.modal.IncomePlannerModal;
import planner.db.modal.MonthModal;
import planner.db.modal.PlanTypeModal;
import planner.db.modal.YearModal;
import planner.utility.IdentifierGenerator;

public class IncomePlanner extends AppCompatActivity {
    private static final String TAG = "IncomePlanner";
    private Spinner monthSpinner, yearSpinner;
    private Button saveIncome;
    private EditText descriptionEditText;
    private ImageView backButton;
    private List<MonthModal> monthModals;
    private List<YearModal> yearModals;

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
        monthModals = MonthModal.returnAll(dbReader);
        ExpenseIncomeMonthAdapter expenseIncomeMonthAdapter = new ExpenseIncomeMonthAdapter(getApplicationContext(), monthModals);
        monthSpinner.setAdapter(expenseIncomeMonthAdapter);

        dbReader = new FinancialDatabaseWriter(getApplicationContext(), 1).getDatabaseReader();
        yearModals = YearModal.returnAll(dbReader);
        ExpenseIncomeYearAdapter expenseIncomeYearAdapter = new ExpenseIncomeYearAdapter(getApplicationContext(), yearModals);
        yearSpinner.setAdapter(expenseIncomeYearAdapter);

        saveIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IncomePlannerModal incomePlannerModal = SaveIncomePlannerData();
                if (incomePlannerModal != null) {
                    Income.incomePlannerModals.add(incomePlannerModal);
                    finish();
                } else {
                    showPopUp(getCurrentFocus(), "Plan Already Added");
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

    public IncomePlannerModal SaveIncomePlannerData() {
        IncomePlannerModal incomePlannerModal = null;
        String description = descriptionEditText.getText().toString().trim();
        MonthModal monthModal = monthModals.get(monthSpinner.getSelectedItemPosition());
        YearModal yearModal = yearModals.get(yearSpinner.getSelectedItemPosition());
        SQLiteDatabase dbReader = new FinancialDatabaseWriter(getApplicationContext(), 1).getDatabaseReader();
        if (IncomePlannerModal.checkItem(dbReader, monthModal.getId(), yearModal.getId()) > 0) {
            incomePlannerModal = null;
        } else {
            if (description.equalsIgnoreCase("") || description.isEmpty()) {
                description = "All my income for " + monthModal.getName() + " " + yearModal.getYear();
            }
            incomePlannerModal = new IncomePlannerModal(
                    IdentifierGenerator.timeStampGenerator(),
                    description,
                    monthModal.getId(),
                    monthModal.getActualPosition(),
                    monthModal.getName(),
                    yearModal.getId(),
                    yearModal.getYear());
            SQLiteDatabase dbWriter = new FinancialDatabaseWriter(getApplicationContext(), 1).getDatabaseWriter();
            IncomePlannerModal.insertIntoTable(dbWriter, incomePlannerModal);
        }
        return incomePlannerModal;
    }

    public void showPopUp(View view, String message) {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        TextView messageTextView = popupView.findViewById(R.id.messageTextView);
        messageTextView.setText(message);
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 300);
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}
