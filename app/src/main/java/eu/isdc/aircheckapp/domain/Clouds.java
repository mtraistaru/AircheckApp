package eu.isdc.aircheckapp.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mihai.Traistaru on 22-Apr-16.
 */
public class Clouds {

    @SerializedName("all")
    private int all;

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }
}
