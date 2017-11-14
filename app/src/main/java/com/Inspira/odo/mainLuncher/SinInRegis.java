package com.Inspira.odo.mainLuncher;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.Inspira.odo.R;
import com.Inspira.odo.data.ApiClient;
import com.Inspira.odo.data.ApiInterface;
import com.Inspira.odo.data.Model.BuyerRegistration;
import com.Inspira.odo.database.SharedPreferencesManager;

import okhttp3.ResponseBody;
import retrofit2.Call;

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
        number_phone=(EditText)findViewById(R.id.number_phone);
        passwordlog=(EditText)findViewById(R.id.passwordlog);

    }
    private  void getData (){
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);



    }
}
