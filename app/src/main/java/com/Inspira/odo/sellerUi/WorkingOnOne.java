package com.Inspira.odo.sellerUi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.Inspira.odo.R;
import com.Inspira.odo.data.ApiClient;
import com.Inspira.odo.data.ApiInterface;
import com.Inspira.odo.data.Model.CompanyOnMap;
import com.Inspira.odo.data.Model.SellerRegistration;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.Inspira.odo.util.UserRegistrationPref.USER_COMPANY_ADDRESS;
import static com.Inspira.odo.util.UserRegistrationPref.USER_COMPANY_LAT;
import static com.Inspira.odo.util.UserRegistrationPref.USER_COMPANY_LNG;
import static com.Inspira.odo.util.UserRegistrationPref.USER_COMPANY_NAME;
import static com.Inspira.odo.util.UserRegistrationPref.USER_COMPANY_TYPE;
import static com.Inspira.odo.util.UserRegistrationPref.USER_EMAIL;
import static com.Inspira.odo.util.UserRegistrationPref.USER_FUll_NAME;
import static com.Inspira.odo.util.UserRegistrationPref.USER_HASH_VALUE;
import static com.Inspira.odo.util.UserRegistrationPref.USER_PASSWORD;
import static com.Inspira.odo.util.UserRegistrationPref.USER_PHONE_NO;
import static com.Inspira.odo.util.UserRegistrationPref.getPersistedData;

public class WorkingOnOne extends AppCompatActivity {
    ArrayList<String> categories;
    Spinner SpinnerOne ,SpinnerTwo ,SpinnerThree  ,SpinnerCarFoure;
    ImageView competRegister ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_on_one);


        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        setTitle(Html.fromHtml("<small>Working on</small>"));
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        SpinnerOne = (Spinner)findViewById(R.id.SpinnerOne);
        SpinnerTwo=(Spinner)findViewById(R.id.SpinnerTwo);
        SpinnerThree =(Spinner)findViewById(R.id.SpinnerThree);
        SpinnerCarFoure =(Spinner)findViewById(R.id.SpinnerCarFoure);
        competRegister=(ImageView)findViewById(R.id.competRegister);
        competRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fName = getPersistedData(WorkingOnOne.this,USER_FUll_NAME);
                String phoneNo = getPersistedData(WorkingOnOne.this,USER_PHONE_NO);;
                String password = getPersistedData(WorkingOnOne.this,USER_PASSWORD);;
                String hashVal = getPersistedData(WorkingOnOne.this,USER_HASH_VALUE);;
                String email = getPersistedData(WorkingOnOne.this,USER_EMAIL);;
                String companyName = getPersistedData(WorkingOnOne.this,USER_COMPANY_NAME);;
                String companyAddress = getPersistedData(WorkingOnOne.this,USER_COMPANY_ADDRESS);;
                String companyType = getPersistedData(WorkingOnOne.this,USER_COMPANY_TYPE);;
                String companyLat = getPersistedData(WorkingOnOne.this,USER_COMPANY_LAT);;
                String companyLng = getPersistedData(WorkingOnOne.this,USER_COMPANY_LNG);;

                CompanyOnMap companyOnMap = new CompanyOnMap();
                companyOnMap.setLatitude(companyLat);
                companyOnMap.setLongitude(companyLng);

                ApiInterface apiService =
                        ApiClient.getClient().create(ApiInterface.class);

//                // ToDo Just Add WorkingOn List and it'll work  ,, IMPORTANT
//                Call<ResponseBody> call = apiService.doSellerRegister(new SellerRegistration(phoneNo,fName, email,password,hashVal, companyName, companyAddress, companyOnMap,companyType));
//                call.enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        int responseCode = response.code();
//                        Log.d("CODE", "ResponseCode: " + responseCode);
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        // Log error here since request failed
//                        Log.e(TAG, t.toString());
//                    }
//                });
                Intent intent = new Intent(WorkingOnOne.this,NavigationDrawerSeler.class);
                startActivity(intent);
                finish();

                    }
                });


        // Spinner Drop down elements
        categories= new ArrayList<>();
        categories.add("Automobile");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerOne.setAdapter(dataAdapter);
        SpinnerTwo.setAdapter(dataAdapter);
        SpinnerThree.setAdapter(dataAdapter);
        SpinnerCarFoure.setAdapter(dataAdapter);



    }
}
