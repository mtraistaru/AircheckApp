package eu.isdc.aircheckapp.api.event;

import eu.isdc.aircheckapp.api.response.CheckDestinationResponse;
import retrofit.RetrofitError;

/**
 * Created by ancestor on 4/23/16.
 */
public class CheckDestinationEvent {

    private CheckDestinationResponse checkDestinationResponse;
    private RetrofitError retrofitError;

    public CheckDestinationEvent(CheckDestinationResponse checkDestinationResponse) {
        this.checkDestinationResponse = checkDestinationResponse;
    }

    public CheckDestinationEvent(RetrofitError retrofitError) {
        this.retrofitError = retrofitError;
    }

    public CheckDestinationResponse getCheckDestinationResponse() {
        return checkDestinationResponse;
    }

    public void setCheckDestinationResponse(CheckDestinationResponse checkDestinationResponse) {
        this.checkDestinationResponse = checkDestinationResponse;
    }

    public RetrofitError getRetrofitError() {
        return retrofitError;
    }

    public void setRetrofitError(RetrofitError retrofitError) {
        this.retrofitError = retrofitError;
    }
}
