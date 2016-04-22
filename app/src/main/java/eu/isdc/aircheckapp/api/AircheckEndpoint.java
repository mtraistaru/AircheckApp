package eu.isdc.aircheckapp.api;

import eu.isdc.aircheckapp.domain.GetWeatherByCityResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Pure description of endpoints go here.
 * Created by Mihai.Traistaru on 22-Apr-16.
 */
public interface AircheckEndpoint {

    @GET("/weather")
    void getWeatherByCity(@Query("q") String city, @Query("APPID") String apiKey, Callback<GetWeatherByCityResponse> response);
}
