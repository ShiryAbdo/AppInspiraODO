
package com.Inspira.odo.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddsFavourite {

    @SerializedName("buyerPhoneNumber")
    @Expose
    private String buyerPhoneNumber;
    @SerializedName("sellerPhoneNumber")
    @Expose
    private String sellerPhoneNumber;
    @SerializedName("orderId")
    @Expose
    private String orderId;

    public AddsFavourite(String buyerPhoneNumber, String sellerPhoneNumber, String orderId) {
        this.buyerPhoneNumber = buyerPhoneNumber;
        this.sellerPhoneNumber = sellerPhoneNumber;
        this.orderId = orderId;
    }

    public String getBuyerPhoneNumber() {
        return buyerPhoneNumber;
    }

    public void setBuyerPhoneNumber(String buyerPhoneNumber) {
        this.buyerPhoneNumber = buyerPhoneNumber;
    }

    public String getSellerPhoneNumber() {
        return sellerPhoneNumber;
    }

    public void setSellerPhoneNumber(String sellerPhoneNumber) {
        this.sellerPhoneNumber = sellerPhoneNumber;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}
