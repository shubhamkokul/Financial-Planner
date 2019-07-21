package com.mumbai.financial.financialplanner;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import planner.androidadapters.WalletListViewAdapter;
import planner.db.FinancialDatabaseWriter;
import planner.db.modal.WalletPlannerModal;

public class Wallet extends Fragment {

    private static final String TAG = "Expenses";
    private ListView walletListView;
    private ImageView plusSignButton;
    private List<WalletPlannerModal> walletPlannerModals;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);

        SQLiteDatabase dbReader = new FinancialDatabaseWriter(getActivity(), 1).getDatabaseReader();
        walletPlannerModals = WalletPlannerModal.returnAll(dbReader);

        walletListView = view.findViewById(R.id.walletListView);
        WalletListViewAdapter adapter = new WalletListViewAdapter(getActivity(), walletPlannerModals);
        walletListView.setAdapter(adapter);

        plusSignButton = view.findViewById(R.id.plusSignButton);

        plusSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WalletPlanner.class);
                startActivity(intent);
            }
        });

        walletListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openDetailView(position);
            }
        });

        return view;
    }

    public void openDetailView(int position) {
        Intent intent = new Intent(getActivity(), ExpenseDetailView.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

}
