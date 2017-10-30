package com.Inspira.odo.model;

import android.graphics.Color;

/**
 * Created by Andy on 10-Dec-14.
 */

public class ObjectDrawerItem {

    private int Number;
    private String name;

    public ObjectDrawerItem(int number, String name ) {
        this.Number = number;
        this.name = name;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
