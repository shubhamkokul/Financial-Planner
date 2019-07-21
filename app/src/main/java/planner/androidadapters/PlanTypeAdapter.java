package planner.androidadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mumbai.financial.financialplanner.R;

import java.util.List;

import planner.db.modal.PlanTypeModal;

public class PlanTypeAdapter extends ArrayAdapter<PlanTypeModal> {
    private static final String TAG = "PlanTypeAdapter";
    private final LayoutInflater mInflater;
    private TextView dropDownItem;
    private List<PlanTypeModal> planTypeModals;

    public PlanTypeAdapter(Context context, List<PlanTypeModal> expenseIncomeModalArrayList) {
        super(context, R.layout.drop_down_item, expenseIncomeModalArrayList);
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.planTypeModals = expenseIncomeModalArrayList;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.drop_down_item, parent, false);
        }
        dropDownItem = convertView.findViewById(R.id.dropDownItem);
        dropDownItem.setText(planTypeModals.get(position).getName());
        return convertView;
    }
}
