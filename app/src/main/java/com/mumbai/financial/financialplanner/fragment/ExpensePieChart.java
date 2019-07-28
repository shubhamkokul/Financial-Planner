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
import com.mumbai.financial.financialplanner.activity.AddExpense;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import planner.db.FinancialDatabaseOperation;
import planner.db.modal.ActualExpenseModal;
import planner.db.modal.ExpensePlannerModal;
import planner.db.modal.PlannedExpenseModal;
import planner.utility.IdentifierGenerator;
import planner.utility.MyValueFormatter;

public class ExpensePieChart extends Fragment {
    ExpensePlannerModal expensePlannerModal;
    ImageView backImage, toggleImage, addExpense;
    private TextView transactionTextViewTitle;
    private boolean transactionViewBoolean = true;
    private List<ActualExpenseModal> actualExpenseModals;
    private List<PlannedExpenseModal> plannedExpenseModals;
    private PieChart expensePieChart;

    public ExpensePieChart() {

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense_pie_chart, container, false);
        this.expensePlannerModal = (ExpensePlannerModal) getArguments().getSerializable("expensePlannerModal");
        transactionTextViewTitle = view.findViewById(R.id.transactionTextViewTitle);
        backImage = view.findViewById(R.id.backImage);
        toggleImage = view.findViewById(R.id.toggleImage);
        addExpense = view.findViewById(R.id.addExpense);
        expensePieChart = view.findViewById(R.id.expensePieChart);
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
            transactionTextViewTitle.setText("Actual Expense Pie Chart");
            SQLiteDatabase dbReader = new FinancialDatabaseOperation(getActivity(), 1).getDatabaseReader();
            actualExpenseModals = ActualExpenseModal.returnMonthTransaction(dbReader, expensePlannerModal.getId());
            HashMap<String, Double> pieMerge = IdentifierGenerator.mergeCategoriesActualExpense(actualExpenseModals);
            List<Integer> mergeColor = IdentifierGenerator.mergeColorActualExpense(actualExpenseModals);
            contructPie(pieMerge, mergeColor);
            transactionViewBoolean = false;
        } else {
            transactionTextViewTitle.setText("Planned Expense Pie Chart");
            SQLiteDatabase dbReader = new FinancialDatabaseOperation(getActivity(), 1).getDatabaseReader();
            plannedExpenseModals = PlannedExpenseModal.returnMonthTransaction(dbReader, expensePlannerModal.getId());
            HashMap<String, Double> pieMerge = IdentifierGenerator.mergeCategoriesPlannedExpense(plannedExpenseModals);
            List<Integer> mergeColor = IdentifierGenerator.mergeColorPlannedExpense(plannedExpenseModals);
            contructPie(pieMerge, mergeColor);
            transactionViewBoolean = true;
        }
    }
    public void contructPie(HashMap<String, Double> pieMerge, List<Integer> mergeColor) {
        List<PieEntry> entries = new ArrayList<>();
        float sum = 0;
        for (Map.Entry<String, Double> pie : pieMerge.entrySet()) {
            sum = sum + Float.parseFloat(pie.getValue() + "");
        }
        for (Map.Entry<String, Double> pie : pieMerge.entrySet()) {
            float percent = Float.parseFloat(pie.getValue() + "") * 100 / sum;
            entries.add(new PieEntry(percent, pie.getKey()));
        }
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(mergeColor);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(expensePieChart));
        data.setDataSet(dataSet);
        data.setValueTextSize(14f);
        data.setValueTextColor(Color.BLACK);
        Description description = new Description();
        description.setText("");
        expensePieChart.invalidate();
        expensePieChart.setDescription(description);
        expensePieChart.setUsePercentValues(true);
        expensePieChart.setData(data);
        expensePieChart.setDrawHoleEnabled(true);
        expensePieChart.animateXY(1000, 1000);
        expensePieChart.getLegend().setEnabled(true);
    }


}
