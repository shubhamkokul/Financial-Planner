package planner.androidadapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.mumbai.financial.financialplanner.R;

import java.util.ArrayList;
import java.util.List;

import planner.db.modal.IncomeComparisionModal;
import planner.utility.MyValueFormatter;

public class IncomeComparisionAdapter extends ArrayAdapter<IncomeComparisionModal> {
    private static final String TAG = "IncomeComparisionAdapter";
    private final LayoutInflater mInflater;
    private HorizontalBarChart horizontalBarChart;
    private List<IncomeComparisionModal> incomeComparisionModals;

    public IncomeComparisionAdapter(Context context, List<IncomeComparisionModal> incomeComparisionModals) {
        super(context, R.layout.comparision_income_row_item, incomeComparisionModals);
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.incomeComparisionModals = incomeComparisionModals;

    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = mInflater.inflate(R.layout.comparision_income_row_item, parent, false);
        } else {
            view = convertView;
        }
        horizontalBarChart = view.findViewById(R.id.horizontalBarChart);
        showOverviewGraph(horizontalBarChart, incomeComparisionModals.get(position).getActualValue(), incomeComparisionModals.get(position).getPlannedValue(), incomeComparisionModals.get(position).getCategoryName());
        return view;
    }
    private void showOverviewGraph(HorizontalBarChart horizontalBarChartCurrent, String planned, String actual, String categoryName) {
        float maxValue = 0;
        List<BarEntry> plannedEntried = new ArrayList<>();
        plannedEntried.add(new BarEntry(1f, Float.parseFloat(planned), planned));
        BarDataSet plannedDataSet = new BarDataSet(plannedEntried, "Planned");
        plannedDataSet.setValueFormatter(new MyValueFormatter());
        int color = ContextCompat.getColor(getContext(), R.color.blue);
        plannedDataSet.setColor(color);

        List<BarEntry> actualEntries = new ArrayList<>();
        actualEntries.add(new BarEntry(0, Float.parseFloat(actual), actual));
        BarDataSet actualDataSet = new BarDataSet(actualEntries, "Actual");
        actualDataSet.setValueFormatter(new MyValueFormatter());
        if(Float.parseFloat(actual)>Float.parseFloat(planned)){
            color = ContextCompat.getColor(getContext(), R.color.red);
            maxValue = Float.parseFloat(actual) * 2;
        } else {
            color = ContextCompat.getColor(getContext(), R.color.green);
            maxValue = Float.parseFloat(planned) * 2;
        }
        actualDataSet.setColor(color);
        BarData barData = new BarData(plannedDataSet, actualDataSet);
        barData.setBarWidth(.8f);
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
        description.setText(categoryName);
        horizontalBarChartCurrent.setDescription(description);
        horizontalBarChartCurrent.animateXY(2000,2000);
        horizontalBarChartCurrent.setData(barData);
        horizontalBarChartCurrent.setFitBars(true);
        horizontalBarChartCurrent.invalidate();

    }
}
