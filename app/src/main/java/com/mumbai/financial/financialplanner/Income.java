package com.mumbai.financial.financialplanner;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.List;

import planner.androidadapters.IncomeListViewAdapter;
import planner.db.FinancialDatabaseWriter;
import planner.db.modal.IncomePlannerModal;


public class Income extends Fragment {

    private static final String TAG = "Income";
    private ListView incomeListView;
    private ImageView plusSignButton;
    public static List<IncomePlannerModal> incomePlannerModals;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income, container, false);
        SQLiteDatabase dbReader = new FinancialDatabaseWriter(getActivity(), 1).getDatabaseReader();
        incomePlannerModals = IncomePlannerModal.returnAll(dbReader);
        incomeListView = view.findViewById(R.id.incomeListView);
        IncomeListViewAdapter adapter = new IncomeListViewAdapter(getActivity(), incomePlannerModals);
        incomeListView.setAdapter(adapter);

        plusSignButton = view.findViewById(R.id.plusSignButton);

        plusSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IncomePlanner.class);
                startActivity(intent);
            }
        });

        incomeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openDetailView(position);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        IncomeListViewAdapter adapter = new IncomeListViewAdapter(getActivity(), incomePlannerModals);
        incomeListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void openDetailView(int position){
        Intent intent = new Intent(getActivity(), ExpenseDetailView.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

}
