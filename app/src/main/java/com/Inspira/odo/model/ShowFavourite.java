
package com.Inspira.odo.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShowFavourite {

    @SerializedName("orderID")
    @Expose
    private String orderID;
    @SerializedName("responses")
    @Expose
    private List<String> responses = null;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public List<String> getResponses() {
        return responses;
    }

    public void setResponses(List<String> responses) {
        this.responses = responses;
    }

}
