package com.Inspira.odo.sellerUi;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.adaptors.CustomArrayAdapter_Spinner;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.helper.LocaleHelper;
import com.Inspira.odo.mainLuncher.RegistrationActivity;
import com.Inspira.odo.ui.DelayAutoCompleteTextView;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
 import static com.Inspira.odo.util.GeoAddress.getLatLong;
import static com.Inspira.odo.util.GeoAddress.getLocationInfo;
import static com.Inspira.odo.util.UserRegistrationPref.USER_COMPANY_ADDRESS;
import static com.Inspira.odo.util.UserRegistrationPref.USER_COMPANY_LAT;
import static com.Inspira.odo.util.UserRegistrationPref.USER_COMPANY_LNG;
import static com.Inspira.odo.util.UserRegistrationPref.USER_COMPANY_NAME;
import static com.Inspira.odo.util.UserRegistrationPref.USER_COMPANY_TYPE;
import static com.Inspira.odo.util.UserRegistrationPref.persist;

public class ContinuingRegSeler extends AppCompatActivity {
    Button Campany_acount_Countio ;
    private Integer THRESHOLD = 2;
    private DelayAutoCompleteTextView geo_autocomplete;
//  private ImageView geo_autocomplete_clear;
    Spinner sellerType ,addArea;
    ArrayList<String> categories ;
    CustomArrayAdapter_Spinner  myAdaptor  ,Adaptor;
    EditText companyName ,company_address;
    TextView Enter_location;
    private static String geoValue;
    private static double lat , lng;
    Bundle bundle;
    String latitude,longitude;
    String text ,itemType ,arraType;
    SharedPreferencesManager  sharedPreferencesManager ;
    String  fName, phoneNo, password, email  ,companyNamey,company_addressy;
    LocaleHelper localeHelper ;
    ImageView go_back ;
 ArrayList<String>AreaArray ;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_continuing_reg_seler);
        bundle=getIntent().getExtras();
        if(bundle!=null){

            latitude=bundle.getString("latitude");
            longitude=bundle.getString("longitude");
            fName=bundle.getString("fName");
            phoneNo= bundle.getString("phoneNo");
            password=bundle.getString("password");
            email=bundle.getString("email");
            companyNamey = bundle.getString("companyName");
            company_addressy = bundle.getString("company_address");



        }
         go_back= (ImageView)findViewById(R.id.go_back);
        localeHelper= new LocaleHelper();
        String lange=  localeHelper.getLanguage(ContinuingRegSeler.this);
        if(lange.equals("ar")){
            go_back.setImageResource(R.drawable.back_right);
        }else if(lange.equals("en")){
            go_back.setImageResource(R.drawable.back);
        }
        Campany_acount_Countio = (Button)findViewById(R.id.Campany_acount_Countio);
        sellerType =(Spinner)findViewById(R.id.spinner_tYpeSeler);
        companyName = (EditText) findViewById(R.id.company_name);
        Enter_location=(TextView) findViewById(R.id.Enter_location);
        addArea=(Spinner)findViewById(R.id.addArea);
        text =getString(R.string.Enter_location);
        sharedPreferencesManager=new SharedPreferencesManager(ContinuingRegSeler.this);
        company_address= (EditText)findViewById(R.id.company_address);
        if(companyNamey!=null&&company_addressy!=null){
            companyName.setText(companyNamey);
            company_address.setText(company_addressy);
            Toast.makeText(getApplicationContext(),companyNamey,Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),company_addressy,Toast.LENGTH_SHORT).show();

        }

