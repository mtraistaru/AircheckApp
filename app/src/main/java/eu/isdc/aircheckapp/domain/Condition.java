package eu.isdc.aircheckapp.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ancestor on 4/23/16.
 */
public class Condition {

    @SerializedName("name")
    private String name;
    @SerializedName("probability")
    private String probability;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }
}
