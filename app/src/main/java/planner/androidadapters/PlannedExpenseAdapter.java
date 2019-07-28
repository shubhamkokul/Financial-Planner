package planner.androidadapters;

import android.content.Context;
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
import planner.db.modal.PlannedExpenseModal;

public class PlannedExpenseAdapter extends ArrayAdapter<PlannedExpenseModal> {
    private static final String TAG = "PlannedExpenseAdapter";
    private final LayoutInflater mInflater;
    private TextView dayWord, month3Word, description, amount;
    private List<PlannedExpenseModal> plannedExpenseModals;
    private LinearLayout monthAndDay;

    public PlannedExpenseAdapter(FragmentActivity context, List<PlannedExpenseModal> plannedExpenseModals) {
        super(context, R.layout.custom_transaction_list_item, plannedExpenseModals);
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.plannedExpenseModals = plannedExpenseModals;
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
        monthAndDay.setBackgroundColor(getContext().getResources().getColor(plannedExpenseModals.get(position).getCategoryColor()));
        dayWord.setText(plannedExpenseModals.get(position).getDay());
        month3Word.setText(plannedExpenseModals.get(position).getMonth().substring(0, 3));
        String displayText = "-" + plannedExpenseModals.get(position).getAmount();
        amount.setText(displayText);
        amount.setTextColor(getContext().getResources().getColor(R.color.red));
        description.setText(plannedExpenseModals.get(position).getCategoryName());
        return view;
    }
}