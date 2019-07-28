package com.mumbai.financial.financialplanner.fragment;


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

import com.mumbai.financial.financialplanner.R;
import com.mumbai.financial.financialplanner.activity.WalletPlanner;
import com.mumbai.financial.financialplanner.activity.ExpenseDetailView;

import java.util.List;

import planner.androidadapters.WalletListViewAdapter;
import planner.db.FinancialDatabaseOperation;
import planner.db.modal.WalletPlannerModal;

public class Wallet extends Fragment {

    private static final String TAG = "Expenses";
    private ListView walletListView;
    private ImageView plusSignButton;
    public static List<WalletPlannerModal> walletPlannerModals;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);

        SQLiteDatabase dbReader = new FinancialDatabaseOperation(getActivity(), 1).getDatabaseReader();
        walletPlannerModals = WalletPlannerModal.returnAll(dbReader);

        walletListView = view.findViewById(R.id.walletListView);
        WalletListViewAdapter adapter = new WalletListViewAdapter(getActivity(), walletPlannerModals);
        walletListView.setAdapter(adapter);

        plusSignButton = view.findViewById(R.id.plusSignButton);

        plusSignButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), WalletPlanner.class);
            startActivity(intent);
        });

        /*walletListView.setOnItemClickListener((parent, view1, position, id) -> openDetailView(position));*/

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        WalletListViewAdapter adapter = new WalletListViewAdapter(getActivity(), walletPlannerModals);
        walletListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void openDetailView(int position) {
        Intent intent = new Intent(getActivity(), ExpenseDetailView.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

}
