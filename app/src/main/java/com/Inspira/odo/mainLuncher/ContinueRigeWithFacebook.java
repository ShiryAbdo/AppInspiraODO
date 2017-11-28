package com.Inspira.odo.mainLuncher;

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
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.helper.CheckValidation;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.facebook.FacebookSdk.getApplicationContext;

public class ContinueRigeWithFacebook extends AppCompatActivity {
    EditText Mobile_Number ,passworred ,Confirm_Password;
    Button Continue ;
    Bundle bundle ;
    String user_name  ,email;
    SharedPreferencesManager sharedPreferencesManager ;
    CheckValidation checkValidation ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        sharedPreferencesManager= new SharedPreferencesManager(ContinueRigeWithFacebook.this);
        checkValidation= new CheckValidation(this);
                bundle=getIntent().getExtras();
        if(bundle!=null){
            user_name = bundle.getString("user_name");
            email= bundle.getString("email");
        }
        setContentView(R.layout.activity_continue_rige_with_facebook);
        Mobile_Number=(EditText)findViewById(R.id.Mobile_Number);
        passworred=(EditText)findViewById(R.id.passworred);
        Continue=(Button)findViewById(R.id.Continue);
        Confirm_Password=(EditText)findViewById(R.id.Confirm_Password);
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                LoginManager.getInstance().logOut();
//                Intent intent = new Intent(ContinueRigeWithFacebook.this,LogInActivity.class);
//                startActivity(intent);
                Toast.makeText(getApplicationContext(),email,Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),user_name,Toast.LENGTH_SHORT).show();
                if(user_name!=null&&!Mobile_Number.getText().toString().trim().equals("")&&email!=null &&passworred.getText().toString().trim().equals("")){
                    boolean checkPassword =checkValidation.ComfierPassord(passworred ,Confirm_Password);
                    if(checkPassword==true){
                        getData(Mobile_Number.getText().toString().trim(),user_name,email,passworred.getText().toString().trim(),"","");

                    }else {
                        Toast.makeText(getApplicationContext(),getString(R.string.Password_Not_matching),Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(getApplicationContext(),getString(R.string.enter_data),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private  void getData(final String phoneNumber, final String fullName, String email, String password , String hashVal ,
                          String  userType ){

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiService.doBuyerRegister(new BuyerRegistration(phoneNumber,
                fullName,email,password,"1bu4i3iug262bi6u22j2ij3bug5ug45i","buyer"));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody>call, Response<ResponseBody> response) {
                int responseCode = response.code();
                Log.d("CODE", "ResponseCode: " + responseCode);
                if(responseCode==200){
                    sharedPreferencesManager.setLogin(true);
                    sharedPreferencesManager.setUser_Name(fullName);
                    sharedPreferencesManager.setUser_Phoe(phoneNumber);
                    sharedPreferencesManager.setUserType("buyer");
                    sharedPreferencesManager.setCheckFacebookLogin(true);

                    Toast.makeText(getApplicationContext(),"ResponseCode: " + responseCode,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ContinueRigeWithFacebook.this, NavigationDrawerBuyer.class);
                    startActivity(intent);
                    finish();
                }else {

                    Toast.makeText(getApplicationContext(),"ResponseCode: " + responseCode,Toast.LENGTH_SHORT).show();
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
