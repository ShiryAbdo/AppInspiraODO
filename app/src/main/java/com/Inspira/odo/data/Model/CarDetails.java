
package com.Inspira.odo.data.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarDetails implements Parcelable
{

    @SerializedName("carType")
    @Expose
    private String carType;
    @SerializedName("carModel")
    @Expose
    private String carModel;
    @SerializedName("carYear")
    @Expose
    private String carYear;
    public final static Creator<CarDetails> CREATOR = new Creator<CarDetails>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CarDetails createFromParcel(Parcel in) {
            return new CarDetails(in);
        }

        public CarDetails[] newArray(int size) {
            return (new CarDetails[size]);
        }

    }
    ;

    protected CarDetails(Parcel in) {
        this.carType = ((String) in.readValue((String.class.getClassLoader())));
        this.carModel = ((String) in.readValue((String.class.getClassLoader())));
        this.carYear = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CarDetails() {
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarYear() {
        return carYear;
    }

    public void setCarYear(String carYear) {
        this.carYear = carYear;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(carType);
        dest.writeValue(carModel);
        dest.writeValue(carYear);
    }

    public int describeContents() {
        return  0;
    }

}
