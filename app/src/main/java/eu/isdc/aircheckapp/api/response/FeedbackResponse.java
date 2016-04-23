package eu.isdc.aircheckapp.api.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ancestor on 4/23/16.
 */
public class FeedbackResponse {

    @SerializedName("success")
    private boolean isSuccess;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
