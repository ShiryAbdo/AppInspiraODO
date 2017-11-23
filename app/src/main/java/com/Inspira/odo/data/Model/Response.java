
package com.Inspira.odo.data.Model;

import android.content.Context;

 import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Response  {
    public transient Context myContext;

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("sellerData")
    @Expose
    private SellerData sellerData;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public SellerData getSellerData() {
        return sellerData;
    }

    public void setSellerData(SellerData sellerData) {
        this.sellerData = sellerData;
    }

}
