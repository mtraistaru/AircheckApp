package eu.isdc.aircheckapp.activity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import eu.isdc.aircheckapp.AircheckApp;
import eu.isdc.aircheckapp.R;
import eu.isdc.aircheckapp.api.AircheckService;
import eu.isdc.aircheckapp.api.event.CheckDestinationEvent;
import eu.isdc.aircheckapp.api.response.CheckDestinationResponse;

/**
 * Created by ancestor on 4/23/16.
 */
public class TravelActivity extends AppCompatActivity {

    @Inject
    AircheckService aircheckService;

    @Inject
    Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AircheckApp.getApp(this).getAircheckComponent().inject(this);
        setContentView(R.layout.activity_travel);
        final EditText destination = (EditText) findViewById(R.id.destination);
        Button check = (Button) findViewById(R.id.check);
        final Geocoder geocoder = new Geocoder(this);
        if (check != null) {
            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        List<Address> addresses = geocoder.getFromLocationName(destination.getText().toString(), 1);
                        if (addresses.size() > 0) {
                            Address address = addresses.get(0);
                            aircheckService.checkDestination(address.getLatitude(), address.getLongitude());
                        } else {
                            Snackbar.make(view, R.string.Destination_Error, Snackbar.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Subscribe
    public void onCheckDestinationEvent(CheckDestinationEvent checkDestinationEvent) {
        CheckDestinationResponse checkDestinationResponse = checkDestinationEvent.getCheckDestinationResponse();
        if (checkDestinationResponse != null) {
            Intent intent = new Intent(this, CheckDestinationActivity.class);
            intent.putExtra(CheckDestinationActivity.DESTINATION_INFORMATION, checkDestinationResponse);
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
