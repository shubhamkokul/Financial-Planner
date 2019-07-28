package com.mumbai.financial.financialplanner.fragment;

import android.content.Intent;
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
import java.util.List;

import planner.utility.MyValueFormatter;

public class Home extends Fragment {
    private static final String START = "StartFragment";
    protected RelativeLayout placeholderHome;
    private FloatingActionButton expenseFloatingActionButton, incomeFloatingActionButton;
    protected HorizontalBarChart horizontalBarChartActual, horizontalBarChartPlanned;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_start_screen,container,false);
        placeholderHome = view.findViewById(R.id.placeholderHome);
        getLayoutInflater().inflate(R.layout.current_month_summary, placeholderHome);
        horizontalBarChartActual = view.findViewById(R.id.horizontalBarChartActual);
        horizontalBarChartPlanned = view.findViewById(R.id.horizontalBarChartPlanned);
        showOverviewGraph(horizontalBarChartActual, 500.5f, 100);
        showOverviewGraph(horizontalBarChartPlanned, 60.0f, 700.53f);

        expenseFloatingActionButton = view.findViewById(R.id.expenseFloatingActionButton);
        incomeFloatingActionButton = view.findViewById(R.id.incomeFloatingActionButton);

        expenseFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddExpense.class);
                startActivity(intent);
            }
        });

        incomeFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddIncome.class);
                startActivity(intent);
            }
        });
        return view;
    }
    public void showOverviewGraph(HorizontalBarChart horizontalBarChartCurrent, float income, float expense) {
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
        //YAxis leftYAxis = horizontalBarChartCurrent.getAxisLeft();
        XAxis XAxis = horizontalBarChartCurrent.getXAxis();
        horizontalBarChartCurrent.setTouchEnabled(false);
        XAxis.setEnabled(false);
        rightYAxis.setEnabled(false);
        //leftYAxis.setEnabled(false);
        Description description = new Description();
        description.setText("");
        horizontalBarChartCurrent.setDescription(description);
        horizontalBarChartCurrent.animateXY(2000,2000);
        horizontalBarChartCurrent.setData(barData);
        horizontalBarChartCurrent.setFitBars(true);
        horizontalBarChartCurrent.invalidate();

    }

}
