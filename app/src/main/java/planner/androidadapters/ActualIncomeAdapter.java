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

import planner.db.modal.ActualIncomeModal;

public class ActualIncomeAdapter  extends ArrayAdapter<ActualIncomeModal> {
    private static final String TAG = "ActualIncomeAdapter";
    private final LayoutInflater mInflater;
    private TextView dayWord, month3Word, description, amount;
    private List<ActualIncomeModal> actualIncomeModals;
    private LinearLayout monthAndDay;

    public ActualIncomeAdapter(FragmentActivity context, List<ActualIncomeModal> actualIncomeModals) {
        super(context, R.layout.custom_transaction_list_item, actualIncomeModals);
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.actualIncomeModals = actualIncomeModals;
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
        monthAndDay.setBackgroundColor(getContext().getResources().getColor(actualIncomeModals.get(position).getCategoryColor()));
        dayWord.setText(actualIncomeModals.get(position).getDay());
        month3Word.setText(actualIncomeModals.get(position).getMonth().substring(0, 3));
        String displayText = "" + actualIncomeModals.get(position).getAmount();
        amount.setText(displayText);
        amount.setTextColor(getContext().getResources().getColor(R.color.green));
        description.setText(actualIncomeModals.get(position).getCategoryName());
        return view;
    }
}