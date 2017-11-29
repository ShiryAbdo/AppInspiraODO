package com.Inspira.odo.buyerUi;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.data.Model.Response;
import com.Inspira.odo.helper.LocaleHelper;
import com.Inspira.odo.mainLuncher.MyApplication;
import com.Inspira.odo.mainLuncher.RegistrationActivity;
import com.Inspira.odo.model.FilterData;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class detalisOfRequest extends AppCompatActivity {
    TextView price, name_of_part, Date, LOCATION;
    FloatingActionButton fab;
    Button Get_Direction;
    Bundle bundle;
    List<Response> mSelectedList ;
     MyApplication myApplication ;
    String prices, SellerPhoneNumber, Latitude, Longitude, name, CompanyAddress;
    ImageView back ;
    LocaleHelper localeHelper ;
    AdView adView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalis_of_request);
         adView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);
        back=(ImageView)findViewById(R.id.image);
        localeHelper= new LocaleHelper();
        String lange=  localeHelper.getLanguage(detalisOfRequest.this);
        if(lange.equals("ar")){
            back.setImageResource(R.drawable.back_wiht);
        }else if(lange.equals("en")){
            back.setImageResource(R.drawable.back_eft_whit);
        }

        price = (TextView) findViewById(R.id.price);
        name_of_part = (TextView) findViewById(R.id.name_of_part);
        Date = (TextView) findViewById(R.id.Date);
        LOCATION = (TextView) findViewById(R.id.LOCATION);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            prices = bundle.getString("price");
            SellerPhoneNumber = bundle.getString("SellerPhoneNumber");
            Latitude = bundle.getString("Latitude");
            Longitude = bundle.getString("Longitude");
            name = bundle.getString("name");
            CompanyAddress = bundle.getString("CompanyAddress");

        }

        Intent intent = getIntent();
        mSelectedList = intent.getParcelableArrayListExtra("Response");
         myApplication= new MyApplication();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(detalisOfRequest.this,RequestResponses.class);
                intent.putParcelableArrayListExtra("Response", (ArrayList<? extends Parcelable>)  mSelectedList);
                startActivity(intent);
            }
        });

        price.setText(prices + "EGP");
        name_of_part.setText(name);
        Date.setText("date");
        LOCATION.setText(CompanyAddress);


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneCall(SellerPhoneNumber);
//                 Intent callIntent = new Intent(Intent.ACTION_CALL);
//                 callIntent.setData(Uri.parse("tel:" + SellerPhoneNumber));
//                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                if (ActivityCompat.checkSelfPermission(detalisOfRequest.this, Manifest.permission.CALL_PHONE)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                    return;
//                }
//                startActivity(callIntent);

            }
        });
        Get_Direction=(Button)findViewById(R.id.Get_Direction);
        Get_Direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+Latitude+","+Longitude);
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, gmmIntentUri);
                startActivity(intent);

            }
        });



    }
    private void phoneCall(String tel ){
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + tel));
             startActivity(callIntent);
        }else{
            Toast.makeText(this, getString(R.string.assign_permission),Toast.LENGTH_SHORT).show();

         }
    }
}
