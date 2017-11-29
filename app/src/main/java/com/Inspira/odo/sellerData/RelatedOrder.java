
package com.Inspira.odo.sellerData;

import java.util.List;

import com.Inspira.odo.database.OrderImage;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelatedOrder {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("buyerPhoneNumber")
    @Expose
    private String buyerPhoneNumber;
    @SerializedName("orderPartType")
    @Expose
    private String orderPartType;
    @SerializedName("order")
    @Expose
    private Order order;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("orderImages")
    @Expose
    private List<OrderImage> orderImages = null;
    @SerializedName("responses")
    @Expose
    private List<Object> responses = null;
    @SerializedName("carDetails")
    @Expose
    private CarDetails carDetails;
    @SerializedName("Date")
    @Expose
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuyerPhoneNumber() {
        return buyerPhoneNumber;
    }

    public void setBuyerPhoneNumber(String buyerPhoneNumber) {
        this.buyerPhoneNumber = buyerPhoneNumber;
    }

    public String getOrderPartType() {
        return orderPartType;
    }

    public void setOrderPartType(String orderPartType) {
        this.orderPartType = orderPartType;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public List<OrderImage> getOrderImages() {
        return orderImages;
    }

    public void setOrderImages(List<OrderImage> orderImages) {
        this.orderImages = orderImages;
    }

    public List<Object> getResponses() {
        return responses;
    }

    public void setResponses(List<Object> responses) {
        this.responses = responses;
    }

    public CarDetails getCarDetails() {
        return carDetails;
    }

    public void setCarDetails(CarDetails carDetails) {
        this.carDetails = carDetails;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
