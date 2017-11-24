
package com.Inspira.odo.data.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyOnMap implements Parcelable
{

    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    public final static Creator<CompanyOnMap> CREATOR = new Creator<CompanyOnMap>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CompanyOnMap createFromParcel(Parcel in) {
            return new CompanyOnMap(in);
        }

        public CompanyOnMap[] newArray(int size) {
            return (new CompanyOnMap[size]);
        }

    }
    ;

    protected CompanyOnMap(Parcel in) {
        this.longitude = ((String) in.readValue((String.class.getClassLoader())));
        this.latitude = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CompanyOnMap() {
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(longitude);
        dest.writeValue(latitude);
    }

    public int describeContents() {
        return  0;
    }

}
