package eu.isdc.aircheckapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import eu.isdc.aircheckapp.AircheckApp;
import eu.isdc.aircheckapp.R;

public class WelcomeActivity extends AppCompatActivity {

    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AircheckApp.getApp(this).getAircheckComponent().inject(this);
        setContentView(R.layout.activity_welcome);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location myLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        sharedPreferences
                .edit()
                .putString(LATITUDE, String.valueOf(myLocation.getLatitude()))
                .putString(LONGITUDE, String.valueOf(myLocation.getLongitude()))
                .apply();
        TextView departure = (TextView) findViewById(R.id.destination);
        Geocoder geocoder = new Geocoder(this);
        try {
            List<Address> addresses = geocoder.getFromLocation(myLocation.getLatitude(), myLocation.getLongitude(), 1);
            if (addresses.size() > 0) {
                if (departure != null) {
                    departure.setText(addresses.get(0).getLocality());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Button skip = (Button) findViewById(R.id.skipButton);
        if (skip != null) {
            skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(WelcomeActivity.this, TravelActivity.class);
                    startActivity(intent);
                }
            });
        }

        Button next = (Button) findViewById(R.id.nextButton);
        if (next != null) {
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(WelcomeActivity.this, SymptomsActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}
