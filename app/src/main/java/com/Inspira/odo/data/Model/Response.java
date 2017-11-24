
package com.Inspira.odo.data.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response implements Parcelable
{

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("sellerData")
    @Expose
    private SellerData sellerData;
    public final static Creator<Response> CREATOR = new Creator<Response>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Response createFromParcel(Parcel in)
        {
            return new Response(in);
        }

        public Response[] newArray(int size) {
            return (new Response[size]);
        }

    }
    ;

    protected Response(Parcel in) {
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((String) in.readValue((String.class.getClassLoader())));
        this.sellerData = ((SellerData) in.readValue((SellerData.class.getClassLoader())));
    }

    public Response() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return Double.parseDouble(price);
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(description);
        dest.writeValue(price);
        dest.writeValue(sellerData);
    }

    public int describeContents() {
        return  0;
    }

}
