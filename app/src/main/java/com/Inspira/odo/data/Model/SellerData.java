
package com.Inspira.odo.data.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SellerData implements Parcelable
{

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
    public final static Creator<SellerData> CREATOR = new Creator<SellerData>() {


        @SuppressWarnings({
            "unchecked"
        })
        public SellerData createFromParcel(Parcel in) {
            return new SellerData(in);
        }

        public SellerData[] newArray(int size) {
            return (new SellerData[size]);
        }

    }
    ;

    protected SellerData(Parcel in) {
        this.companyOnMap = ((CompanyOnMap) in.readValue((CompanyOnMap.class.getClassLoader())));
        this.companyAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.companyName = ((String) in.readValue((String.class.getClassLoader())));
        this.sellerPhoneNumber = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SellerData() {
    }

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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(companyOnMap);
        dest.writeValue(companyAddress);
        dest.writeValue(companyName);
        dest.writeValue(sellerPhoneNumber);
    }

    public int describeContents() {
        return  0;
    }

}
