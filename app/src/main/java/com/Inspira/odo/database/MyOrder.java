
package com.Inspira.odo.database;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.Inspira.odo.data.Model.CarDetails;
import com.Inspira.odo.data.Model.Response;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyOrder implements Parcelable
{

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
    private int v;
    @SerializedName("orderImages")
    @Expose
    private List<OrderImage> orderImages = null;
    @SerializedName("responses")
    @Expose
    private List<Response> responses = null;
    @SerializedName("carDetails")
    @Expose
    private CarDetails carDetails;
    @SerializedName("Date")
    @Expose
    private String date;
    public final static Creator<MyOrder> CREATOR = new Creator<MyOrder>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MyOrder createFromParcel(Parcel in) {
            return new MyOrder(in);
        }

        public MyOrder[] newArray(int size) {
            return (new MyOrder[size]);
        }

    }
    ;

    protected MyOrder(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.buyerPhoneNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.orderPartType = ((String) in.readValue((String.class.getClassLoader())));
        this.order = ((Order) in.readValue((Order.class.getClassLoader())));
        this.v = ((int) in.readValue((int.class.getClassLoader())));
        in.readList(this.orderImages, (Object.class.getClassLoader()));
        in.readList(this.responses, (Response.class.getClassLoader()));
        this.carDetails = ((CarDetails) in.readValue((CarDetails.class.getClassLoader())));
        this.date = ((String) in.readValue((String.class.getClassLoader())));
    }

    public MyOrder() {
    }

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

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public List<OrderImage> getOrderImages() {
        return orderImages;
    }

    public void setOrderImages(List<OrderImage> orderImages) {
        this.orderImages = orderImages;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(buyerPhoneNumber);
        dest.writeValue(orderPartType);
        dest.writeValue(order);
        dest.writeValue(v);
        dest.writeList(orderImages);
        dest.writeList(responses);
        dest.writeValue(carDetails);
        dest.writeValue(date);
    }

    public int describeContents() {
        return  0;
    }

}
