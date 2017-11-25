
package com.Inspira.odo.sellerData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarDetails {

    @SerializedName("carType")
    @Expose
    private String carType;
    @SerializedName("carModel")
    @Expose
    private String carModel;
    @SerializedName("carYear")
    @Expose
    private String carYear;

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

}
