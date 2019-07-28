package com.mumbai.financial.financialplanner.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mumbai.financial.financialplanner.R;
import com.mumbai.financial.financialplanner.fragment.IncomeComparision;
import com.mumbai.financial.financialplanner.fragment.IncomePieChart;
import com.mumbai.financial.financialplanner.fragment.IncomeSummary;
import com.mumbai.financial.financialplanner.fragment.IncomeTransaction;

import planner.androidadapters.DetailSectionPageAdapter;
import planner.db.modal.IncomePlannerModal;

public class IncomeDetailView extends AppCompatActivity {
    private static final String TAG = "IncomeDetailView";
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private IncomePlannerModal incomePlannerModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_detail_view);
        Intent intent = getIntent();
        incomePlannerModal = (IncomePlannerModal) intent.getSerializableExtra("incomePlannerModal");
        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        DetailSectionPageAdapter adapter = new DetailSectionPageAdapter(getSupportFragmentManager());
        Bundle args = new Bundle();
        args.putSerializable("incomePlannerModal", incomePlannerModal);
        IncomeTransaction incomeTransaction = new IncomeTransaction();
        incomeTransaction.setArguments(args);
        adapter.addFragment(incomeTransaction, "Transaction");
        IncomePieChart incomePieChart = new IncomePieChart();
        incomePieChart.setArguments(args);
        adapter.addFragment(incomePieChart, "Charts");
        IncomeComparision incomeComparision = new IncomeComparision();
        incomeComparision.setArguments(args);
        adapter.addFragment(incomeComparision, "Comparision");
        IncomeSummary incomeSummary = new IncomeSummary();
        incomeSummary.setArguments(args);
        adapter.addFragment(incomeSummary, "Summary");
        viewPager.setAdapter(adapter);
    }
}