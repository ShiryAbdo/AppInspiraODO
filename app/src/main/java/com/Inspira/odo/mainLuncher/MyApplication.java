package com.Inspira.odo.mainLuncher;

import android.app.Application;
import android.content.Context;
//import com.Inspira.odo.util.LocaleHelper;

/**
 * Created by ZeOwls on 11/4/2017.
 */

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
