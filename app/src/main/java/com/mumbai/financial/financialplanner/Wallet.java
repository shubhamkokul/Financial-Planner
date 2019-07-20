package com.mumbai.financial.financialplanner;


import android.content.Intent;
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
import planner.androidmodels.WalletModal;

public class Wallet extends Fragment {

    private static final String TAG = "Expenses";
    private ListView walletListView;
    private ImageView plusSignButton;
    private List<WalletModal> walletModalArrayList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_wallet, container, false);

        walletModalArrayList = new ArrayList<>();
        walletModalArrayList.add(new WalletModal("Bank","1000","1000", "2500",R.drawable.ic_bank_icon));
        walletModalArrayList.add(new WalletModal("Credit Card","1000","1000", "2500",R.drawable.ic_bank_icon));
        walletModalArrayList.add(new WalletModal("Cash","1000","1000", "2500",R.drawable.ic_bank_icon));
        walletModalArrayList.add(new WalletModal("Discover","1000","1000", "2200",R.drawable.ic_bank_icon));
        walletModalArrayList.add(new WalletModal("AMEX","1000","1000", "5000",R.drawable.ic_bank_icon));

        walletListView = view.findViewById(R.id.walletListView);
        WalletListViewAdapter adapter = new WalletListViewAdapter(getActivity(), walletModalArrayList);
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
    public void openDetailView(int position){
        Intent intent = new Intent(getActivity(), ExpenseDetailView.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

}
