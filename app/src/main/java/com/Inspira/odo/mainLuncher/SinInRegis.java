package com.Inspira.odo.mainLuncher;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.buyerUi.NavigationDrawerBuyer;
import com.Inspira.odo.data.ApiClient;
import com.Inspira.odo.data.ApiInterface;
import com.Inspira.odo.data.Model.BuyerRegistration;
import com.Inspira.odo.data.Model.Login;
import com.Inspira.odo.data.Model.LoginData;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.helper.LocaleHelper;
import com.Inspira.odo.sellerUi.NavigationDrawerSeler;

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
    Button login ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sin_in_regis);
        login= (Button)findViewById(R.id.login);
        sharedPreferencesManager = new SharedPreferencesManager(this);
        number_phone=(EditText)findViewById(R.id.number_phone);
        passwordlog=(EditText)findViewById(R.id.passwordlog);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = number_phone.getText().toString().trim() ;
                String passord =passwordlog.getText().toString().trim() ;

                if(!number_phone.getText().toString().trim().equals("")&&number!=null&&
                !passwordlog.getText().toString().trim().equals("")&&passord!=null){
                    checkUser();
                }else {
                    Toast.makeText(getApplicationContext(),getString(R.string.enter_data),Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
    private  void checkUser (){

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<LoginData> call = apiService.getLogin(new Login(number_phone.getText().toString(),passwordlog.getText().toString(),"1bu4i3iug262bi6u22j2ij3bug5ug45i"));
        call.enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(Call<LoginData>call, Response<LoginData> response) {
                int responseCode = response.code();
                Log.d("CODE", "ResponseCode: " + responseCode);
                if(responseCode==200){

                    sharedPreferencesManager.setLogin(true);
                    LoginData loginData = response.body() ;
                     String user = loginData.getUserType();
                    String token =loginData.getToken();
                    sharedPreferencesManager.setUserType(user);
                    sharedPreferencesManager.setToken(token);
                    sharedPreferencesManager.setUser_Phoe(number_phone.getText().toString().trim());
                    if(user.equals("buyer")){

                        Intent intent = new Intent(SinInRegis.this, NavigationDrawerBuyer.class);
                        startActivity(intent);
                        finish();

                    }else if (user.equals("seller")){
                        Intent intent = new Intent(SinInRegis.this, NavigationDrawerSeler.class);
                        startActivity(intent);
                        finish();

                    }
                    Toast.makeText(getApplicationContext(),user,Toast.LENGTH_SHORT).show();


                    Toast.makeText(getApplicationContext(),"ResponseCode: " + responseCode,Toast.LENGTH_SHORT).show();


                }else {

                    Toast.makeText(getApplicationContext(),getString(R.string.enter_data),Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<LoginData>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });



    }
}
