package com.mumbai.financial.financialplanner.fragment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.mumbai.financial.financialplanner.R;
import com.mumbai.financial.financialplanner.activity.AddIncome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import planner.db.FinancialDatabaseOperation;
import planner.db.modal.ActualIncomeModal;
import planner.db.modal.IncomePlannerModal;
import planner.db.modal.PlannedIncomeModal;
import planner.utility.Utility;

public class IncomePieChart extends Fragment {
    IncomePlannerModal incomePlannerModal;
    ImageView backImage, toggleImage, addIncome;
    private TextView transactionTextViewTitle, transactionType, totalAmount;
    private boolean transactionViewBoolean = true;
    private List<ActualIncomeModal> actualIncomeModals;
    private List<PlannedIncomeModal> plannedIncomeModals;
    private PieChart incomePieChart;

    public IncomePieChart() {

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income_pie_chart, container, false);
        this.incomePlannerModal = (IncomePlannerModal) getArguments().getSerializable("incomePlannerModal");
        transactionTextViewTitle = view.findViewById(R.id.transactionTextViewTitle);
        transactionType = view.findViewById(R.id.transactionType);
        totalAmount = view.findViewById(R.id.totalAmount);
        backImage = view.findViewById(R.id.backImage);
        toggleImage = view.findViewById(R.id.toggleImage);
        addIncome = view.findViewById(R.id.addIncome);
        incomePieChart = view.findViewById(R.id.incomePieChart);
        toggleTransaction();
        toggleImage.setOnClickListener(v -> toggleTransaction());
        addIncome.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddIncome.class);
            startActivity(intent);
        });
        backImage.setOnClickListener(v -> getActivity().onBackPressed());
        return view;
    }

    public void toggleTransaction() {
        String displayText = incomePlannerModal.getMonthName() + " " + incomePlannerModal.getYearName();
        transactionTextViewTitle.setText(displayText);
        if (transactionViewBoolean) {
            transactionType.setText("Actual Income");
            SQLiteDatabase dbReader = new FinancialDatabaseOperation(getActivity(), 1).getDatabaseReader();
            actualIncomeModals = ActualIncomeModal.returnMonthTransaction(dbReader, incomePlannerModal.getId());
            HashMap<String, String> pieMerge = Utility.mergeCategoriesActualIncome(actualIncomeModals);
            List<Integer> mergeColor = Utility.mergeColorActualIncome(actualIncomeModals);
            generatePie(pieMerge, mergeColor);
            transactionViewBoolean = false;
        } else {
            transactionType.setText("Planned Income");
            SQLiteDatabase dbReader = new FinancialDatabaseOperation(getActivity(), 1).getDatabaseReader();
            plannedIncomeModals = PlannedIncomeModal.returnMonthTransaction(dbReader, incomePlannerModal.getId());
            HashMap<String, String> pieMerge = Utility.mergeCategoriesPlannedIncome(plannedIncomeModals);
            List<Integer> mergeColor = Utility.mergeColorPlannedIncome(plannedIncomeModals);
            generatePie(pieMerge, mergeColor);
            transactionViewBoolean = true;
        }
    }

    public void generatePie(HashMap<String, String> pieMerge, List<Integer> mergeColor) {
        List<PieEntry> entries = new ArrayList<>();
        float sum = 0;
        for (Map.Entry<String, String> pie : pieMerge.entrySet()) {
            sum = sum + Float.parseFloat(pie.getValue());
        }
        String displayText = sum + "";
        totalAmount.setText(displayText);
        for (Map.Entry<String, String> pie : pieMerge.entrySet()) {
            float percent = Float.parseFloat(pie.getValue()) * 100 / sum;
            entries.add(new PieEntry(percent, pie.getKey()));
        }
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(mergeColor);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(incomePieChart));
        data.setDataSet(dataSet);
        data.setValueTextSize(14f);
        data.setValueTextColor(Color.BLACK);
        Description description = new Description();
        description.setText("");
        incomePieChart.invalidate();
        incomePieChart.setDescription(description);
        incomePieChart.setUsePercentValues(true);
        incomePieChart.setData(data);
        incomePieChart.setDrawHoleEnabled(true);
        incomePieChart.animateXY(1000, 1000);
        incomePieChart.getLegend().setEnabled(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        toggleTransaction();
    }
}
