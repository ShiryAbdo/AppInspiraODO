package com.Inspira.odo.buyerUi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.data.ApiClient;
import com.Inspira.odo.data.ApiInterface;
import com.Inspira.odo.data.Model.BuyerRegistration;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.helper.CheckValidation;
import com.Inspira.odo.mainLuncher.ContinueRigeWithFacebook;
import com.facebook.FacebookSdk;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.facebook.FacebookSdk.getApplicationContext;

public class BuyerWithFacebook extends Fragment {
    EditText Mobile_Number ,passworred ,Confirm_Password;
    Button Continue ;
    Bundle bundle ;
    String user_name  ,email;
    SharedPreferencesManager sharedPreferencesManager ;
    CheckValidation checkValidation ;
    View roote;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        roote = inflater.inflate(R.layout.fragment_buyer_with_facebook, container, false);
        user_name = getArguments().getString("user_name");
        email=  getArguments().getString("email");
        FacebookSdk.sdkInitialize(getApplicationContext());
        sharedPreferencesManager= new SharedPreferencesManager(getActivity());
        checkValidation= new CheckValidation(getApplicationContext());
        Mobile_Number=(EditText)roote.findViewById(R.id.Mobile_Number);
        passworred=(EditText)roote.findViewById(R.id.passworred);
        Continue=(Button)roote.findViewById(R.id.Continue);
        Confirm_Password=(EditText)roote.findViewById(R.id.Confirm_Password);
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),email,Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),user_name,Toast.LENGTH_SHORT).show();
                if(!Mobile_Number.getText().toString().trim().equals("") &&!passworred.getText().toString().trim().equals("")&&email!=null&&user_name!=null){
                    boolean checkPassword =checkValidation.ComfierPassord(passworred ,Confirm_Password);
                    if(checkPassword==true){
                        String   phone = Mobile_Number.getText().toString().trim();
                        String pass =passworred.getText().toString().trim();
                        if(email!=null){
                            getData(phone,user_name,email,pass,"","");
//                        getData(Mobile_Number.getText().toString().trim(),user_name,email,passworred.getText().toString().trim(),"","");

                        }


                    }else {
                        Toast.makeText(getApplicationContext(),getString(R.string.Password_Not_matching),Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(getApplicationContext(),getString(R.string.enter_data),Toast.LENGTH_SHORT).show();
                }
            }
        });
        return  roote ;
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
                    Intent intent = new Intent(getActivity(), NavigationDrawerBuyer.class);
                    startActivity(intent);
                    getActivity().finish();
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
