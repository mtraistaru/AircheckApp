package eu.isdc.aircheckapp.api.event;

import eu.isdc.aircheckapp.api.response.FeedbackResponse;
import retrofit.RetrofitError;

/**
 * Created by ancestor on 4/23/16.
 */
public class SendFeedbackEvent {

    private FeedbackResponse feedbackResponse;
    private RetrofitError retrofitError;

    public SendFeedbackEvent(FeedbackResponse feedbackResponse) {
        this.feedbackResponse = feedbackResponse;
    }

    public SendFeedbackEvent(RetrofitError retrofitError) {
        this.retrofitError = retrofitError;
    }

    public FeedbackResponse getFeedbackResponse() {
        return feedbackResponse;
    }

    public void setFeedbackResponse(FeedbackResponse feedbackResponse) {
        this.feedbackResponse = feedbackResponse;
    }

    public RetrofitError getRetrofitError() {
        return retrofitError;
    }

    public void setRetrofitError(RetrofitError retrofitError) {
        this.retrofitError = retrofitError;
    }
}
