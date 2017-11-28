package com.Inspira.odo.adds;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by shirya on 28/11/17.
 */

public class AdViewClass {
    private   Activity activity;
    AdView adView ;
    Context context ;
    private static final String TOAST_TEXT ="loading .." ;

    public AdViewClass(Activity activity, AdView adView, Context context) {
        this.activity = activity;
        this.adView = adView;
        this.context = context;
    }

    public  void  showAdds(){
        // Load an ad into the AdMob banner view.
        adView = (AdView)activity.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
        Toast.makeText(context, TOAST_TEXT, Toast.LENGTH_LONG).show();

    }
}
