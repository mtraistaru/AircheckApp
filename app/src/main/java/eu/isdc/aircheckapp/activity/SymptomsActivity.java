package eu.isdc.aircheckapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import eu.isdc.aircheckapp.AircheckApp;
import eu.isdc.aircheckapp.R;
import eu.isdc.aircheckapp.adapter.SymptomsAdapter;
import eu.isdc.aircheckapp.api.AircheckService;
import eu.isdc.aircheckapp.api.event.GetSymptomsEvent;
import eu.isdc.aircheckapp.api.response.SymptomsResponse;
import eu.isdc.aircheckapp.domain.Symptom;

/**
 * Created by ancestor on 4/23/16.
 */
public class SymptomsActivity extends AppCompatActivity {

    @Inject
    AircheckService aircheckService;

    @Inject
    Bus bus;

    public static final String SYMPTOMS = "symptoms";

    ArrayAdapter<Symptom> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        AircheckApp.getApp(this).getAircheckComponent().inject(this);

        final List<Symptom> selectedSymptoms = new ArrayList<>();

        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        ListView symptomsList = (ListView) findViewById(R.id.options);
        if (symptomsList != null) {
            adapter = new SymptomsAdapter(this, R.layout.item_symptom);
            symptomsList.setAdapter(adapter);
            symptomsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    selectedSymptoms.add(adapter.getItem(i));
                    return false;
                }
            });
        }

        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    aircheckService.getSymptoms(newText);
                    return false;
                }
            });
        }

        Button nextButton = (Button) findViewById(R.id.nextButton);
        if (nextButton != null) {
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SymptomsActivity.this, ConditionsActivity.class);
                    intent.putParcelableArrayListExtra(SYMPTOMS, (ArrayList<Symptom>) selectedSymptoms);
                    startActivity(intent);
                }
            });
        }
    }

    @Subscribe
    public void onGetSymptomsEvent(GetSymptomsEvent getSymptomsEvent) {
        SymptomsResponse symptomsResponse = getSymptomsEvent.getSymptomsResponse();
        adapter.clear();
        if (symptomsResponse != null) {
            for (String symptom : symptomsResponse.getSymptoms()) {
                adapter.add(new Symptom(symptom));
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        bus.unregister(this);
    }
}
