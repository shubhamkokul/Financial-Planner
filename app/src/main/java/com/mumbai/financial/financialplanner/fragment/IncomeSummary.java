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
import com.mumbai.financial.financialplanner.activity.AddIncome;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import planner.db.FinancialDatabaseOperation;
import planner.db.modal.ActualIncomeModal;
import planner.db.modal.IncomePlannerModal;
import planner.db.modal.PlannedIncomeModal;
import planner.utility.Utility;

public class IncomeSummary extends Fragment {
    IncomePlannerModal incomePlannerModal;
    private TextView sumPlannedAmountText, sumActualAmountText, differenceAmountText, deviationAmountText, textSummaryTextView, transactionTextViewTitle;
    private ImageView imageSentiment, backImage, addIncome;
    private List<ActualIncomeModal> actualIncomeModals;
    private List<PlannedIncomeModal> plannedIncomeModals;

    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income_summary, container, false);
        this.incomePlannerModal = (IncomePlannerModal) getArguments().getSerializable("incomePlannerModal");
        transactionTextViewTitle = view.findViewById(R.id.transactionTextViewTitle);
        sumPlannedAmountText = view.findViewById(R.id.sumPlannedAmountText);
        sumActualAmountText = view.findViewById(R.id.sumActualAmountText);
        differenceAmountText = view.findViewById(R.id.differenceAmountText);
        deviationAmountText = view.findViewById(R.id.deviationAmountText);
        textSummaryTextView = view.findViewById(R.id.textSummaryTextView);
        imageSentiment = view.findViewById(R.id.imageSentiment);
        backImage = view.findViewById(R.id.backImage);
        addIncome = view.findViewById(R.id.addIncome);
        addIncome.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddIncome.class);
            startActivity(intent);
        });
        backImage.setOnClickListener(v -> getActivity().onBackPressed());
        summaryData();
        return view;
    }


    public void summaryData() {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        String displayText = incomePlannerModal.getMonthName() + " " + incomePlannerModal.getYearName();
        transactionTextViewTitle.setText(displayText);
        SQLiteDatabase dbReader = new FinancialDatabaseOperation(getActivity(), 1).getDatabaseReader();
        actualIncomeModals = ActualIncomeModal.returnMonthTransaction(dbReader, incomePlannerModal.getId());
        HashMap<String, String> actualIncomeMerge = Utility.mergeCategoriesActualIncome(actualIncomeModals);
        float actualSum = 0;
        for (Map.Entry<String, String> pie : actualIncomeMerge.entrySet()) {
            actualSum = actualSum + Float.parseFloat(pie.getValue());
        }
        displayText = actualSum+"";
        sumActualAmountText.setText(displayText);
        dbReader = new FinancialDatabaseOperation(getActivity(), 1).getDatabaseReader();
        plannedIncomeModals = PlannedIncomeModal.returnMonthTransaction(dbReader, incomePlannerModal.getId());
        HashMap<String, String> plannedIncomeMerge = Utility.mergeCategoriesPlannedIncome(plannedIncomeModals);
        float plannedSum = 0;
        for (Map.Entry<String, String> pie : plannedIncomeMerge.entrySet()) {
            plannedSum = plannedSum + Float.parseFloat(pie.getValue());
        }
        displayText = plannedSum+"";
        sumPlannedAmountText.setText(displayText);
        float difference = plannedSum - actualSum;
        displayText = difference+"";
        differenceAmountText.setText(displayText);
        if(plannedSum>=actualSum){
            differenceAmountText.setTextColor(getContext().getResources().getColor(R.color.red));
            float deviation = actualSum / plannedSum * 100;
            deviation = 100 - deviation;
            displayText = numberFormat.format(deviation)+ "%";
            deviationAmountText.setText(displayText);
            deviationAmountText.setTextColor(getContext().getResources().getColor(R.color.red));
            imageSentiment.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_sad));
            displayText = "The difference between the sum planned income and sum actual income is positive which means you have not exceeded the proposed income";
            textSummaryTextView.setText(displayText);
            textSummaryTextView.setTextColor(getContext().getResources().getColor(R.color.red));
        } else {
            differenceAmountText.setTextColor(getContext().getResources().getColor(R.color.green));
            float deviation = plannedSum / actualSum * 100;
            deviation = 100 - deviation;
            displayText = numberFormat.format(deviation)+ "%";
            deviationAmountText.setText(displayText);
            deviationAmountText.setTextColor(getContext().getResources().getColor(R.color.green));
            imageSentiment.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_happy));
            displayText = "The difference between the sum planned income and sum actual income is negative which means you have exceeded the proposed income";
            textSummaryTextView.setText(displayText);
            textSummaryTextView.setTextColor(getContext().getResources().getColor(R.color.green));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        summaryData();
    }
}
