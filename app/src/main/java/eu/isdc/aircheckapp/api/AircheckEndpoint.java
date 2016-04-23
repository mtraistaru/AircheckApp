package eu.isdc.aircheckapp.api;

import eu.isdc.aircheckapp.api.request.FeedbackRequest;
import eu.isdc.aircheckapp.api.response.CheckDestinationResponse;
import eu.isdc.aircheckapp.api.response.ConditionsResponse;
import eu.isdc.aircheckapp.api.response.FeedbackResponse;
import eu.isdc.aircheckapp.api.response.SymptomsResponse;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Pure description of endpoints go here.
 * Created by Mihai.Traistaru on 22-Apr-16.
 */
public interface AircheckEndpoint {

    @GET("/symptoms")
    void getSymptoms(@Query("text") String text, Callback<SymptomsResponse> callback);

    @GET("/conditions")
    void getConditions(@Query("text") String text, Callback<ConditionsResponse> callback);

    @POST("/feedback")
    void sendFeedback(@Body FeedbackRequest feedbackRequest, Callback<FeedbackResponse> callback);

    @GET("/check")
    void checkDestination(@Query("lat") double latitude, @Query("long") double longitude, Callback<CheckDestinationResponse> callback);
}
