package eu.isdc.aircheckapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ancestor on 4/23/16.
 */
public class Pollution implements Parcelable {

    @SerializedName("aqi")
    @Expose
    private Integer aqi;
    @SerializedName("pollutant")
    @Expose
    private Pollutant pollutant;

    public Integer getAqi() {
        return aqi;
    }

    public void setAqi(Integer aqi) {
        this.aqi = aqi;
    }

    public Pollutant getPollutant() {
        return pollutant;
    }

    public void setPollutant(Pollutant pollutant) {
        this.pollutant = pollutant;
    }

    protected Pollution(Parcel in) {
        aqi = in.readByte() == 0x00 ? null : in.readInt();
        pollutant = (Pollutant) in.readValue(Pollutant.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (aqi == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(aqi);
        }
        dest.writeValue(pollutant);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Pollution> CREATOR = new Parcelable.Creator<Pollution>() {
        @Override
        public Pollution createFromParcel(Parcel in) {
            return new Pollution(in);
        }

        @Override
        public Pollution[] newArray(int size) {
            return new Pollution[size];
        }
    };
}