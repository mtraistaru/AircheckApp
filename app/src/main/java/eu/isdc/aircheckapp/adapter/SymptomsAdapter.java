package eu.isdc.aircheckapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.TextView;

import eu.isdc.aircheckapp.R;
import eu.isdc.aircheckapp.domain.Symptom;

/**
 * Created by ancestor on 4/23/16.
 */
public class SymptomsAdapter extends ArrayAdapter<Symptom> {

    private Context context;
    private int resource;

    public SymptomsAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        SymptomHolder symptomHolder;
        if (row == null) {
            row = inflater.inflate(resource, parent, false);
            symptomHolder = new SymptomHolder();
            symptomHolder.name = (TextView) row.findViewById(R.id.symptomName);
            symptomHolder.numberPicker = (NumberPicker) row.findViewById(R.id.rating);
            row.setTag(symptomHolder);
        } else {
            symptomHolder = (SymptomHolder) row.getTag();
        }
        if (getCount() > 0) {
            Symptom currentItem = getItem(position);
            if (currentItem != null) {
                symptomHolder.name.setText(currentItem.getSymptomName());
                symptomHolder.numberPicker.setMinValue(1);
                symptomHolder.numberPicker.setMaxValue(5);
                symptomHolder.numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                        getItem(position).setRating(numberPicker.getValue());
                    }
                });
            }
        }
        return row;
    }

    class SymptomHolder {
        TextView name;
        NumberPicker numberPicker;
    }
}
