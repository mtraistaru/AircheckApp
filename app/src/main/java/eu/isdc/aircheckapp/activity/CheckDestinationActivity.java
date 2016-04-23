package eu.isdc.aircheckapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import eu.isdc.aircheckapp.R;
import eu.isdc.aircheckapp.adapter.ConditionsAdapter;
import eu.isdc.aircheckapp.api.response.CheckDestinationResponse;
import eu.isdc.aircheckapp.domain.Condition;

/**
 * Created by ancestor on 4/23/16.
 */
public class CheckDestinationActivity extends AppCompatActivity {

    public static final String DESTINATION_INFORMATION = "destination_information";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_destination);
        CheckDestinationResponse checkDestinationResponse = getIntent().getExtras().getParcelable(DESTINATION_INFORMATION);
        if (checkDestinationResponse != null) {
            ListView conditionsListView = (ListView) findViewById(R.id.conditionsList);
            if (!checkDestinationResponse.getConditions().isEmpty()) {
                ConditionsAdapter conditionsAdapter = new ConditionsAdapter(this, R.layout.item_conditions);
                conditionsAdapter.addAll(checkDestinationResponse.getConditions());
                if (conditionsListView != null) {
                    conditionsListView.setAdapter(conditionsAdapter);
                }
            } else {
                TextView emptyListMessage = (TextView) findViewById(R.id.conditionsEmptyListMessage);
                if (emptyListMessage != null) {
                    emptyListMessage.setVisibility(View.VISIBLE);
                }
                if (conditionsListView != null) {
                    conditionsListView.setVisibility(View.GONE);
                }
            }
            ListView standardInfoListView = (ListView) findViewById(R.id.standardInfoList);
            ConditionsAdapter standardInfoAdapter = new ConditionsAdapter(this, R.layout.item_conditions);
            Condition temperature = new Condition();
            temperature.setName(getResources().getString(R.string.Temperature));
            temperature.setProbability(String.valueOf(checkDestinationResponse.getWeather().getTemp()));
            standardInfoAdapter.add(temperature);
            Condition humidity = new Condition();
            humidity.setName(getResources().getString(R.string.Humidity));
            humidity.setProbability(String.valueOf(checkDestinationResponse.getWeather().getHumidity()));
            standardInfoAdapter.add(humidity);
            Condition pollution = new Condition();
            pollution.setName(getResources().getString(R.string.Pollution));
            pollution.setProbability(String.valueOf(checkDestinationResponse.getPollution().getAqi()));
            standardInfoAdapter.add(pollution);
            if (standardInfoListView != null) {
                standardInfoListView.setAdapter(standardInfoAdapter);
            }
        }
    }
}
