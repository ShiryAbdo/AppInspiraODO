package com.Inspira.odo.data.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shirya on 07/11/17.
 */

public class SellerRegistration {

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
    @SerializedName("companyName")
    private String companyName;
    @SerializedName("companyAddress")
    private String companyAddress;
    @SerializedName("companyOnMap")
    private String companyOnMap;



}
