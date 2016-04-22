package eu.isdc.aircheckapp.api.event;

import eu.isdc.aircheckapp.domain.GetWeatherByCityResponse;
import retrofit.RetrofitError;

/**
 * Event posted on the bus on the receiving of a getWeatherByCity call response.
 * Created by Mihai.Traistaru on 22-Apr-16.
 */
public class GetWeatherByCityEvent {

    private GetWeatherByCityResponse getWeatherByCityResponse;
    private RetrofitError retrofitError;

    public GetWeatherByCityEvent(GetWeatherByCityResponse getWeatherByCityResponse) {
        this.getWeatherByCityResponse = getWeatherByCityResponse;
    }

    public GetWeatherByCityEvent(RetrofitError retrofitError) {
        this.retrofitError = retrofitError;
    }

    public GetWeatherByCityResponse getGetWeatherByCityResponse() {
        return getWeatherByCityResponse;
    }

    public void setGetWeatherByCityResponse(GetWeatherByCityResponse getWeatherByCityResponse) {
        this.getWeatherByCityResponse = getWeatherByCityResponse;
    }

    public RetrofitError getRetrofitError() {
        return retrofitError;
    }

    public void setRetrofitError(RetrofitError retrofitError) {
        this.retrofitError = retrofitError;
    }
}
