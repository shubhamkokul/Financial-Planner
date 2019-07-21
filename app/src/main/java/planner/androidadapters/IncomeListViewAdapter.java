package planner.androidadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mumbai.financial.financialplanner.R;

import java.util.ArrayList;
import java.util.List;

import planner.db.modal.IncomePlannerModal;

public class IncomeListViewAdapter extends ArrayAdapter<IncomePlannerModal> {

    private static final String TAG = "ExpenseListViewAdapter";
    private final LayoutInflater mInflater;
    private TextView year3Word, month3Word, monthName, description;
    private List<IncomePlannerModal> incomePlannerModals;

    public IncomeListViewAdapter(Context context, List<IncomePlannerModal> incomePlannerModals) {
        super(context, R.layout.custom_income_list_item, incomePlannerModals);
        this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.incomePlannerModals = incomePlannerModals;
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
        monthName = view.findViewById(R.id.monthName);
        description = view.findViewById(R.id.description);


        year3Word.setText(incomePlannerModals.get(position).getYearName()+"");
        month3Word.setText(incomePlannerModals.get(position).getMonthName().substring(0, 3));
        monthName.setText(incomePlannerModals.get(position).getMonthName());
        description.setText(incomePlannerModals.get(position).getDescription());
        return view;
    }
}
