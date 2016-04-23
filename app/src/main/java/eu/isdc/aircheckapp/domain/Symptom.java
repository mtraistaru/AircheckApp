package eu.isdc.aircheckapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ancestor on 4/23/16.
 */
public class Symptom implements Parcelable {

    private String symptomName;
    private int rating;

    public Symptom(String symptomName) {
        this.symptomName = symptomName;
    }

    public String getSymptomName() {
        return symptomName;
    }

    public void setSymptomName(String symptomName) {
        this.symptomName = symptomName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    protected Symptom(Parcel in) {
        symptomName = in.readString();
        rating = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(symptomName);
        dest.writeInt(rating);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Symptom> CREATOR = new Parcelable.Creator<Symptom>() {
        @Override
        public Symptom createFromParcel(Parcel in) {
            return new Symptom(in);
        }

        @Override
        public Symptom[] newArray(int size) {
            return new Symptom[size];
        }
    };
}