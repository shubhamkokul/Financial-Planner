package planner.androidadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mumbai.financial.financialplanner.R;

import java.util.List;

import planner.db.modal.CategoryItemModal;

public class CategoryListViewListAdapter extends ArrayAdapter<CategoryItemModal> {
    private static final String TAG = "CategoryListViewList";
    private final LayoutInflater mInflater;
    private TextView dropDownItem;
    private List<CategoryItemModal> categoryItemModals;
    Context context;

    public CategoryListViewListAdapter(Context context, List<CategoryItemModal> categoryItemModals) {
        super(context, R.layout.drop_down_item, categoryItemModals);
        this.context = context;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.categoryItemModals = categoryItemModals;
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

        dropDownItem.setText(categoryItemModals.get(position).getName());
        return convertView;
    }
}
