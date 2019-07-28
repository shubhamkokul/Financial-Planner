package planner.androidadapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mumbai.financial.financialplanner.R;
import java.util.List;

import planner.db.modal.ActualExpenseModal;

public class ActualExpenseAdapter extends ArrayAdapter<ActualExpenseModal> {
    private static final String TAG = "ActualExpenseAdapter";
    private final LayoutInflater mInflater;
    private TextView dayWord, month3Word, description, amount;
    private List<ActualExpenseModal> actualExpenseModals;
    private LinearLayout monthAndDay;

    public ActualExpenseAdapter(FragmentActivity context, List<ActualExpenseModal> actualExpenseModals) {
        super(context, R.layout.custom_transaction_list_item, actualExpenseModals);
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.actualExpenseModals = actualExpenseModals;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = mInflater.inflate(R.layout.custom_transaction_list_item, parent, false);
        } else {
            view = convertView;
        }

        monthAndDay = view.findViewById(R.id.monthAndDay);
        dayWord = view.findViewById(R.id.dayWord);
        month3Word = view.findViewById(R.id.month3Word);
        amount = view.findViewById(R.id.amount);
        description = view.findViewById(R.id.description);
        monthAndDay.setBackgroundColor(getContext().getResources().getColor(actualExpenseModals.get(position).getCategoryColor()));
        dayWord.setText(actualExpenseModals.get(position).getDay());
        month3Word.setText(actualExpenseModals.get(position).getMonth().substring(0, 3));
        String displayText = "-" + actualExpenseModals.get(position).getAmount();
        amount.setText(displayText);
        description.setText(actualExpenseModals.get(position).getCategoryName());
        return view;
    }
}
