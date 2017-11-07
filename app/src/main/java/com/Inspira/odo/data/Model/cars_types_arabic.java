package com.Inspira.odo.data.Model;

/**
 * Created by shirya on 07/11/17.
 */

public class cars_types_arabic {
    private   String  carType ;
    private   String  carModels ;
    private   String    years ;


    public cars_types_arabic() {
    }

    public cars_types_arabic(String carType, String carModels, String years) {
        this.carType = carType;
        this.carModels = carModels;
        this.years = years;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarModels() {
        return carModels;
    }

    public void setCarModels(String carModels) {
        this.carModels = carModels;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }
}
