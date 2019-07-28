package com.mumbai.financial.financialplanner.activity;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.mumbai.financial.financialplanner.R;
import com.mumbai.financial.financialplanner.fragment.Expenses;

import java.util.List;

import planner.androidadapters.ExpenseIncomeMonthAdapter;
import planner.androidadapters.ExpenseIncomeYearAdapter;
import planner.androidadapters.PlanTypeAdapter;
import planner.db.FinancialDatabaseOperation;
import planner.db.modal.ExpensePlannerModal;
import planner.db.modal.MonthModal;
import planner.db.modal.PlanTypeModal;
import planner.db.modal.YearModal;
import planner.utility.Utility;

public class ExpensePlanner extends AppCompatActivity {

    private static final String TAG = "ExpensePlanner";
    private Spinner planTypeSpinner, monthSpinner, yearSpinner;
    private Button saveExpense;
    private EditText descriptionEditText;
    private ImageView backButton;
    private List<PlanTypeModal> planTypeModals;
    private List<MonthModal> monthModals;
    private List<YearModal> yearModals;

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

        SQLiteDatabase dbReader = new FinancialDatabaseOperation(getApplicationContext(), 1).getDatabaseReader();
        planTypeModals = PlanTypeModal.returnAll(dbReader);
        PlanTypeAdapter planTypeAdapter = new PlanTypeAdapter(getApplicationContext(), planTypeModals);
        planTypeSpinner.setAdapter(planTypeAdapter);

        dbReader = new FinancialDatabaseOperation(getApplicationContext(), 1).getDatabaseReader();
        monthModals = MonthModal.returnAll(dbReader);
        ExpenseIncomeMonthAdapter expenseIncomeMonthAdapter = new ExpenseIncomeMonthAdapter(getApplicationContext(), monthModals);
        monthSpinner.setAdapter(expenseIncomeMonthAdapter);

        dbReader = new FinancialDatabaseOperation(getApplicationContext(), 1).getDatabaseReader();
        yearModals = YearModal.returnAll(dbReader);
        ExpenseIncomeYearAdapter expenseIncomeYearAdapter = new ExpenseIncomeYearAdapter(getApplicationContext(), yearModals);
        yearSpinner.setAdapter(expenseIncomeYearAdapter);

        saveExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExpensePlannerModal expensePlannerModal = SaveExpensePlannerData();
                if (expensePlannerModal != null) {
                    Expenses.expenseIncomeModalArrayList.add(expensePlannerModal);
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

    public ExpensePlannerModal SaveExpensePlannerData() {
        ExpensePlannerModal expensePlannerModal = null;
        PlanTypeModal planTypeModal = planTypeModals.get(planTypeSpinner.getSelectedItemPosition());
        if (planTypeModal.getName().equalsIgnoreCase("Monthly")) {
            String description = descriptionEditText.getText().toString().trim();
            MonthModal monthModal = monthModals.get(monthSpinner.getSelectedItemPosition());
            YearModal yearModal = yearModals.get(yearSpinner.getSelectedItemPosition());
            SQLiteDatabase dbReader = new FinancialDatabaseOperation(getApplicationContext(), 1).getDatabaseReader();
            if (ExpensePlannerModal.checkItem(dbReader, planTypeModal.getId(), monthModal.getId(), yearModal.getId()) > 0) {
                expensePlannerModal = null;
            } else {
                if(description.equalsIgnoreCase("") || description.isEmpty()){
                    description = "All my "+planTypeModal.getTypeName().toLowerCase()+" for "+monthModal.getName()+" "+yearModal.getYear();
                }
                expensePlannerModal = new ExpensePlannerModal(
                        Utility.timeStampGenerator(),
                        planTypeModal.getId(),
                        planTypeModal.getType(),
                        planTypeModal.getName(),
                        description,
                        monthModal.getId(),
                        monthModal.getActualPosition(),
                        monthModal.getName(),
                        yearModal.getId(),
                        yearModal.getYear());
                SQLiteDatabase dbWriter = new FinancialDatabaseOperation(getApplicationContext(), 1).getDatabaseWriter();
                ExpensePlannerModal.insertIntoTable(dbWriter, expensePlannerModal);
            }


        } else if (planTypeModal.getName().equalsIgnoreCase("Other")) {

        }
        return expensePlannerModal;
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
