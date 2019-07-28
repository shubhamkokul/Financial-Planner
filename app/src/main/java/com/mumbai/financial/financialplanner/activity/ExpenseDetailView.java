package com.mumbai.financial.financialplanner.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mumbai.financial.financialplanner.R;
import com.mumbai.financial.financialplanner.fragment.ExpenseActualTransaction;

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
        ExpenseActualTransaction expenseActualTransaction = new ExpenseActualTransaction();
        expenseActualTransaction.setArguments(args);
        adapter.addFragment(expenseActualTransaction, "Transaction");
        viewPager.setAdapter(adapter);
    }

}
