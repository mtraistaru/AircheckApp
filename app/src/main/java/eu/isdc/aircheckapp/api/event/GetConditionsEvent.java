package eu.isdc.aircheckapp.api.event;

import eu.isdc.aircheckapp.api.response.ConditionsResponse;
import retrofit.RetrofitError;

/**
 * Created by ancestor on 4/23/16.
 */
public class GetConditionsEvent {

    private ConditionsResponse conditionsResponse;
    private RetrofitError retrofitError;

    public GetConditionsEvent(ConditionsResponse conditionsResponse) {
        this.conditionsResponse = conditionsResponse;
    }

    public GetConditionsEvent(RetrofitError retrofitError) {
        this.retrofitError = retrofitError;
    }

    public ConditionsResponse getConditionsResponse() {
        return conditionsResponse;
    }

    public void setConditionsResponse(ConditionsResponse conditionsResponse) {
        this.conditionsResponse = conditionsResponse;
    }

    public RetrofitError getRetrofitError() {
        return retrofitError;
    }

    public void setRetrofitError(RetrofitError retrofitError) {
        this.retrofitError = retrofitError;
    }
}
