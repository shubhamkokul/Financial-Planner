package com.mumbai.financial.financialplanner.fragment;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mumbai.financial.financialplanner.R;
import com.mumbai.financial.financialplanner.activity.AddExpense;

import java.util.List;

import planner.androidadapters.ActualExpenseAdapter;
import planner.db.FinancialDatabaseOperation;
import planner.db.modal.ActualExpenseModal;
import planner.db.modal.ExpensePlannerModal;

public class ExpenseActualTransaction extends Fragment {
    ExpensePlannerModal expensePlannerModal;
    ImageView backImage, toggleImage, addExpense;
    private TextView transactionTextViewTitle;
    private boolean transactionViewBoolean = true;
    private List<ActualExpenseModal> actualExpenseModals;
    private ListView actualExpenseListView;


    public ExpenseActualTransaction() {

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense_actual_transaction, container, false);
        this.expensePlannerModal = (ExpensePlannerModal) getArguments().getSerializable("expensePlannerModal");
        transactionTextViewTitle = view.findViewById(R.id.transactionTextViewTitle);
        backImage = view.findViewById(R.id.backImage);
        toggleImage = view.findViewById(R.id.toggleImage);
        addExpense = view.findViewById(R.id.addExpense);
        actualExpenseListView = view.findViewById(R.id.actualExpenseListView);
        toggleTransaction();
        toggleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleTransaction();
            }
        });
        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddExpense.class);
                startActivity(intent);
            }
        });
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        return view;
    }

    public void toggleTransaction() {
        if (transactionViewBoolean) {
            transactionTextViewTitle.setText("Actual Expense");
            SQLiteDatabase dbReader = new FinancialDatabaseOperation(getActivity(), 1).getDatabaseReader();
            actualExpenseModals = ActualExpenseModal.returnMonthTransaction(dbReader, expensePlannerModal.getId());
            ActualExpenseAdapter adapter = new ActualExpenseAdapter(getActivity(), actualExpenseModals);
            actualExpenseListView.setAdapter(adapter);
            transactionViewBoolean = false;
        } else {
            transactionTextViewTitle.setText("Planned Expense");
            SQLiteDatabase dbReader = new FinancialDatabaseOperation(getActivity(), 1).getDatabaseReader();
            transactionViewBoolean = true;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        SQLiteDatabase dbReader = new FinancialDatabaseOperation(getActivity(), 1).getDatabaseReader();
        actualExpenseModals = ActualExpenseModal.returnMonthTransaction(dbReader, expensePlannerModal.getId());
        ActualExpenseAdapter adapter = new ActualExpenseAdapter(getActivity(), actualExpenseModals);
        actualExpenseListView.setAdapter(adapter);
    }
}
