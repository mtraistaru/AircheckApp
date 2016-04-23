package eu.isdc.aircheckapp.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ancestor on 4/23/16.
 */
public class SymptomsResponse {

    @SerializedName("symptoms")
    private List<String> symptoms;

    public List<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<String> symptoms) {
        this.symptoms = symptoms;
    }
}
