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

public class ExpenseListViewListAdapter extends ArrayAdapter<ExpensePlannerModal> {
    private static final String TAG = "WalletListViewList";
    private final LayoutInflater mInflater;
    private TextView dropDownItem;
    private List<ExpensePlannerModal> expensePlannerModalList;
    Context context;

    public ExpenseListViewListAdapter(Context context, List<ExpensePlannerModal> expensePlannerModalList) {
        super(context, R.layout.drop_down_item, expensePlannerModalList);
        this.context = context;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.expensePlannerModalList = expensePlannerModalList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.drop_down_item, parent, false);
        }
        dropDownItem = convertView.findViewById(R.id.dropDownItem);

        String displayString = expensePlannerModalList.get(position).getMonthName() + " " + expensePlannerModalList.get(position).getYearName();
        dropDownItem.setText(displayString);
        return convertView;
    }
}