//        geo_autocomplete = (DelayAutoCompleteTextView) findViewById(R.id.company_address);

        if(latitude!=null&&longitude!=null){
            text =getString(R.string.done);
            sharedPreferencesManager.setLatitude(latitude);
            sharedPreferencesManager.setLongitude(longitude);
        }



        Enter_location.setText(text);
        Enter_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContinuingRegSeler.this,MapsActivity.class);
                intent.putExtra("companyName",companyName.getText().toString().trim());
                intent.putExtra("company_address",company_address.getText().toString().trim());
                intent.putExtra("your_Type_requse",itemType);
                intent.putExtra("fName",fName);
                intent.putExtra("phoneNo",phoneNo);
                intent.putExtra("password",password);
                intent.putExtra("email",email);
                intent.putExtra("area",arraType);
                startActivity(intent);
                finish();

            }
        });

        Campany_acount_Countio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!companyName.getText().toString().trim().equals("")&&
                  !company_address.getText().toString().trim().equals("")){
                    if(itemType!=null && arraType!=null){
                        Intent intent = new Intent(ContinuingRegSeler.this,WorkingOnOne.class);
                        intent.putExtra("latitude",latitude);
                        intent.putExtra("longitude",longitude);
                        intent.putExtra("companyName",companyName.getText().toString().trim());
                        intent.putExtra("company_address",company_address.getText().toString().trim());
                        intent.putExtra("your_Type_requse",itemType);
                        intent.putExtra("fName",fName);
                        intent.putExtra("phoneNo",phoneNo);
                        intent.putExtra("password",password);
                        intent.putExtra("email",email);
                        intent.putExtra("area",arraType);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(),getString(R.string.enter_data),Toast.LENGTH_SHORT).show();

                    }

                }else {
                    Toast.makeText(getApplicationContext(),getString(R.string.enter_data),Toast.LENGTH_SHORT).show();
                }


                
            }
        });

//        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
//                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
//
//        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(Place place) {
//                // TODO: Get info about the selected place.
//                Log.i("", "Place: " + place.getName());
//            }
//
//            @Override
//            public void onError(Status status) {
//                // TODO: Handle the error.
//                Log.i("", "An error occurred: " + status);
//            }
//        });

        // Spinner Drop down elements
        categories= new ArrayList<>();
        categories.add(getString(R.string.your_Type_requse));
        categories.add("spareParts");
        categories.add("batteries");
        categories.add("tyres");
        categories.add("accessories");
        AreaArray= new ArrayList<>();
        AreaArray.add(getString(R.string.choseArea));
        AreaArray.add(getString(R.string.cairo));
        AreaArray.add(getString(R.string.ma3adi));
        AreaArray.add(getString(R.string.giza));

        Adaptor = new CustomArrayAdapter_Spinner(this,
                R.layout.customspinneritem, AreaArray);

        myAdaptor = new CustomArrayAdapter_Spinner(this,
                R.layout.customspinneritem, categories);

        sellerType.setAdapter(myAdaptor);
        addArea.setAdapter(Adaptor);

        // Spinner click listener
        sellerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                // On selecting a spinner item
                // On selecting a spinner item
                String  itemReques= parent.getItemAtPosition(position).toString();
                if(itemReques.equals(getString(R.string.your_Type_requse))){
                    itemType=null;
                    Toast.makeText(parent.getContext(),getString(R.string.your_Type_requse) , Toast.LENGTH_LONG).show();
                }else {
                    itemType=parent.getItemAtPosition(position).toString() ;
                    sharedPreferencesManager.setPartType(itemType);
                 }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        // Spinner click listener
        addArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                // On selecting a spinner item
                // On selecting a spinner item
                String  itemReques= parent.getItemAtPosition(position).toString();
                if(itemReques.equals(getString(R.string.choseArea))){
                    arraType=null;
                    Toast.makeText(parent.getContext(),getString(R.string.choseArea) , Toast.LENGTH_LONG).show();
                }else {
                    arraType=parent.getItemAtPosition(position).toString() ;
                    sharedPreferencesManager.setArea(arraType);
                    Toast.makeText(parent.getContext(), arraType, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void doContinue(){
        persist(ContinuingRegSeler.this, USER_COMPANY_NAME, companyName.getText().toString());
        persist(ContinuingRegSeler.this, USER_COMPANY_ADDRESS, geo_autocomplete.getText().toString());
        persist(ContinuingRegSeler.this, USER_COMPANY_TYPE, sellerType.getSelectedItem().toString());
        persist(ContinuingRegSeler.this, USER_COMPANY_LAT, String.valueOf(lat));
        persist(ContinuingRegSeler.this, USER_COMPANY_LNG, String.valueOf(lng));
        Intent intent = new Intent(ContinuingRegSeler.this, WorkingOnOne.class);
        startActivity(intent);
    }

//    private class GetAddress extends AsyncTask<String, Void, String> {
//
//
//        private LatLng latLng;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//            try{
//                latLng = getLatLong(getLocationInfo(geoValue));
//
//                return null;
//            }
//            catch (Exception e){
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            lat = latLng.latitude;
//            lng = latLng.longitude;
//            doContinue();
//        }
//    }

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(ContinuingRegSeler.this , RegistrationActivity.class);
        startActivity(setIntent);
    }
}
