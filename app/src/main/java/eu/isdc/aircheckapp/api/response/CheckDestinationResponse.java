package eu.isdc.aircheckapp.api.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import eu.isdc.aircheckapp.domain.Condition;
import eu.isdc.aircheckapp.domain.Pollution;
import eu.isdc.aircheckapp.domain.Weather;

/**
 * Created by ancestor on 4/23/16.
 */
public class CheckDestinationResponse implements Parcelable {

    @SerializedName("conditions")
    private List<Condition> conditions = new ArrayList<>();
    @SerializedName("weather")
    private Weather weather;
    @SerializedName("pollution")
    private Pollution pollution;

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Pollution getPollution() {
        return pollution;
    }

    public void setPollution(Pollution pollution) {
        this.pollution = pollution;
    }

    protected CheckDestinationResponse(Parcel in) {
        if (in.readByte() == 0x01) {
            conditions = new ArrayList<Condition>();
            in.readList(conditions, Condition.class.getClassLoader());
        } else {
            conditions = null;
        }
        weather = (Weather) in.readValue(Weather.class.getClassLoader());
        pollution = (Pollution) in.readValue(Pollution.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (conditions == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(conditions);
        }
        dest.writeValue(weather);
        dest.writeValue(pollution);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CheckDestinationResponse> CREATOR = new Parcelable.Creator<CheckDestinationResponse>() {
        @Override
        public CheckDestinationResponse createFromParcel(Parcel in) {
            return new CheckDestinationResponse(in);
        }

        @Override
        public CheckDestinationResponse[] newArray(int size) {
            return new CheckDestinationResponse[size];
        }
    };
}