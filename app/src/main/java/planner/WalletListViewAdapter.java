package planner;

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

public class WalletListViewAdapter extends ArrayAdapter<WalletModal> {

    private static final String TAG = "WalletListViewAdapter";
    private final LayoutInflater mInflater;
    private TextView titleAccount, incomeLinearTextValue, expenseLinearTextValue, balanceLinearTextValue;
    private ImageView imageAccount;
    private List<WalletModal> walletModalArrayList = new ArrayList<>();

    public WalletListViewAdapter(Context context, List<WalletModal> walletModalArrayList) {
        super(context, R.layout.custom_wallet_list_item, walletModalArrayList);
        this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.walletModalArrayList = walletModalArrayList;
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


        titleAccount.setText(walletModalArrayList.get(position).getAccountName());
        incomeLinearTextValue.setText(walletModalArrayList.get(position).getIncome());
        expenseLinearTextValue.setText(walletModalArrayList.get(position).getExpense());
        balanceLinearTextValue.setText(walletModalArrayList.get(position).getBalance());
        Drawable displayImageDrawable = view.getResources().getDrawable(walletModalArrayList.get(position).getImage());
        imageAccount.setImageDrawable(displayImageDrawable);
        return view;
    }
}
