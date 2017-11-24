package com.Inspira.odo.buyerUi;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.Inspira.odo.R;

public class detalisOfRequest extends AppCompatActivity {
    TextView price, name_of_part, Date, LOCATION;
    FloatingActionButton fab;
    Button Get_Direction;
    Bundle bundle;
    String prices, SellerPhoneNumber, Latitude, Longitude, name, CompanyAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalis_of_request);
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

        price.setText(prices);
        name_of_part.setText(name);
        Date.setText("date");
        LOCATION.setText(CompanyAddress);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String phone_no = tv_phone.getText().toString().replaceAll("-", "");
                Intent callIntent = new Intent(Intent.ACTION_CALL);
//                Uri.parse("tel:" + Constants.CALL_CENTER_NUMBER)
                callIntent.setData(Uri.parse("tel:" + SellerPhoneNumber));
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (ActivityCompat.checkSelfPermission(detalisOfRequest.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
        Get_Direction=(Button)findViewById(R.id.Get_Direction);
        Get_Direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(detalisOfRequest.this,MapsActivity.class);
                startActivity(intent);
            }
        });



    }
    private void phoneCall(){
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:12345678900"));
             startActivity(callIntent);
        }else{
            Toast.makeText(this, "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }
    }
}
