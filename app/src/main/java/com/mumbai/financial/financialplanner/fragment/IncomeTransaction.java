package com.mumbai.financial.financialplanner.fragment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mumbai.financial.financialplanner.R;
import com.mumbai.financial.financialplanner.activity.AddExpense;
import com.mumbai.financial.financialplanner.activity.AddIncome;

import java.util.List;

import planner.androidadapters.ActualIncomeAdapter;
import planner.androidadapters.PlannedIncomeAdapter;
import planner.db.FinancialDatabaseOperation;
import planner.db.modal.ActualIncomeModal;
import planner.db.modal.IncomePlannerModal;
import planner.db.modal.PlannedIncomeModal;

public class IncomeTransaction extends Fragment {
    IncomePlannerModal incomePlannerModal;
    ImageView backImage, toggleImage, addIncome;
    private TextView transactionTextViewTitle;
    private boolean transactionViewBoolean = true;
    private List<ActualIncomeModal> actualIncomeModals;
    private List<PlannedIncomeModal> plannedIncomeModals;
    private ListView transactionList;

    public IncomeTransaction() {

    }
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income_transaction, container, false);
        this.incomePlannerModal = (IncomePlannerModal) getArguments().getSerializable("incomePlannerModal");
        transactionTextViewTitle = view.findViewById(R.id.transactionTextViewTitle);
        backImage = view.findViewById(R.id.backImage);
        toggleImage = view.findViewById(R.id.toggleImage);
        addIncome = view.findViewById(R.id.addIncome);
        transactionList = view.findViewById(R.id.transactionList);
        toggleTransaction();
        toggleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleTransaction();
            }
        });
        addIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddIncome.class);
                startActivity(intent);
            }
        });
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        return view;
    }

    public void toggleTransaction() {
        if (transactionViewBoolean) {
            transactionTextViewTitle.setText("Actual Income");
            SQLiteDatabase dbReader = new FinancialDatabaseOperation(getActivity(), 1).getDatabaseReader();
            actualIncomeModals = ActualIncomeModal.returnMonthTransaction(dbReader, incomePlannerModal.getId());
            ActualIncomeAdapter adapter = new ActualIncomeAdapter(getActivity(), actualIncomeModals);
            transactionList.setAdapter(adapter);
            transactionViewBoolean = false;
        } else {
            transactionTextViewTitle.setText("Planned Income");
            SQLiteDatabase dbReader = new FinancialDatabaseOperation(getActivity(), 1).getDatabaseReader();
            plannedIncomeModals = PlannedIncomeModal.returnMonthTransaction(dbReader, incomePlannerModal.getId());
            PlannedIncomeAdapter adapter = new PlannedIncomeAdapter(getActivity(), plannedIncomeModals);
            transactionList.setAdapter(adapter);
            transactionViewBoolean = true;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        toggleTransaction();
    }
}
