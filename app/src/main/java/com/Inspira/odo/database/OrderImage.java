
package com.Inspira.odo.database;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderImage {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("originalName")
    @Expose
    private String originalName;

    public OrderImage(String originalName) {
        this.originalName = originalName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

}
