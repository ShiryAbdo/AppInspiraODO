
package com.Inspira.odo.data.Model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Responses implements Parcelable
{

    @SerializedName("responses")
    @Expose
    private List<Response> responses = new ArrayList<>();
    public final static Creator<Responses> CREATOR = new Creator<Responses>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Responses createFromParcel(Parcel in) {
            return new Responses(in);
        }

        public Responses[] newArray(int size) {
            return (new Responses[size]);
        }

    }
    ;

    protected Responses(Parcel in) {
        in.readList(this.responses, (Response.class.getClassLoader()));
    }

    public Responses() {
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(responses);
    }

    public int describeContents() {
        return  0;
    }

}
