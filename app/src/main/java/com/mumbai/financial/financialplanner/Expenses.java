package com.mumbai.financial.financialplanner;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import planner.androidadapters.ExpenseListViewAdapter;
import planner.db.FinancialDatabaseWriter;
import planner.db.modal.ExpensePlannerModal;


public class Expenses extends Fragment {

    private static final String TAG = "Expenses";
    private ListView expenseListView;
    private ImageView plusSignButton;
    private List<ExpensePlannerModal> expenseIncomeModalArrayList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expenses, container, false);
        SQLiteDatabase dbReader = new FinancialDatabaseWriter(getActivity(), 1).getDatabaseReader();
        expenseIncomeModalArrayList = ExpensePlannerModal.returnAll(dbReader);

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

        expenseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openDetailView(position);
            }
        });


        return view;
    }
    public void openDetailView(int position){
        Intent intent = new Intent(getActivity(), ExpenseDetailView.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}
