
package com.Inspira.odo.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SellerData {

    @SerializedName("companyOnMap")
    @Expose
    private CompanyOnMap companyOnMap;
    @SerializedName("companyAddress")
    @Expose
    private String companyAddress;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("sellerPhoneNumber")
    @Expose
    private String sellerPhoneNumber;

    public CompanyOnMap getCompanyOnMap() {
        return companyOnMap;
    }

    public void setCompanyOnMap(CompanyOnMap companyOnMap) {
        this.companyOnMap = companyOnMap;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSellerPhoneNumber() {
        return sellerPhoneNumber;
    }

    public void setSellerPhoneNumber(String sellerPhoneNumber) {
        this.sellerPhoneNumber = sellerPhoneNumber;
    }

}
