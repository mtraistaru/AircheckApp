package eu.isdc.aircheckapp.api;

import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Inject;

import eu.isdc.aircheckapp.AircheckApp;
import eu.isdc.aircheckapp.api.event.CheckDestinationEvent;
import eu.isdc.aircheckapp.api.event.GetConditionsEvent;
import eu.isdc.aircheckapp.api.event.GetSymptomsEvent;
import eu.isdc.aircheckapp.api.event.SendFeedbackEvent;
import eu.isdc.aircheckapp.api.request.FeedbackRequest;
import eu.isdc.aircheckapp.api.response.CheckDestinationResponse;
import eu.isdc.aircheckapp.api.response.ConditionsResponse;
import eu.isdc.aircheckapp.api.response.FeedbackResponse;
import eu.isdc.aircheckapp.api.response.SymptomsResponse;
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

    public void getSymptoms(String text) {
        aircheckEndpoint.getSymptoms(text, new Callback<SymptomsResponse>() {
            @Override
            public void success(SymptomsResponse symptomsResponse, Response response) {
                bus.post(new GetSymptomsEvent(symptomsResponse));
            }

            @Override
            public void failure(RetrofitError error) {
                bus.post(new GetSymptomsEvent(error));
            }
        });
    }

    public void getConditions(String text) {
        aircheckEndpoint.getConditions(text, new Callback<ConditionsResponse>() {
            @Override
            public void success(ConditionsResponse conditionsResponse, Response response) {
                bus.post(new GetConditionsEvent(conditionsResponse));
            }

            @Override
            public void failure(RetrofitError error) {
                bus.post(new GetConditionsEvent(error));
            }
        });
    }

    public void sendFeedback(FeedbackRequest feedbackRequest) {
        aircheckEndpoint.sendFeedback(feedbackRequest, new Callback<FeedbackResponse>() {
            @Override
            public void success(FeedbackResponse feedbackResponse, Response response) {
                bus.post(new SendFeedbackEvent(feedbackResponse));
            }

            @Override
            public void failure(RetrofitError error) {
                bus.post(new SendFeedbackEvent(error));
            }
        });
    }

    public void checkDestination(double latitude, double longitude) {
        aircheckEndpoint.checkDestination(latitude, longitude, new Callback<CheckDestinationResponse>() {
            @Override
            public void success(CheckDestinationResponse checkDestinationResponse, Response response) {
                bus.post(new CheckDestinationEvent(checkDestinationResponse));
            }

            @Override
            public void failure(RetrofitError error) {
                bus.post(new CheckDestinationEvent(error));
            }
        });
    }
}
