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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import planner.androidadapters.ExpenseComparisionAdapter;
import planner.db.FinancialDatabaseOperation;
import planner.db.modal.ActualExpenseModal;
import planner.db.modal.ExpenseComparisionModal;
import planner.db.modal.ExpensePlannerModal;
import planner.db.modal.PlannedExpenseModal;
import planner.utility.Utility;

public class ExpenseComparision extends Fragment {
    ExpensePlannerModal expensePlannerModal;
    ImageView backImage, addExpense;
    private TextView transactionTextViewTitle;
    private List<ActualExpenseModal> actualExpenseModals;
    private List<PlannedExpenseModal> plannedExpenseModals;
    private ListView comparisionListView;

    public ExpenseComparision() {

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense_comparision, container, false);
        this.expensePlannerModal = (ExpensePlannerModal) getArguments().getSerializable("expensePlannerModal");
        transactionTextViewTitle = view.findViewById(R.id.transactionTextViewTitle);
        backImage = view.findViewById(R.id.backImage);
        addExpense = view.findViewById(R.id.addExpense);
        comparisionListView = view.findViewById(R.id.comparisionListView);
        comparisionData();
        addExpense.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddExpense.class);
            startActivity(intent);
        });
        backImage.setOnClickListener(v -> getActivity().onBackPressed());
        return view;
    }

    public void comparisionData() {
        String displayText = expensePlannerModal.getMonthName()+ " " + expensePlannerModal.getYearName();
        transactionTextViewTitle.setText(displayText);
        List<ExpenseComparisionModal> expenseComparisionModals = new ArrayList<>();
        SQLiteDatabase dbReader = new FinancialDatabaseOperation(getActivity(), 1).getDatabaseReader();
        actualExpenseModals = ActualExpenseModal.returnMonthTransaction(dbReader, expensePlannerModal.getId());
        HashMap<String, String> actualExpenseMerge = Utility.mergeCategoriesActualExpense(actualExpenseModals);
        dbReader = new FinancialDatabaseOperation(getActivity(), 1).getDatabaseReader();
        plannedExpenseModals = PlannedExpenseModal.returnMonthTransaction(dbReader, expensePlannerModal.getId());
        HashMap<String, String> plannedExpenseMerge = Utility.mergeCategoriesPlannedExpense(plannedExpenseModals);
        plannedExpenseMerge.forEach(
                (key, value) -> actualExpenseMerge.merge(key, value, (v1, v2) -> v1 + "," + v2)
        );
        for (Map.Entry<String, String> pie : actualExpenseMerge.entrySet()) {
            String[] tempValue = pie.getValue().split(",");
            String actualValue;
            String plannedValue;
            if (tempValue[0].equalsIgnoreCase("") || tempValue[0].isEmpty()) {
                actualValue = "0";
            } else {
                actualValue = tempValue[0];
            }
            try {
                if (tempValue[1].equalsIgnoreCase("") || tempValue[1].isEmpty()) {
                    plannedValue = "0";
                } else {
                    plannedValue = tempValue[1];
                }
            } catch (Exception e) {
                plannedValue = "0";
            }
            ExpenseComparisionModal expenseComparisionModal = new ExpenseComparisionModal(pie.getKey(), actualValue, plannedValue);
            expenseComparisionModals.add(expenseComparisionModal);
        }
        ExpenseComparisionAdapter adapter = new ExpenseComparisionAdapter(getActivity(), expenseComparisionModals);
        comparisionListView.setAdapter(adapter);
    }
    @Override
    public void onResume() {
        super.onResume();
        comparisionData();
    }
}
