
package com.Inspira.odo.data.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("carType")
    @Expose
    private String carType;
    @SerializedName("carModel")
    @Expose
    private String carModel;
    @SerializedName("carYear")
    @Expose
    private String carYear;
    @SerializedName("orderList")
    @Expose
    private List<OrderList> orderList = null;


    public Order(String phoneNumber, String carType, String carModel, String carYear, List<OrderList> orderList) {
        this.phoneNumber = phoneNumber;
        this.carType = carType;
        this.carModel = carModel;
        this.carYear = carYear;
        this.orderList = orderList;
     }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public List<OrderList> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderList> orderList) {
        this.orderList = orderList;
    }


}
