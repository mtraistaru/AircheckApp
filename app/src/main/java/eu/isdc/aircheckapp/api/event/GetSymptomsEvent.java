package eu.isdc.aircheckapp.api.event;

import eu.isdc.aircheckapp.api.response.SymptomsResponse;
import retrofit.RetrofitError;

/**
 * Created by ancestor on 4/23/16.
 */
public class GetSymptomsEvent {

    private SymptomsResponse symptomsResponse;
    private RetrofitError retrofitError;

    public GetSymptomsEvent(SymptomsResponse symptomsResponse) {
        this.symptomsResponse = symptomsResponse;
    }

    public GetSymptomsEvent(RetrofitError retrofitError) {
        this.retrofitError = retrofitError;
    }

    public SymptomsResponse getSymptomsResponse() {
        return symptomsResponse;
    }

    public void setSymptomsResponse(SymptomsResponse symptomsResponse) {
        this.symptomsResponse = symptomsResponse;
    }

    public RetrofitError getRetrofitError() {
        return retrofitError;
    }

    public void setRetrofitError(RetrofitError retrofitError) {
        this.retrofitError = retrofitError;
    }
}
