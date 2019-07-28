package com.mumbai.financial.financialplanner.activity;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.mumbai.financial.financialplanner.R;
import com.mumbai.financial.financialplanner.fragment.Wallet;

import java.util.List;

import planner.androidadapters.WalletIconAdapter;
import planner.db.FinancialDatabaseOperation;
import planner.db.modal.IconModal;
import planner.db.modal.WalletPlannerModal;
import planner.utility.IdentifierGenerator;

public class WalletPlanner extends AppCompatActivity {
    private Button saveWallet;
    private EditText nameEditText, currentAmountEditText;
    private Spinner iconSpinner;
    private ImageView backButton;
    private List<IconModal> iconModals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_planner);
        saveWallet = findViewById(R.id.saveWallet);
        nameEditText = findViewById(R.id.nameEditText);
        currentAmountEditText = findViewById(R.id.currentAmountEditText);
        iconSpinner = findViewById(R.id.iconSpinner);
        backButton = findViewById(R.id.backButton);

        SQLiteDatabase dbReader = new FinancialDatabaseOperation(getApplicationContext(), 1).getDatabaseReader();
        iconModals = IconModal.returnAll(dbReader);
        WalletIconAdapter walletIconAdapter = new WalletIconAdapter(getApplicationContext(), iconModals);
        iconSpinner.setAdapter(walletIconAdapter);

        saveWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WalletPlannerModal walletPlannerModal = SaveWalletData();
                if (walletPlannerModal != null) {
                    Wallet.walletPlannerModals.add(walletPlannerModal);
                    finish();
                }
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private WalletPlannerModal SaveWalletData() {
        WalletPlannerModal walletPlannerModal = null;
        String name = nameEditText.getText().toString().trim();
        IconModal iconModal = iconModals.get(iconSpinner.getSelectedItemPosition());
        String balance = currentAmountEditText.getText().toString().trim();
        SQLiteDatabase dbReader = new FinancialDatabaseOperation(getApplicationContext(), 1).getDatabaseReader();
        if (WalletPlannerModal.checkItem(dbReader, name.toUpperCase(), iconModal.getIcon()) > 0) {
            walletPlannerModal = null;
            showPopUp(getCurrentFocus(), "Account Already Added");
        } else {
            if(balance.equalsIgnoreCase("") || balance.isEmpty()){
                balance = "0.0";
            }
            if(name.equalsIgnoreCase("") || name.isEmpty()){
                showPopUp(getCurrentFocus(), "Enter Account Name");
            } else {
                walletPlannerModal = new WalletPlannerModal(
                        IdentifierGenerator.timeStampGenerator(),
                        name.toUpperCase(),
                        iconModal.getId(),
                        iconModal.getIcon(),
                        iconModal.getIconName(),
                        Double.parseDouble(balance),
                        0.0,
                        Double.parseDouble(balance)
                );
                SQLiteDatabase dbWriter = new FinancialDatabaseOperation(getApplicationContext(), 1).getDatabaseWriter();
                WalletPlannerModal.insertIntoTable(dbWriter, walletPlannerModal);
            }

        }
        return walletPlannerModal;
    }

    public void showPopUp(View view, String message) {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        TextView messageTextView = popupView.findViewById(R.id.messageTextView);
        messageTextView.setText(message);
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 300);
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}
