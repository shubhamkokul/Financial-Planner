package com.mumbai.financial.financialplanner;

import android.app.DatePickerDialog;
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
import java.util.List;

import planner.androidadapters.CategoryListViewListAdapter;
import planner.androidadapters.IncomeListViewListAdapter;
import planner.androidadapters.WalletListViewListAdapter;
import planner.db.FinancialDatabaseWriter;
import planner.db.businesspopulate.AmountCalculation;
import planner.db.modal.ActualIncomeModal;
import planner.db.modal.CategoryItemModal;
import planner.db.modal.IncomePlannerModal;
import planner.db.modal.PlannedIncomeModal;
import planner.db.modal.TransactionModal;
import planner.db.modal.WalletPlannerModal;
import planner.utility.IdentifierGenerator;

public class AddIncome extends AppCompatActivity {

    private Spinner accountTypeSpinner, planTypeSpinner, categoryTypeSpinner;
    private EditText amountEditText, dateEditText;
    private Switch planSwitch;
    protected static List<WalletPlannerModal> walletPlannerModals;
    protected static List<IncomePlannerModal> incomePlannerModals;
    protected static List<CategoryItemModal> categoryItemModals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);

        amountEditText = findViewById(R.id.amountEditText);
        planSwitch = findViewById(R.id.planSwitch);


        accountTypeSpinner = findViewById(R.id.accountTypeSpinner);
        SQLiteDatabase dbReader = new FinancialDatabaseWriter(getApplicationContext(), 1).getDatabaseReader();
        walletPlannerModals = WalletPlannerModal.returnAll(dbReader);
        WalletListViewListAdapter walletAdapter = new WalletListViewListAdapter(getApplicationContext(), walletPlannerModals);
        accountTypeSpinner.setAdapter(walletAdapter);

        planTypeSpinner = findViewById(R.id.planTypeSpinner);
        dbReader = new FinancialDatabaseWriter(getApplicationContext(), 1).getDatabaseReader();
        incomePlannerModals = IncomePlannerModal.returnAll(dbReader);
        IncomeListViewListAdapter incomeAdapter = new IncomeListViewListAdapter(getApplicationContext(), incomePlannerModals);
        planTypeSpinner.setAdapter(incomeAdapter);

        categoryTypeSpinner = findViewById(R.id.categoryTypeSpinner);
        dbReader = new FinancialDatabaseWriter(getApplicationContext(), 1).getDatabaseReader();
        categoryItemModals = CategoryItemModal.returnAll(dbReader, 1);
        CategoryListViewListAdapter categoryAdapter = new CategoryListViewListAdapter(getApplicationContext(), categoryItemModals);
        categoryTypeSpinner.setAdapter(categoryAdapter);

        dateEditText = findViewById(R.id.dateEditText);
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectedDayChange();
            }
        });

        amountEditText = findViewById(R.id.amountEditText);
        planSwitch = findViewById(R.id.planSwitch);
        Button saveIncome = findViewById(R.id.saveIncome);
        saveIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransactionModal transactionModal = onIncomeSave();
                if (transactionModal == null) {

                } else {
                    if (saveIncomeMain(transactionModal) > 0) {
                        showPopUp(getCurrentFocus(), "Saved");
                    } else {
                        showPopUp(getCurrentFocus(), "Error while Saving");
                    }
                }

            }
        });


        ImageView backButton = findViewById(R.id.backButton);
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
        incomePlannerModals = IncomePlannerModal.returnAll(dbReader);
        IncomeListViewListAdapter incomeAdapter = new IncomeListViewListAdapter(getApplicationContext(), incomePlannerModals);
        planTypeSpinner.setAdapter(incomeAdapter);
        incomeAdapter.notifyDataSetChanged();
    }

    public void onSelectedDayChange() {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(AddIncome.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String displayDate = (monthOfYear + 1) + "-" + dayOfMonth + "-" + year;
                        dateEditText.setText(displayDate);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    private long saveIncomeMain(TransactionModal transactionModal) {
        long returnValue = 0;
        if (transactionModal == null) {
            return 0;
        } else {
            if (transactionModal.isPlanned()) {
                PlannedIncomeModal plannedIncomeModal = new PlannedIncomeModal(
                        IdentifierGenerator.timeStampGenerator(),
                        transactionModal.getId(),
                        transactionModal.getAccountId(),
                        transactionModal.getAccountName(),
                        transactionModal.getPlanId(),
                        transactionModal.getPlanName(),
                        transactionModal.getCategoryID(),
                        transactionModal.getCategoryName(),
                        transactionModal.getDate(),
                        transactionModal.getCurrentAmount(),
                        transactionModal.isPlanned()
                );
                SQLiteDatabase dbWriter = new FinancialDatabaseWriter(getApplicationContext(), 1).getDatabaseWriter();
                returnValue = PlannedIncomeModal.insertIntoTable(dbWriter, plannedIncomeModal);
            } else {
                ActualIncomeModal actualIncomeModal = new ActualIncomeModal(
                        IdentifierGenerator.timeStampGenerator(),
                        transactionModal.getId(),
                        transactionModal.getAccountId(),
                        transactionModal.getAccountName(),
                        transactionModal.getPlanId(),
                        transactionModal.getPlanName(),
                        transactionModal.getCategoryID(),
                        transactionModal.getCategoryName(),
                        transactionModal.getDate(),
                        transactionModal.getCurrentAmount(),
                        transactionModal.isPlanned()
                );
                SQLiteDatabase dbWriter = new FinancialDatabaseWriter(getApplicationContext(), 1).getDatabaseWriter();
                ActualIncomeModal.insertIntoTable(dbWriter, actualIncomeModal);
                SQLiteDatabase dbReader = new FinancialDatabaseWriter(getApplicationContext(), 1).getDatabaseReader();
                WalletPlannerModal walletPlannerModal = WalletPlannerModal.returnWallet(dbReader, transactionModal.getAccountId());
                returnValue = AmountCalculation.incomeCalculation(getApplicationContext(), actualIncomeModal, walletPlannerModal);
            }
        }
        return returnValue;
    }

    public TransactionModal onIncomeSave() {
        TransactionModal returnValue = null;
        if (amountEditText.getText().toString().isEmpty() || dateEditText.getText().toString().isEmpty()) {
            showPopUp(getCurrentFocus(), "Please Enter all fields");
            return null;
        } else {
            long id = IdentifierGenerator.timeStampGenerator();
            String date = dateEditText.getText().toString().trim();
            long timeStamp = id;
            IncomePlannerModal incomePlannerModal = incomePlannerModals.get(planTypeSpinner.getSelectedItemPosition());
            WalletPlannerModal walletPlannerModal = walletPlannerModals.get(accountTypeSpinner.getSelectedItemPosition());
            double currentAmount = Double.parseDouble(amountEditText.getText().toString().trim());
            CategoryItemModal categoryItemModal = categoryItemModals.get(categoryTypeSpinner.getSelectedItemPosition());
            boolean planned = planSwitch.isChecked();
            TransactionModal transactionModal = new TransactionModal(
                    id,
                    date,
                    timeStamp,
                    incomePlannerModal.getPlanID(),
                    incomePlannerModal.getMonthName() + " " + incomePlannerModal.getYearName(),
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
