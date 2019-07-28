package planner.androidadapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.mumbai.financial.financialplanner.R;

import java.util.ArrayList;
import java.util.List;

import planner.db.modal.ExpenseComparisionModal;
import planner.utility.MyValueFormatter;

public class ExpenseComparisionAdapter extends ArrayAdapter<ExpenseComparisionModal> {
    private static final String TAG = "ExpenseComparisionAdapter";
    private final LayoutInflater mInflater;
    private HorizontalBarChart horizontalBarChart;
    private List<ExpenseComparisionModal> expenseComparisionModals;

    public ExpenseComparisionAdapter(Context context, List<ExpenseComparisionModal> expenseComparisionModals) {
        super(context, R.layout.comparion_expense_row_item, expenseComparisionModals);
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.expenseComparisionModals = expenseComparisionModals;

    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = mInflater.inflate(R.layout.comparion_expense_row_item, parent, false);
        } else {
            view = convertView;
        }
        horizontalBarChart = view.findViewById(R.id.horizontalBarChart);
        showOverviewGraph(horizontalBarChart, expenseComparisionModals.get(position).getActualValue(), expenseComparisionModals.get(position).getPlannedValue(), expenseComparisionModals.get(position).getCategoryName());
        return view;
    }
    private void showOverviewGraph(HorizontalBarChart horizontalBarChartCurrent, String actual, String planned, String categoryName) {
        List<BarEntry> plannedEntried = new ArrayList<>();
        plannedEntried.add(new BarEntry(1f, Float.parseFloat(planned)));
        BarDataSet plannedDataSet = new BarDataSet(plannedEntried, "Planned");
        plannedDataSet.setValueFormatter(new MyValueFormatter());
        int color = ContextCompat.getColor(getContext(), R.color.blue);
        plannedDataSet.setColor(color);

        List<BarEntry> actualEntries = new ArrayList<>();
        actualEntries.add(new BarEntry(0, Float.parseFloat(actual)));
        BarDataSet actualDataSet = new BarDataSet(actualEntries, "Actual");
        actualDataSet.setValueFormatter(new MyValueFormatter());
        if(Float.parseFloat(actual)>Float.parseFloat(planned)){
            color = ContextCompat.getColor(getContext(), R.color.red);
        } else {
            color = ContextCompat.getColor(getContext(), R.color.green);
        }
        actualDataSet.setColor(color);
        BarData barData = new BarData(plannedDataSet, actualDataSet);
        barData.setBarWidth(.8f);
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
        description.setText(categoryName);
        horizontalBarChartCurrent.setDescription(description);
        horizontalBarChartCurrent.animateXY(2000,2000);
        horizontalBarChartCurrent.setData(barData);
        horizontalBarChartCurrent.setFitBars(true);
        horizontalBarChartCurrent.invalidate();

    }
}
