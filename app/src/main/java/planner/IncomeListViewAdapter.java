package planner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mumbai.financial.financialplanner.R;

import java.util.ArrayList;
import java.util.List;

public class IncomeListViewAdapter extends ArrayAdapter<ExpenseIncomeModal> {

    private static final String TAG = "ExpenseListViewAdapter";
    private final LayoutInflater mInflater;
    private TextView year3Word, month3Word, fullMonthDate, monthName;
    private List<ExpenseIncomeModal> expenseIncomeModalArrayList = new ArrayList<>();

    public IncomeListViewAdapter(Context context, List<ExpenseIncomeModal> expenseIncomeModalArrayList) {
        super(context, R.layout.custom_income_list_item, expenseIncomeModalArrayList);
        this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.expenseIncomeModalArrayList = expenseIncomeModalArrayList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = mInflater.inflate(R.layout.custom_income_list_item, parent, false);
        } else {
            view = convertView;
        }

        year3Word = view.findViewById(R.id.year3Word);
        month3Word = view.findViewById(R.id.month3Word);
        fullMonthDate = view.findViewById(R.id.fullMonthDate);
        monthName = view.findViewById(R.id.monthName);


        year3Word.setText(expenseIncomeModalArrayList.get(position).getYear());
        month3Word.setText(expenseIncomeModalArrayList.get(position).getMonth());
        fullMonthDate.setText(expenseIncomeModalArrayList.get(position).getFullMonthDate());
        monthName.setText(expenseIncomeModalArrayList.get(position).getMonthStatement());
        return view;
    }
}
