package planner.androidadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mumbai.financial.financialplanner.R;

import java.util.List;

import planner.db.modal.IncomePlannerModal;

public class IncomeListViewListAdapter extends ArrayAdapter<IncomePlannerModal> {
    private static final String TAG = "WalletListViewList";
    private final LayoutInflater mInflater;
    private TextView dropDownItem;
    private List<IncomePlannerModal> incomePlannerModals;
    Context context;

    public IncomeListViewListAdapter(Context context, List<IncomePlannerModal> incomePlannerModals) {
        super(context, R.layout.drop_down_item, incomePlannerModals);
        this.context = context;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.incomePlannerModals = incomePlannerModals;
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

        String displayString = incomePlannerModals.get(position).getMonthName() + " " + incomePlannerModals.get(position).getYearName();
        dropDownItem.setText(displayString);
        return convertView;
    }
}
