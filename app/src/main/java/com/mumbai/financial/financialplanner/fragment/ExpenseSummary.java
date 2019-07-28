package com.mumbai.financial.financialplanner.fragment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mumbai.financial.financialplanner.R;
import com.mumbai.financial.financialplanner.activity.AddExpense;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import planner.db.FinancialDatabaseOperation;
import planner.db.modal.ActualExpenseModal;
import planner.db.modal.ExpensePlannerModal;
import planner.db.modal.PlannedExpenseModal;
import planner.utility.Utility;

public class ExpenseSummary extends Fragment {
    ExpensePlannerModal expensePlannerModal;
    private TextView sumPlannedAmountText, sumActualAmountText, differenceAmountText, deviationAmountText, textSummaryTextView, transactionTextViewTitle;
    private ImageView imageSentiment, backImage, addExpense;
    private List<ActualExpenseModal> actualExpenseModals;
    private List<PlannedExpenseModal> plannedExpenseModals;

    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense_summary, container, false);
        this.expensePlannerModal = (ExpensePlannerModal) getArguments().getSerializable("expensePlannerModal");
        transactionTextViewTitle = view.findViewById(R.id.transactionTextViewTitle);
        sumPlannedAmountText = view.findViewById(R.id.sumPlannedAmountText);
        sumActualAmountText = view.findViewById(R.id.sumActualAmountText);
        differenceAmountText = view.findViewById(R.id.differenceAmountText);
        deviationAmountText = view.findViewById(R.id.deviationAmountText);
        textSummaryTextView = view.findViewById(R.id.textSummaryTextView);
        imageSentiment = view.findViewById(R.id.imageSentiment);
        backImage = view.findViewById(R.id.backImage);
        addExpense = view.findViewById(R.id.addExpense);
        addExpense.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddExpense.class);
            startActivity(intent);
        });
        backImage.setOnClickListener(v -> getActivity().onBackPressed());
        summaryData();
        return view;
    }

    public void summaryData() {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        String displayText = expensePlannerModal.getMonthName() + " " + expensePlannerModal.getYearName();
        transactionTextViewTitle.setText(displayText);
        SQLiteDatabase dbReader = new FinancialDatabaseOperation(getActivity(), 1).getDatabaseReader();
        actualExpenseModals = ActualExpenseModal.returnMonthTransaction(dbReader, expensePlannerModal.getId());
        HashMap<String, String> actualExpenseMerge = Utility.mergeCategoriesActualExpense(actualExpenseModals);
        float actualSum = 0;
        for (Map.Entry<String, String> pie : actualExpenseMerge.entrySet()) {
            actualSum = actualSum + Float.parseFloat(pie.getValue());
        }
        displayText = actualSum+"";
        sumActualAmountText.setText(displayText);
        dbReader = new FinancialDatabaseOperation(getActivity(), 1).getDatabaseReader();
        plannedExpenseModals = PlannedExpenseModal.returnMonthTransaction(dbReader, expensePlannerModal.getId());
        HashMap<String, String> plannedExpenseMerge = Utility.mergeCategoriesPlannedExpense(plannedExpenseModals);
        float plannedSum = 0;
        for (Map.Entry<String, String> pie : plannedExpenseMerge.entrySet()) {
            plannedSum = plannedSum + Float.parseFloat(pie.getValue());
        }
        displayText = plannedSum+"";
        sumPlannedAmountText.setText(displayText);
        float difference = plannedSum - actualSum;
        displayText = difference+"";
        differenceAmountText.setText(displayText);
        if(plannedSum>=actualSum){
            differenceAmountText.setTextColor(getContext().getResources().getColor(R.color.green));
            float deviation = actualSum / plannedSum * 100;
            deviation = 100 - deviation;
            displayText = numberFormat.format(deviation)+ "%";
            deviationAmountText.setText(displayText);
            deviationAmountText.setTextColor(getContext().getResources().getColor(R.color.green));
            imageSentiment.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_happy));
            displayText = "The difference between the sum planned expenses and sum actual expenses is positive which means you have not exceeded the proposed expenditure";
            textSummaryTextView.setText(displayText);
            textSummaryTextView.setTextColor(getContext().getResources().getColor(R.color.green));
        } else {
            differenceAmountText.setTextColor(getContext().getResources().getColor(R.color.red));
            float deviation = plannedSum / actualSum * 100;
            deviation = 100 - deviation;
            displayText = numberFormat.format(deviation)+ "%";
            deviationAmountText.setText(displayText);
            deviationAmountText.setTextColor(getContext().getResources().getColor(R.color.red));
            imageSentiment.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_sad));
            displayText = "The difference between the sum planned expenses and sum actual expenses is negative which means you have exceeded the proposed expenditure";
            textSummaryTextView.setText(displayText);
            textSummaryTextView.setTextColor(getContext().getResources().getColor(R.color.red));
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        summaryData();
    }
}
