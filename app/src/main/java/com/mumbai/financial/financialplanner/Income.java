package com.mumbai.financial.financialplanner;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import planner.ExpenseIncomeModal;
import planner.IncomeListViewAdapter;


public class Income extends Fragment {

    private static final String TAG = "Income";
    private ListView incomeListView;
    private ImageView plusSignButton;
    private List<ExpenseIncomeModal> expenseIncomeModalArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income, container, false);
        expenseIncomeModalArrayList = new ArrayList<>();
        expenseIncomeModalArrayList.add(new ExpenseIncomeModal("2019","JAN","1.01.2019 - 31.01.2019", "All my income for Jan 2019"));
        expenseIncomeModalArrayList.add(new ExpenseIncomeModal("2019","FEB","1.02.2019 - 28.02.2019", "All my income for FEB 2019"));
        expenseIncomeModalArrayList.add(new ExpenseIncomeModal("2019","MAR","1.03.2019 - 31.03.2019", "All my income for MAR 2019"));
        expenseIncomeModalArrayList.add(new ExpenseIncomeModal("2019","APR","1.04.2019 - 30.04.2019", "All my income for APR 2019"));
        expenseIncomeModalArrayList.add(new ExpenseIncomeModal("2019","MAY","1.05.2019 - 31.05.2019", "All my income for MAY 2019"));

        incomeListView = view.findViewById(R.id.incomeListView);
        IncomeListViewAdapter adapter = new IncomeListViewAdapter(getActivity(), expenseIncomeModalArrayList);
        incomeListView.setAdapter(adapter);

        plusSignButton = view.findViewById(R.id.plusSignButton);

        plusSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IncomePlanner.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
