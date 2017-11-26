
package com.Inspira.odo.sellerData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespondToOrder {

    @SerializedName("sellerPhoneNumber")
    @Expose
    private String sellerPhoneNumber;
    @SerializedName("buyerPhoneNumber")
    @Expose
    private String buyerPhoneNumber;
    @SerializedName("orderID")
    @Expose
    private String orderID;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("description")
    @Expose
    private String description;


    public RespondToOrder(String sellerPhoneNumber, String buyerPhoneNumber, String orderID, String price,
                          String description) {
        this.sellerPhoneNumber = sellerPhoneNumber;
        this.buyerPhoneNumber = buyerPhoneNumber;
        this.orderID = orderID;
        this.price = price;
        this.description = description;
    }

    public String getSellerPhoneNumber() {
        return sellerPhoneNumber;
    }

    public void setSellerPhoneNumber(String sellerPhoneNumber) {
        this.sellerPhoneNumber = sellerPhoneNumber;
    }

    public String getBuyerPhoneNumber() {
        return buyerPhoneNumber;
    }

    public void setBuyerPhoneNumber(String buyerPhoneNumber) {
        this.buyerPhoneNumber = buyerPhoneNumber;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
