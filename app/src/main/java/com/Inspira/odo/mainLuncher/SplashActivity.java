package com.Inspira.odo.mainLuncher;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

import com.Inspira.odo.R;
import com.Inspira.odo.buyerUi.NavigationDrawerBuyer;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.helper.LocaleHelper;
import com.Inspira.odo.sellerUi.NavigationDrawerSeler;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;
    SharedPreferencesManager sharedPreferencesManager ;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        sharedPreferencesManager = new SharedPreferencesManager(this);


        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // Check if user is already logged in or not
                if (sharedPreferencesManager.isLoggedIn()) {
                    String user =   sharedPreferencesManager.getUserType();
                    if(user.equals("buyer")){

                        Intent intent = new Intent(SplashActivity.this, NavigationDrawerBuyer.class);
                        startActivity(intent);
                        finish();

                    }else if (user.equals("seller")){
                        Intent intent = new Intent(SplashActivity.this, NavigationDrawerSeler.class);
                        startActivity(intent);
                        finish();

                    }

                }else {
                    Intent intent = new Intent(SplashActivity.this, LogInActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }





}
