package eu.isdc.aircheckapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import eu.isdc.aircheckapp.R;
import eu.isdc.aircheckapp.domain.Condition;

/**
 * Created by ancestor on 4/23/16.
 */
public class ConditionsAdapter extends ArrayAdapter<Condition> {

    private Context context;
    private int resource;

    public ConditionsAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        ConditionHolder conditionHolder;
        if (row == null) {
            row = inflater.inflate(resource, parent, false);
            conditionHolder = new ConditionHolder();
            conditionHolder.name = (TextView) row.findViewById(R.id.condition);
            conditionHolder.value = (TextView) row.findViewById(R.id.value);
            row.setTag(conditionHolder);
        } else {
            conditionHolder = (ConditionHolder) row.getTag();
        }
        if (getCount() > 0) {
            Condition currentItem = getItem(position);
            if (currentItem != null) {
                conditionHolder.name.setText(currentItem.getName());
                conditionHolder.value.setText(currentItem.getProbability());
            }
        }
        return row;
    }

    class ConditionHolder {
        TextView name;
        TextView value;
    }
}
