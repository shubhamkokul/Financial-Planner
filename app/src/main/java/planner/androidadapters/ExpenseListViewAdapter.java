package planner.androidadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mumbai.financial.financialplanner.R;

import java.util.List;

import planner.db.modal.ExpensePlannerModal;

public class ExpenseListViewAdapter extends ArrayAdapter<ExpensePlannerModal> {

    private static final String TAG = "ExpenseListViewAdapter";
    private final LayoutInflater mInflater;
    private TextView year3Word, month3Word, monthName, description;
    private List<ExpensePlannerModal> expenseIncomeModalArrayList;

    public ExpenseListViewAdapter(Context context, List<ExpensePlannerModal> expenseIncomeModalArrayList) {
        super(context, R.layout.custom_expense_list_item, expenseIncomeModalArrayList);
        this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.expenseIncomeModalArrayList = expenseIncomeModalArrayList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = mInflater.inflate(R.layout.custom_expense_list_item, parent, false);
        } else {
            view = convertView;
        }

        year3Word = view.findViewById(R.id.year3Word);
        month3Word = view.findViewById(R.id.month3Word);
        monthName = view.findViewById(R.id.monthName);
        description = view.findViewById(R.id.description);


        year3Word.setText(expenseIncomeModalArrayList.get(position).getYearName()+"");
        month3Word.setText(expenseIncomeModalArrayList.get(position).getMonthName().substring(0,3));
        monthName.setText(expenseIncomeModalArrayList.get(position).getMonthName());
        description.setText(expenseIncomeModalArrayList.get(position).getDescription());
        return view;
    }
}
