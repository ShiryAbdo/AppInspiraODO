
package com.Inspira.odo.database;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Order implements Parcelable
{

    @SerializedName("partType")
    @Expose
    private String partType;
    @SerializedName("part")
    @Expose
    private String part;
    @SerializedName("engineCapacity")
    @Expose
    private String engineCapacity;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("ampere")
    @Expose
    private String ampere;
    public final static Creator<Order> CREATOR = new Creator<Order>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        public Order[] newArray(int size) {
            return (new Order[size]);
        }

    }
    ;

    protected Order(Parcel in) {
        this.partType = ((String) in.readValue((String.class.getClassLoader())));
        this.part = ((String) in.readValue((String.class.getClassLoader())));
        this.engineCapacity = ((String) in.readValue((String.class.getClassLoader())));
        this.color = ((String) in.readValue((String.class.getClassLoader())));
        this.ampere = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Order(String phone, String carType, String carModel, String carYear, List<OrderList> orderList, List<OrderImage> orderImages) {
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAmpere() {
        return ampere;
    }

    public void setAmpere(String ampere) {
        this.ampere = ampere;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(partType);
        dest.writeValue(part);
        dest.writeValue(engineCapacity);
        dest.writeValue(color);
        dest.writeValue(ampere);
    }

    public int describeContents() {
        return  0;
    }

}
