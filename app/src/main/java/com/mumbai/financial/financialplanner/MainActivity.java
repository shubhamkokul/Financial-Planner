package com.mumbai.financial.financialplanner;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import planner.SectionPageAdapter;

public class MainActivity extends AppCompatActivity {
    private static final String START = "MainActivity";
    private SectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;
    protected TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Home(), "Home");
        adapter.addFragment(new Expenses(), "Expenses");
        adapter.addFragment(new Income(), "Income");
        adapter.addFragment(new Wallet(), "Wallet");
        viewPager.setAdapter(adapter);
    }



}
