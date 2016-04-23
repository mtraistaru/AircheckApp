package eu.isdc.aircheckapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ancestor on 4/23/16.
 */
public class Pollutant implements Parcelable {

    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("effects")
    private String effects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEffects() {
        return effects;
    }

    public void setEffects(String effects) {
        this.effects = effects;
    }

    protected Pollutant(Parcel in) {
        name = in.readString();
        description = in.readString();
        effects = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(effects);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Pollutant> CREATOR = new Parcelable.Creator<Pollutant>() {
        @Override
        public Pollutant createFromParcel(Parcel in) {
            return new Pollutant(in);
        }

        @Override
        public Pollutant[] newArray(int size) {
            return new Pollutant[size];
        }
    };
}