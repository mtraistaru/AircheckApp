package eu.isdc.aircheckapp.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ancestor on 4/23/16.
 */
public class ConditionsResponse {

    @SerializedName("conditions")
    private List<String> conditions;

    public List<String> getConditions() {
        return conditions;
    }

    public void setConditions(List<String> conditions) {
        this.conditions = conditions;
    }
}
