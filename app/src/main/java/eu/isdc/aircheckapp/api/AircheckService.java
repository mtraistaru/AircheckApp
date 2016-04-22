package eu.isdc.aircheckapp.api;

import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Inject;

import eu.isdc.aircheckapp.AircheckApp;
import eu.isdc.aircheckapp.Constants;
import eu.isdc.aircheckapp.api.event.GetWeatherByCityEvent;
import eu.isdc.aircheckapp.domain.GetWeatherByCityResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * What the UI calls. Responses are returned on the bus. Objects interested must be subscribed to the right events.
 * Created by Mihai.Traistaru on 22-Apr-16.
 */
public class AircheckService {

    @Inject
    AircheckEndpoint aircheckEndpoint;
    @Inject
    Bus bus;

    public AircheckService(Context context) {
        AircheckApp.getApp(context).getAircheckComponent().inject(this);
    }

    public void getWeatherByCity(String city) {
        aircheckEndpoint.getWeatherByCity(city, Constants.API_KEY, new Callback<GetWeatherByCityResponse>() {
            @Override
            public void success(GetWeatherByCityResponse getWeatherByCityResponse, Response response) {
                bus.post(new GetWeatherByCityEvent(getWeatherByCityResponse));
            }

            @Override
            public void failure(RetrofitError error) {
                bus.post(new GetWeatherByCityEvent(error));
            }
        });
    }
}
