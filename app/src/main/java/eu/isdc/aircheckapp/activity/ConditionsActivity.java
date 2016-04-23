package eu.isdc.aircheckapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.List;

import javax.inject.Inject;

import eu.isdc.aircheckapp.AircheckApp;
import eu.isdc.aircheckapp.R;
import eu.isdc.aircheckapp.api.AircheckService;
import eu.isdc.aircheckapp.api.event.GetConditionsEvent;
import eu.isdc.aircheckapp.api.event.SendFeedbackEvent;
import eu.isdc.aircheckapp.api.request.FeedbackRequest;
import eu.isdc.aircheckapp.api.response.ConditionsResponse;
import eu.isdc.aircheckapp.api.response.FeedbackResponse;
import eu.isdc.aircheckapp.domain.Location;
import eu.isdc.aircheckapp.domain.Symptom;

/**
 * Created by ancestor on 4/23/16.
 */
public class ConditionsActivity extends AppCompatActivity {

    @Inject
    AircheckService aircheckService;

    @Inject
    Bus bus;

    ArrayAdapter<String> adapter;
    String selectedCondition;
    View oldSelectedConditionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        AircheckApp.getApp(this).getAircheckComponent().inject(this);
        final List<Symptom> symptoms = getIntent().getExtras().getParcelableArrayList(SymptomsActivity.SYMPTOMS);
        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        ListView conditionsList = (ListView) findViewById(R.id.options);
        if (conditionsList != null) {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
            conditionsList.setAdapter(adapter);
            conditionsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    if (selectedCondition != null) {
                        oldSelectedConditionView = view;
                    }
                    selectedCondition = adapter.getItem(i);
                    if (oldSelectedConditionView != null) {
                        oldSelectedConditionView.setBackgroundColor(getResources().getColor(android.R.color.white));
                    }
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
                    aircheckService.getConditions(newText);
                    return false;
                }
            });
        }

        Button nextButton = (Button) findViewById(R.id.nextButton);
        if (nextButton != null) {
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FeedbackRequest feedbackRequest = new FeedbackRequest();
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                    double latitude = Double.valueOf(sharedPreferences.getString(WelcomeActivity.LATITUDE, ""));
                    double longitude = Double.valueOf(sharedPreferences.getString(WelcomeActivity.LONGITUDE, ""));
                    Location location = new Location(latitude, longitude);
                    feedbackRequest.setLocation(location);
                    feedbackRequest.setSymptoms(symptoms);
                    feedbackRequest.setCondition(selectedCondition);
                    aircheckService.sendFeedback(feedbackRequest);
                }
            });
        }
    }

    @Subscribe
    public void onGetConditionsEvent(GetConditionsEvent getConditionsEvent) {
        ConditionsResponse conditionsResponse = getConditionsEvent.getConditionsResponse();
        adapter.clear();
        if (conditionsResponse != null) {
            for (String condition : conditionsResponse.getConditions()) {
                adapter.add(condition);
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Subscribe
    public void onSendFeedbackResponse(SendFeedbackEvent sendFeedbackEvent) {
        FeedbackResponse feedbackResponse = sendFeedbackEvent.getFeedbackResponse();
        if (feedbackResponse != null && feedbackResponse.isSuccess()) {
            Intent intent = new Intent(this, TravelActivity.class);
            startActivity(intent);
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
