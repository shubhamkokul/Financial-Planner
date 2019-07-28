package com.mumbai.financial.financialplanner.fragment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.mumbai.financial.financialplanner.R;
import com.mumbai.financial.financialplanner.activity.AddExpense;
import com.mumbai.financial.financialplanner.activity.AddIncome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import planner.db.FinancialDatabaseOperation;
import planner.db.modal.ActualExpenseModal;
import planner.db.modal.ActualIncomeModal;
import planner.db.modal.ExpensePlannerModal;
import planner.db.modal.IncomePlannerModal;
import planner.db.modal.PlannedExpenseModal;
import planner.db.modal.PlannedIncomeModal;
import planner.utility.MyValueFormatter;
import planner.utility.Utility;

public class Home extends Fragment {
    private static final String START = "StartFragment";
    protected RelativeLayout placeholderHome;
    private FloatingActionButton expenseFloatingActionButton, incomeFloatingActionButton;
    protected HorizontalBarChart horizontalBarChartActual, horizontalBarChartPlanned;
    private List<ActualIncomeModal> actualIncomeModals;
    private List<PlannedIncomeModal> plannedIncomeModals;
    private List<ActualExpenseModal> actualExpenseModals;
    private List<PlannedExpenseModal> plannedExpenseModals;
    private TextView actualIncomeExpense, plannedIncomeExpense, differenceExpenses, differenceIncome, totalAmount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_start_screen, container, false);
        placeholderHome = view.findViewById(R.id.placeholderHome);
        getLayoutInflater().inflate(R.layout.current_month_summary, placeholderHome);
        horizontalBarChartActual = view.findViewById(R.id.horizontalBarChartActual);
        horizontalBarChartPlanned = view.findViewById(R.id.horizontalBarChartPlanned);
        expenseFloatingActionButton = view.findViewById(R.id.expenseFloatingActionButton);
        incomeFloatingActionButton = view.findViewById(R.id.incomeFloatingActionButton);
        actualIncomeExpense = view.findViewById(R.id.actualIncomeExpense);
        plannedIncomeExpense = view.findViewById(R.id.plannedIncomeExpense);
        differenceExpenses = view.findViewById(R.id.differenceExpenses);
        differenceIncome = view.findViewById(R.id.differenceIncome);
        totalAmount = view.findViewById(R.id.totalAmount);
        calculateCurrentMonthSummary();
        expenseFloatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddExpense.class);
            startActivity(intent);
        });

        incomeFloatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddIncome.class);
            startActivity(intent);
        });
        return view;
    }

    public void calculateCurrentMonthSummary() {
        int month = Utility.getCurrentMonth();
        int year = Utility.getCurrentYear();
        SQLiteDatabase dbReader = new FinancialDatabaseOperation(getActivity(), 1).getDatabaseReader();
        IncomePlannerModal incomePlannerModal = IncomePlannerModal.returnMonthTransaction(dbReader, month, year);
        dbReader = new FinancialDatabaseOperation(getActivity(), 1).getDatabaseReader();
        actualIncomeModals = ActualIncomeModal.returnMonthTransaction(dbReader, incomePlannerModal.getId());
        HashMap<String, String> actualIncomeMerge = Utility.mergeCategoriesActualIncome(actualIncomeModals);
        float actualIncomeMergeSum = 0;
        for (Map.Entry<String, String> pie : actualIncomeMerge.entrySet()) {
            actualIncomeMergeSum = actualIncomeMergeSum + Float.parseFloat(pie.getValue());
        }
        dbReader = new FinancialDatabaseOperation(getActivity(), 1).getDatabaseReader();
        plannedIncomeModals = PlannedIncomeModal.returnMonthTransaction(dbReader, incomePlannerModal.getId());
        HashMap<String, String> plannedIncomeMerge = Utility.mergeCategoriesPlannedIncome(plannedIncomeModals);
        float plannedIncomeMergeSum = 0;
        for (Map.Entry<String, String> pie : plannedIncomeMerge.entrySet()) {
            plannedIncomeMergeSum = plannedIncomeMergeSum + Float.parseFloat(pie.getValue());
        }
        dbReader = new FinancialDatabaseOperation(getActivity(), 1).getDatabaseReader();
        ExpensePlannerModal expensePlannerModal = ExpensePlannerModal.returnMonthTransaction(dbReader, month, year);
        dbReader = new FinancialDatabaseOperation(getActivity(), 1).getDatabaseReader();
        actualExpenseModals = ActualExpenseModal.returnMonthTransaction(dbReader, expensePlannerModal.getId());
        HashMap<String, String> actualExpenseMerge = Utility.mergeCategoriesActualExpense(actualExpenseModals);
        float actualExpenseMergeSum = 0;
        for (Map.Entry<String, String> pie : actualExpenseMerge.entrySet()) {
            actualExpenseMergeSum = actualExpenseMergeSum + Float.parseFloat(pie.getValue());
        }
        dbReader = new FinancialDatabaseOperation(getActivity(), 1).getDatabaseReader();
        plannedExpenseModals = PlannedExpenseModal.returnMonthTransaction(dbReader, expensePlannerModal.getId());
        HashMap<String, String> plannedExpenseMerge = Utility.mergeCategoriesPlannedExpense(plannedExpenseModals);
        float plannedExpenseMergeSum = 0;
        for (Map.Entry<String, String> pie : plannedExpenseMerge.entrySet()) {
            plannedExpenseMergeSum = plannedExpenseMergeSum + Float.parseFloat(pie.getValue());
        }
        showOverviewGraph(horizontalBarChartActual, actualIncomeMergeSum, actualExpenseMergeSum);
        showOverviewGraph(horizontalBarChartPlanned, plannedIncomeMergeSum, plannedExpenseMergeSum);
        setTextViewAmount(actualIncomeMergeSum, plannedIncomeMergeSum, actualExpenseMergeSum, plannedExpenseMergeSum);
    }

    public void setTextViewAmount(float actualIncomeMergeSum, float plannedIncomeMergeSum, float actualExpenseMergeSum, float plannedExpenseMergeSum){
        double actualIncomeExpenseValue = actualIncomeMergeSum - actualExpenseMergeSum;
        String displayText = actualIncomeExpenseValue+"";
        actualIncomeExpense.setText(displayText);
        totalAmount.setText(displayText);
        if(actualIncomeExpenseValue>0){
            actualIncomeExpense.setTextColor(getContext().getResources().getColor(R.color.green));
            totalAmount.setTextColor(getContext().getResources().getColor(R.color.green));
        } else {
            actualIncomeExpense.setTextColor(getContext().getResources().getColor(R.color.red));
            totalAmount.setTextColor(getContext().getResources().getColor(R.color.red));
        }
        double plannedIncomeExpenseValue = plannedIncomeMergeSum - plannedExpenseMergeSum;
        displayText = plannedIncomeExpenseValue+"";
        plannedIncomeExpense.setText(displayText);
        if(plannedIncomeExpenseValue>0){
            plannedIncomeExpense.setTextColor(getContext().getResources().getColor(R.color.green));
        } else {
            plannedIncomeExpense.setTextColor(getContext().getResources().getColor(R.color.red));
        }
        double differenceExpensesValue = plannedExpenseMergeSum - actualExpenseMergeSum;
        displayText = differenceExpensesValue+"";
        differenceExpenses.setText(displayText);
        if(differenceExpensesValue>0){
            differenceExpenses.setTextColor(getContext().getResources().getColor(R.color.green));
        } else {
            differenceExpenses.setTextColor(getContext().getResources().getColor(R.color.red));
        }
        double differenceIncomeValue = actualIncomeMergeSum - plannedIncomeMergeSum;
        displayText = differenceIncomeValue+"";
        differenceIncome.setText(displayText);
        if(differenceExpensesValue>0){
            differenceIncome.setTextColor(getContext().getResources().getColor(R.color.green));
        } else {
            differenceIncome.setTextColor(getContext().getResources().getColor(R.color.red));
        }
    }

    public void showOverviewGraph(HorizontalBarChart horizontalBarChartCurrent, float income, float expense) {
        float maxValue = 0;
        if (income > expense) {
            maxValue = income * 2;
        } else {
            maxValue = expense * 2;
        }
        List<BarEntry> incomeEntries = new ArrayList<>();
        incomeEntries.add(new BarEntry(0, income));
        BarDataSet incomeBarDataSet = new BarDataSet(incomeEntries, "Income");
        incomeBarDataSet.setValueFormatter(new MyValueFormatter());
        int color = ContextCompat.getColor(getContext(), R.color.green);
        incomeBarDataSet.setColor(color);

        List<BarEntry> expenseEntries = new ArrayList<>();
        expenseEntries.add(new BarEntry(1f, expense));
        BarDataSet expenseBarDataSet = new BarDataSet(expenseEntries, "Expense");
        expenseBarDataSet.setValueFormatter(new MyValueFormatter());
        color = ContextCompat.getColor(getContext(), R.color.red);
        expenseBarDataSet.setColor(color);

        BarData barData = new BarData(expenseBarDataSet, incomeBarDataSet);
        barData.setBarWidth(.9f);

        horizontalBarChartCurrent.setVisibility(View.VISIBLE);
        horizontalBarChartCurrent.getAxisLeft().setStartAtZero(true);
        horizontalBarChartCurrent.getAxisRight().setStartAtZero(false);
        horizontalBarChartCurrent.getXAxis().setDrawGridLines(false);
        YAxis rightYAxis = horizontalBarChartCurrent.getAxisRight();
        YAxis leftYAxis = horizontalBarChartCurrent.getAxisLeft();
        XAxis XAxis = horizontalBarChartCurrent.getXAxis();
        horizontalBarChartCurrent.setTouchEnabled(false);
        leftYAxis.setAxisMaxValue(maxValue);
        XAxis.setEnabled(false);
        rightYAxis.setEnabled(false);
        //leftYAxis.setEnabled(false);
        Description description = new Description();
        description.setText("");
        horizontalBarChartCurrent.setDescription(description);
        horizontalBarChartCurrent.animateXY(2000, 2000);
        horizontalBarChartCurrent.setData(barData);
        horizontalBarChartCurrent.setFitBars(true);
        horizontalBarChartCurrent.invalidate();
    }

    @Override
    public void onResume() {
        super.onResume();
        calculateCurrentMonthSummary();
    }

}
