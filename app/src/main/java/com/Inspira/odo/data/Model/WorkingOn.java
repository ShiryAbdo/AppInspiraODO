
package com.Inspira.odo.data.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WorkingOn {

    @SerializedName("carType")
    @Expose
    private String carType;
    @SerializedName("carModels")
    @Expose
    private List<CarModel> carModels = null;

    public String getCarType() {
        return carType;
    }

    public WorkingOn(String carType, List<CarModel> carModels) {
        this.carType = carType;
        this.carModels = carModels;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public List<CarModel> getCarModels() {
        return carModels;
    }

    public void setCarModels(List<CarModel> carModels) {
        this.carModels = carModels;
    }

}
