package com.Inspira.odo.sellerUi;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.Inspira.odo.R;
import com.Inspira.odo.adaptors.CustomArrayAdapter_Spinner;
import com.Inspira.odo.adaptors.GeoAutoCompleteAdapter;
import com.Inspira.odo.mainLuncher.LocaleHelper;
import com.Inspira.odo.model.GeoSearchResult;
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
//    private ImageView geo_autocomplete_clear;
    Spinner sellerType;
    ArrayList<String> categories ;
    CustomArrayAdapter_Spinner  myAdaptor ;
    EditText companyName;
    private static String geoValue;
    private static double lat , lng;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_continuing_reg_seler);
        Campany_acount_Countio = (Button)findViewById(R.id.Campany_acount_Countio);
        sellerType =(Spinner)findViewById(R.id.spinner_tYpeSeler);
        companyName = (EditText) findViewById(R.id.company_name);
        geo_autocomplete = (DelayAutoCompleteTextView) findViewById(R.id.company_address);

        Campany_acount_Countio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean value = true;
                if (companyName.getText().toString().isEmpty()){
                    value = false;
//                    companyName.setError(getString(R.string.required));
                    companyName.requestFocus();
                }else if (geo_autocomplete.getText().toString().isEmpty()) {
                    value = false;
//                    companyAddress.setError(getString(R.string.required));
                    geo_autocomplete.requestFocus();
                }
                if (value) {
                    geoValue = geo_autocomplete.getText().toString();
                    new GetAddress().execute();
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
        categories.add("Spare Parts");
        categories.add("Battery");
        categories.add("Tyres");
        categories.add("Accessories");

        myAdaptor = new CustomArrayAdapter_Spinner(this,
                R.layout.customspinneritem, categories);

        sellerType.setAdapter(myAdaptor);


//        geo_autocomplete_clear = (ImageView) findViewById(R.id.geo_autocomplete_clear);

        geo_autocomplete.setThreshold(2);
         geo_autocomplete.setAdapter(new GeoAutoCompleteAdapter(this)); // 'this' is Activity instance

        geo_autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                GeoSearchResult result = (GeoSearchResult) adapterView.getItemAtPosition(position);
                geo_autocomplete.setText(result.getAddress());
            }
        });

        geo_autocomplete.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 0)
                {
//                    geo_autocomplete_clear.setVisibility(View.VISIBLE);
                }
                else
                {
//                    geo_autocomplete_clear.setVisibility(View.GONE);
                }
            }
        });

//        geo_autocomplete_clear.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                geo_autocomplete.setText("");
//            }
//        });

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

    private class GetAddress extends AsyncTask<String, Void, String> {


        private LatLng latLng;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings) {
            try{
                latLng = getLatLong(getLocationInfo(geoValue));

                return null;
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            lat = latLng.latitude;
            lng = latLng.longitude;
            doContinue();
        }
    }
}
