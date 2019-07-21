package planner.androidadapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mumbai.financial.financialplanner.R;

import java.util.ArrayList;
import java.util.List;

import planner.db.modal.WalletPlannerModal;

public class WalletListViewAdapter extends ArrayAdapter<WalletPlannerModal> {

    private static final String TAG = "WalletListViewAdapter";
    private final LayoutInflater mInflater;
    private TextView titleAccount, incomeLinearTextValue, expenseLinearTextValue, balanceLinearTextValue;
    private ImageView imageAccount;
    private List<WalletPlannerModal> walletPlannerModals;

    public WalletListViewAdapter(Context context, List<WalletPlannerModal> walletPlannerModals) {
        super(context, R.layout.custom_wallet_list_item, walletPlannerModals);
        this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.walletPlannerModals = walletPlannerModals;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = mInflater.inflate(R.layout.custom_wallet_list_item, parent, false);
        } else {
            view = convertView;
        }

        titleAccount = view.findViewById(R.id.titleAccount);
        incomeLinearTextValue = view.findViewById(R.id.incomeLinearTextValue);
        expenseLinearTextValue = view.findViewById(R.id.expenseLinearTextValue);
        balanceLinearTextValue = view.findViewById(R.id.balanceLinearTextValue);
        imageAccount = view.findViewById(R.id.imageAccount);


        titleAccount.setText(walletPlannerModals.get(position).getName());
        incomeLinearTextValue.setText(walletPlannerModals.get(position).getIncomeBalance()+"");
        expenseLinearTextValue.setText(walletPlannerModals.get(position).getExpenseBalance()+"");
        balanceLinearTextValue.setText(walletPlannerModals.get(position).getBalance()+"");
        Drawable displayImageDrawable = view.getResources().getDrawable(walletPlannerModals.get(position).getIcon());
        imageAccount.setImageDrawable(displayImageDrawable);
        return view;
    }
}
