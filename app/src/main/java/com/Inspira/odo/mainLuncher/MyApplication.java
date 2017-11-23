package com.Inspira.odo.mainLuncher;

import android.app.Application;
import android.content.Context;

import com.Inspira.odo.data.Model.Response;
import com.Inspira.odo.helper.LocaleHelper;

import java.util.ArrayList;

public class MyApplication extends Application {

    ArrayList<Response> responses = new ArrayList<Response>();
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    public ArrayList<Response> getResponses() {
        return responses;
    }

    public void setResponses(ArrayList<Response> responses) {
        this.responses = responses;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
