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

import planner.db.modal.PlannedIncomeModal;

public class PlannedIncomeAdapter  extends ArrayAdapter<PlannedIncomeModal> {
    private static final String TAG = "PlannedIncomeAdapter";
    private final LayoutInflater mInflater;
    private TextView dayWord, month3Word, description, amount;
    private List<PlannedIncomeModal> plannedIncomeModals;
    private LinearLayout monthAndDay;

    public PlannedIncomeAdapter(FragmentActivity context, List<PlannedIncomeModal> plannedIncomeModals) {
        super(context, R.layout.custom_transaction_list_item, plannedIncomeModals);
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.plannedIncomeModals = plannedIncomeModals;
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
        monthAndDay.setBackgroundColor(getContext().getResources().getColor(plannedIncomeModals.get(position).getCategoryColor()));
        dayWord.setText(plannedIncomeModals.get(position).getDay());
        month3Word.setText(plannedIncomeModals.get(position).getMonth().substring(0, 3));
        String displayText = "" + plannedIncomeModals.get(position).getAmount();
        amount.setText(displayText);
        description.setText(plannedIncomeModals.get(position).getCategoryName());
        return view;
    }
}