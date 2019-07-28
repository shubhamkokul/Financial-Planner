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
import com.mumbai.financial.financialplanner.activity.AddIncome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import planner.androidadapters.IncomeComparisionAdapter;
import planner.db.FinancialDatabaseOperation;
import planner.db.modal.ActualIncomeModal;
import planner.db.modal.IncomeComparisionModal;
import planner.db.modal.IncomePlannerModal;
import planner.db.modal.PlannedIncomeModal;
import planner.utility.Utility;

public class IncomeComparision extends Fragment {
    IncomePlannerModal incomePlannerModal;
    ImageView backImage, addIncome;
    private TextView transactionTextViewTitle;
    private List<ActualIncomeModal> actualIncomeModals;
    private List<PlannedIncomeModal> plannedIncomeModals;
    private ListView comparisionListView;

    public IncomeComparision() {

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income_comparision, container, false);
        this.incomePlannerModal = (IncomePlannerModal) getArguments().getSerializable("incomePlannerModal");
        transactionTextViewTitle = view.findViewById(R.id.transactionTextViewTitle);
        backImage = view.findViewById(R.id.backImage);
        addIncome = view.findViewById(R.id.addIncome);
        comparisionListView = view.findViewById(R.id.comparisionListView);
        comparisionData();
        addIncome.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddIncome.class);
            startActivity(intent);
        });
        backImage.setOnClickListener(v -> getActivity().onBackPressed());
        return view;
    }

    public void comparisionData() {
        String displayText = incomePlannerModal.getMonthName() + " " + incomePlannerModal.getYearName();
        transactionTextViewTitle.setText(displayText);
        List<IncomeComparisionModal> incomeComparisionModals = new ArrayList<>();
        SQLiteDatabase dbReader = new FinancialDatabaseOperation(getActivity(), 1).getDatabaseReader();
        actualIncomeModals = ActualIncomeModal.returnMonthTransaction(dbReader, incomePlannerModal.getId());
        HashMap<String, String> actualIncomeMerge = Utility.mergeCategoriesActualIncome(actualIncomeModals);
        dbReader = new FinancialDatabaseOperation(getActivity(), 1).getDatabaseReader();
        plannedIncomeModals = PlannedIncomeModal.returnMonthTransaction(dbReader, incomePlannerModal.getId());
        HashMap<String, String> plannedIncomeMerge = Utility.mergeCategoriesPlannedIncome(plannedIncomeModals);
        plannedIncomeMerge.forEach(
                (key, value) -> actualIncomeMerge.merge(key, value, (v1, v2) -> v1 + "," + v2)
        );
        for (Map.Entry<String, String> pie : actualIncomeMerge.entrySet()) {
            String[] tempValue = pie.getValue().split(",");
            String actualValue;
            String plannedValue;
            if (tempValue[0].equalsIgnoreCase("") || tempValue[0].isEmpty()) {
                actualValue = "0";
            } else {
                actualValue = tempValue[0];
            }
            try {
                if (tempValue[1].equalsIgnoreCase("") || tempValue[1].isEmpty()) {
                    plannedValue = "0";
                } else {
                    plannedValue = tempValue[1];
                }
            } catch (Exception e) {
                plannedValue = "0";
            }
            IncomeComparisionModal incomeComparisionModal = new IncomeComparisionModal(pie.getKey(), actualValue, plannedValue);
            incomeComparisionModals.add(incomeComparisionModal);
        }
        IncomeComparisionAdapter adapter = new IncomeComparisionAdapter(getActivity(), incomeComparisionModals);
        comparisionListView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        comparisionData();
    }
}
