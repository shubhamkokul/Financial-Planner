package com.mumbai.financial.financialplanner;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import planner.WalletListViewAdapter;
import planner.WalletModal;

public class Wallet extends Fragment {

    private static final String TAG = "Expenses";
    private ListView walletListView;
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

        return view;
    }

}
