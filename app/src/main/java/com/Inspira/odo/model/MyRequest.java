
package com.Inspira.odo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyRequest {

    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;

    public MyRequest(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
