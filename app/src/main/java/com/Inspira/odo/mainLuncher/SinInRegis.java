package com.Inspira.odo.mainLuncher;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.buyerUi.NavigationDrawerBuyer;
import com.Inspira.odo.data.ApiClient;
import com.Inspira.odo.data.ApiInterface;
import com.Inspira.odo.data.Model.BuyerRegistration;
import com.Inspira.odo.database.SharedPreferencesManager;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class SinInRegis extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }
    EditText number_phone  ,passwordlog;
    SharedPreferencesManager sharedPreferencesManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sin_in_regis);
        sharedPreferencesManager = new SharedPreferencesManager(this);
        // Check if user is already logged in or not
        if (sharedPreferencesManager.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(SinInRegis.this, NavigationDrawerBuyer.class);
            startActivity(intent);
            finish();
        }
        number_phone=(EditText)findViewById(R.id.number_phone);
        passwordlog=(EditText)findViewById(R.id.passwordlog);

    }
    private  void checkUser (){
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiService.doBuyerRegister(new BuyerRegistration("",number_phone.getText().toString(),passwordlog.getText().toString(),"","1bu4i3iug262bi6u22j2ij3bug5ug45i","buyer"));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody>call, Response<ResponseBody> response) {
                int responseCode = response.code();
                Log.d("CODE", "ResponseCode: " + responseCode);
                if(responseCode==200){
                    sharedPreferencesManager.setLogin(true);
                    Toast.makeText(getApplicationContext(),"ResponseCode: " + responseCode,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SinInRegis.this, NavigationDrawerBuyer.class);
                    startActivity(intent);
                    finish();
                }else {

                    Toast.makeText(getApplicationContext(),"ResponseCode: " + responseCode,Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext()," else",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });



    }
}
