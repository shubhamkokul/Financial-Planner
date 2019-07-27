package com.mumbai.financial.financialplanner;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import planner.androidadapters.CategoryListViewListAdapter;
import planner.androidadapters.ExpenseListViewListAdapter;
import planner.androidadapters.WalletListViewListAdapter;
import planner.db.FinancialDatabaseWriter;
import planner.db.modal.CategoryItemModal;
import planner.db.modal.ExpensePlannerModal;
import planner.db.modal.TransactionModal;
import planner.db.modal.WalletPlannerModal;
import planner.utility.IdentifierGenerator;

public class AddExpense extends AppCompatActivity {
    private static final String TAG = "AddExpense";
    private ImageView backButton;
    private Spinner accountTypeSpinner, planTypeSpinner, categoryTypeSpinner;
    private EditText amountEditText, dateEditText;
    DatePickerDialog datePickerDialog;
    private Button addExpensePlanButton, saveExpense;
    private Switch planSwitch;
    protected static List<WalletPlannerModal> walletPlannerModals;
    protected static List<ExpensePlannerModal> expensePlannerModals;
    protected static List<CategoryItemModal> categoryItemModals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        accountTypeSpinner = findViewById(R.id.accountTypeSpinner);
        SQLiteDatabase dbReader = new FinancialDatabaseWriter(getApplicationContext(), 1).getDatabaseReader();
        walletPlannerModals = WalletPlannerModal.returnAll(dbReader);
        WalletListViewListAdapter walletAdapter = new WalletListViewListAdapter(getApplicationContext(), walletPlannerModals);
        accountTypeSpinner.setAdapter(walletAdapter);

        planTypeSpinner = findViewById(R.id.planTypeSpinner);
        dbReader = new FinancialDatabaseWriter(getApplicationContext(), 1).getDatabaseReader();
        expensePlannerModals = ExpensePlannerModal.returnAll(dbReader);
        ExpenseListViewListAdapter expenseAdapter = new ExpenseListViewListAdapter(getApplicationContext(), expensePlannerModals);
        planTypeSpinner.setAdapter(expenseAdapter);

        categoryTypeSpinner = findViewById(R.id.categoryTypeSpinner);
        dbReader = new FinancialDatabaseWriter(getApplicationContext(), 1).getDatabaseReader();
        categoryItemModals = CategoryItemModal.returnAll(dbReader, 0);
        CategoryListViewListAdapter categoryAdapter = new CategoryListViewListAdapter(getApplicationContext(), categoryItemModals);
        categoryTypeSpinner.setAdapter(categoryAdapter);

        dateEditText = findViewById(R.id.dateEditText);
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectedDayChange();
            }
        });

        addExpensePlanButton = findViewById(R.id.addExpensePlanButton);
        addExpensePlanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddExpense.this, ExpensePlanner.class);
                startActivity(intent);
            }
        });

        amountEditText = findViewById(R.id.amountEditText);
        planSwitch = findViewById(R.id.planSwitch);
        saveExpense = findViewById(R.id.saveExpense);
        saveExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onExpenseSave()>0){
                    showPopUp(getCurrentFocus(), "Saved");
                    finish();
                } else {
                    showPopUp(getCurrentFocus(), "Error while Saving");
                }
            }
        });

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        SQLiteDatabase dbReader = new FinancialDatabaseWriter(getApplicationContext(), 1).getDatabaseReader();
        expensePlannerModals = ExpensePlannerModal.returnAll(dbReader);
        ExpenseListViewListAdapter expenseAdapter = new ExpenseListViewListAdapter(getApplicationContext(), expensePlannerModals);
        planTypeSpinner.setAdapter(expenseAdapter);
        expenseAdapter.notifyDataSetChanged();
    }

    public void onSelectedDayChange() {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        datePickerDialog = new DatePickerDialog(AddExpense.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String displayDate = (monthOfYear + 1) + "-" + dayOfMonth + "-" + year;
                        dateEditText.setText(displayDate);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    public long onExpenseSave() {
        long returnValue = 0;
        if (amountEditText.getText().toString().isEmpty()) {
            showPopUp(getCurrentFocus(), "Please Enter the amount");
        } else {
            long id = IdentifierGenerator.timeStampGenerator();
            String date = dateEditText.getText().toString().trim();
            long timeStamp = id;
            ExpensePlannerModal expensePlannerModal = expensePlannerModals.get(planTypeSpinner.getSelectedItemPosition());
            WalletPlannerModal walletPlannerModal = walletPlannerModals.get(accountTypeSpinner.getSelectedItemPosition());
            double currentAmount = Double.parseDouble(amountEditText.getText().toString().trim());
            CategoryItemModal categoryItemModal = categoryItemModals.get(categoryTypeSpinner.getSelectedItemPosition());
            boolean planned = planSwitch.isChecked();
            TransactionModal transactionModal = new TransactionModal(
                    id,
                    date,
                    timeStamp,
                    expensePlannerModal.getPlanID(),
                    expensePlannerModal.getMonthName() + " " + expensePlannerModal.getYearName(),
                    walletPlannerModal.getId(),
                    walletPlannerModal.getName(),
                    walletPlannerModal.getBalance(),
                    currentAmount,
                    categoryItemModal.getId(),
                    categoryItemModal.getType(),
                    categoryItemModal.getName(),
                    categoryItemModal.getTypeID(),
                    categoryItemModal.getType(),
                    categoryItemModal.getTypeName(),
                    planned
            );
            SQLiteDatabase dbWriter = new FinancialDatabaseWriter(getApplicationContext(), 1).getDatabaseWriter();
            returnValue = TransactionModal.insertIntoTable(dbWriter, transactionModal);
        }
            return returnValue;

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
