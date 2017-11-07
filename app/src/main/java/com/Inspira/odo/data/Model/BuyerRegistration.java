package com.Inspira.odo.data.Model;

import com.google.gson.annotations.SerializedName;



public class BuyerRegistration {
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("fullName")
    private String fullName;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("hashVal")
    private String hashVal;
    @SerializedName("userType")
    private String userType;

    public BuyerRegistration(String phoneNumber, String fullName, String email, String password) {
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.hashVal = "1bu4i3iug262bi6u22j2ij3bug5ug45i";
        this.userType = "buyer";
    }
}
