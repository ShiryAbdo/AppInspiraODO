package com.Inspira.odo.mainLuncher;

import android.app.Application;
import android.content.Context;

import com.Inspira.odo.helper.LocaleHelper;

public class MyApplication extends Application {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
