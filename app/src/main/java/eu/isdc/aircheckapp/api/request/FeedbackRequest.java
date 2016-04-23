package eu.isdc.aircheckapp.api.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import eu.isdc.aircheckapp.domain.Location;
import eu.isdc.aircheckapp.domain.Symptom;

/**
 * Created by ancestor on 4/23/16.
 */
public class FeedbackRequest {

    @SerializedName("location")
    private Location location;
    @SerializedName("symptoms")
    private List<Symptom> symptoms;
    @SerializedName("condition")
    private String condition;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
