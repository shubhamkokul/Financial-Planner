package planner.androidadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mumbai.financial.financialplanner.R;

import java.util.List;

import planner.db.modal.IconModal;

public class WalletIconAdapter extends ArrayAdapter<IconModal> {
    private static final String TAG = "ExpenseIncomeYearAdapter";
    private final LayoutInflater mInflater;
    private TextView dropDownItem;
    List<IconModal> iconModals;

    public WalletIconAdapter(Context context, List<IconModal> iconModals) {
        super(context, R.layout.drop_down_item, iconModals);
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.iconModals = iconModals;
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.drop_down_item, parent, false);
        }
        dropDownItem = convertView.findViewById(R.id.dropDownItem);
        dropDownItem.setText(iconModals.get(position).getIconName());
        return convertView;
    }
}
