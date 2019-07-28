package com.mumbai.financial.financialplanner.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mumbai.financial.financialplanner.R;
import com.mumbai.financial.financialplanner.fragment.Expenses;
import com.mumbai.financial.financialplanner.fragment.Home;
import com.mumbai.financial.financialplanner.fragment.Income;
import com.mumbai.financial.financialplanner.fragment.Wallet;

import planner.androidadapters.SectionPageAdapter;

public class MainActivity extends AppCompatActivity {
    private static final String START = "MainActivity";
    private ViewPager mViewPager;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    @Override
    public void onBackPressed() {
        int pageView = tabLayout.getSelectedTabPosition();
        if(pageView == 0){
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        } else {
            mViewPager.setCurrentItem(0);
        }
    }
}
