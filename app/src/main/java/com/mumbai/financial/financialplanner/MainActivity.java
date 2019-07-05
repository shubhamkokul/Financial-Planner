package com.mumbai.financial.financialplanner;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import java.util.ArrayList;
import java.util.List;

import planner.MyValueFormatter;

public class MainActivity extends AppCompatActivity {

    protected RelativeLayout placeholderHome;
    protected HorizontalBarChart horizontalBarChartActual, horizontalBarChartPlanned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        placeholderHome = findViewById(R.id.placeholderHome);
        getLayoutInflater().inflate(R.layout.current_month_summary, placeholderHome);
        horizontalBarChartActual = findViewById(R.id.horizontalBarChartActual);
        horizontalBarChartPlanned = findViewById(R.id.horizontalBarChartPlanned);
        showOverviewGraph(horizontalBarChartActual, 500.5f, 100);
        showOverviewGraph(horizontalBarChartPlanned, 60.0f, 700.53f);
    }

    public void showOverviewGraph(HorizontalBarChart horizontalBarChartCurrent, float income, float expense) {
        List<BarEntry> incomeEntries = new ArrayList<>();
        incomeEntries.add(new BarEntry(0, income));
        BarDataSet incomeBarDataSet = new BarDataSet(incomeEntries, "Income");
        incomeBarDataSet.setValueFormatter(new MyValueFormatter());
        int color = ContextCompat.getColor(this, R.color.green);
        incomeBarDataSet.setColor(color);

        List<BarEntry> expenseEntries = new ArrayList<>();
        expenseEntries.add(new BarEntry(1f, expense));
        BarDataSet expenseBarDataSet = new BarDataSet(expenseEntries, "Expense");
        expenseBarDataSet.setValueFormatter(new MyValueFormatter());
        color = ContextCompat.getColor(this, R.color.red);
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
