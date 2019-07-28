package com.mumbai.financial.financialplanner.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mumbai.financial.financialplanner.R;
import com.mumbai.financial.financialplanner.fragment.ExpenseComparision;
import com.mumbai.financial.financialplanner.fragment.ExpensePieChart;
import com.mumbai.financial.financialplanner.fragment.ExpenseSummary;
import com.mumbai.financial.financialplanner.fragment.ExpenseTransaction;

import planner.androidadapters.DetailSectionPageAdapter;
import planner.db.modal.ExpensePlannerModal;

public class ExpenseDetailView extends AppCompatActivity {
    private static final String TAG = "ExpenseDetailView";
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private ExpensePlannerModal expensePlannerModal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_detail_view);
        Intent intent = getIntent();
        expensePlannerModal = (ExpensePlannerModal)intent.getSerializableExtra("expensePlannerModal");
        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        DetailSectionPageAdapter adapter = new DetailSectionPageAdapter(getSupportFragmentManager());
        Bundle args = new Bundle();
        args.putSerializable("expensePlannerModal", expensePlannerModal);
        ExpenseTransaction expenseTransaction = new ExpenseTransaction();
        expenseTransaction.setArguments(args);
        adapter.addFragment(expenseTransaction, "Transaction");
        ExpensePieChart expensePieChart = new ExpensePieChart();
        expensePieChart.setArguments(args);
        adapter.addFragment(expensePieChart, "Charts");
        ExpenseComparision expenseComparision = new ExpenseComparision();
        expenseComparision.setArguments(args);
        adapter.addFragment(expenseComparision, "Comparision");
        ExpenseSummary expenseSummary = new ExpenseSummary();
        expenseSummary.setArguments(args);
        adapter.addFragment(expenseSummary, "Summary");
        viewPager.setAdapter(adapter);
    }

}
