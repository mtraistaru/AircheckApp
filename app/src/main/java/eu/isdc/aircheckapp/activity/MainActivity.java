package eu.isdc.aircheckapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import eu.isdc.aircheckapp.AircheckApp;
import eu.isdc.aircheckapp.R;
import eu.isdc.aircheckapp.api.AircheckService;
import eu.isdc.aircheckapp.api.event.GetWeatherByCityEvent;

public class MainActivity extends AppCompatActivity {

    @Inject
    Bus bus;

    @Inject
    AircheckService aircheckService;

    TextView response;
    Button getData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AircheckApp.getApp(this).getAircheckComponent().inject(this);
        setContentView(R.layout.activity_main);
        response = (TextView) findViewById(R.id.response);
        getData = (Button) findViewById(R.id.getData);
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aircheckService.getWeatherByCity("Cluj");
            }
        });
    }

    @Subscribe
    public void onGetWeatherByCityEvent(GetWeatherByCityEvent getWeatherByCityEvent) {
        response.setText(new Gson().toJson(getWeatherByCityEvent.getGetWeatherByCityResponse()));
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
