package com.mumbai.financial.financialplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import planner.ExpenseIncomeModal;
import planner.ExpenseListViewAdapter;


public class Expenses extends Fragment {

    private static final String TAG = "Expenses";
    private ListView expenseListView;
    private ImageView plusSignButton;
    private List<ExpenseIncomeModal> expenseIncomeModalArrayList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expenses, container, false);
        expenseIncomeModalArrayList = new ArrayList<>();
        expenseIncomeModalArrayList.add(new ExpenseIncomeModal("2019","JAN","1.01.2019 - 31.01.2019", "All my expense for Jan 2019"));
        expenseIncomeModalArrayList.add(new ExpenseIncomeModal("2019","FEB","1.02.2019 - 28.02.2019", "All my expense for FEB 2019"));
        expenseIncomeModalArrayList.add(new ExpenseIncomeModal("2019","MAR","1.03.2019 - 31.03.2019", "All my expense for MAR 2019"));
        expenseIncomeModalArrayList.add(new ExpenseIncomeModal("2019","APR","1.04.2019 - 30.04.2019", "All my expense for APR 2019"));
        expenseIncomeModalArrayList.add(new ExpenseIncomeModal("2019","MAY","1.05.2019 - 31.05.2019", "All my expense for MAY 2019"));

        expenseListView = view.findViewById(R.id.expenseListView);
        ExpenseListViewAdapter adapter = new ExpenseListViewAdapter(getActivity(), expenseIncomeModalArrayList);
        expenseListView.setAdapter(adapter);

        plusSignButton = view.findViewById(R.id.plusSignButton);

        plusSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ExpensePlanner.class);
                startActivity(intent);
            }
        });


        return view;
    }
}
