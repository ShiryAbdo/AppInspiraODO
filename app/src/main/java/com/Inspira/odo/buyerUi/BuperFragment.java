package com.Inspira.odo.buyerUi;


  import android.content.Intent;
  import android.os.Bundle;
import android.support.v4.app.Fragment;
 import android.text.TextUtils;
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
  import com.Inspira.odo.mainLuncher.SinInRegis;

  import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
  import static com.facebook.FacebookSdk.getApplicationContext;


public class BuperFragment extends Fragment {


    Button creatSelerAcout ;
    EditText fName, phoneNo, password, email ,Confirm_Password;
    SharedPreferencesManager sharedPreferencesManager ;
    CheckValidation checkValidation ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View rooteView= inflater.inflate(R.layout.fragment_buper, container, false);
        sharedPreferencesManager= new SharedPreferencesManager(getApplicationContext());
        checkValidation= new CheckValidation(getContext());
        if (sharedPreferencesManager.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(getActivity(), NavigationDrawerBuyer.class);
            startActivity(intent);
            getActivity().finish();
        }
        creatSelerAcout =(Button)rooteView.findViewById(R.id.creatSelerAcout);
        fName =(EditText) rooteView.findViewById(R.id.fname);
        phoneNo = (EditText)rooteView.findViewById(R.id.phone_no);
        password = (EditText)rooteView.findViewById(R.id.password);
        email = (EditText)rooteView.findViewById(R.id.email);
        Confirm_Password =(EditText)rooteView.findViewById(R.id.Confirm_Password);
        creatSelerAcout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!fName.getText().toString().trim().equals("")&&
                        !phoneNo.getText().toString().trim().equals("")&&
                        !password.getText().toString().trim().equals("")&&
                        !email.getText().toString().trim().equals("")&&
                        !Confirm_Password.getText().toString().equals("")){
                    String name =getDatafromEditText(fName);
                    String  phone = getDatafromEditText(phoneNo);
                    String  passwrd= getDatafromEditText(password);
                    String emaile = getDatafromEditText(email);
                     if(fName.getText().toString().trim()!=null&&!name.isEmpty()&&!phone.isEmpty()&&!passwrd.isEmpty() &&!emaile.isEmpty()){
                     boolean checkedEmail = checkValidation.Emailvalidate(email);
                        boolean checkPassword =checkValidation.ComfierPassord(password ,Confirm_Password);
                        if(checkedEmail==true){
                            if(checkPassword==true){
                                getData();
                            }else {
                                Toast.makeText(getApplicationContext(),getString(R.string.Password_Not_matching),Toast.LENGTH_SHORT).show();

                            }

                        }else {
                            Toast.makeText(getApplicationContext(),getString(R.string.Invalid_Not_Email),Toast.LENGTH_SHORT).show();

                        }


                    }else {
                        Toast.makeText(getContext(),getString(R.string.enter_data),Toast.LENGTH_SHORT).show();
                    }
                }else {

                    Toast.makeText(getContext(),getString(R.string.enter_data),Toast.LENGTH_SHORT).show();
                }




            }
        });
        return rooteView;
    }

    private  void getData (){

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiService.doBuyerRegister(new BuyerRegistration(phoneNo.getText().toString(),fName.getText().toString(),email.getText().toString(),password.getText().toString(),"1bu4i3iug262bi6u22j2ij3bug5ug45i","buyer"));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody>call, Response<ResponseBody> response) {
                int responseCode = response.code();
                Log.d("CODE", "ResponseCode: " + responseCode);
                if(responseCode==200){
                    sharedPreferencesManager.setLogin(true);
                    sharedPreferencesManager.setUser_Name(fName.getText().toString());
                    sharedPreferencesManager.setUser_Phoe(phoneNo.getText().toString());
                    sharedPreferencesManager.setUserType("buyer");

                    Toast.makeText(getContext(),"ResponseCode: " + responseCode,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), NavigationDrawerBuyer.class);
                    startActivity(intent);
                   getActivity().finish();
                }else {

                    Toast.makeText(getContext(),"ResponseCode: " + responseCode,Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext()," else",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

    }


    private String getDatafromEditText(EditText editText){
        String text="";
        if(TextUtils.isEmpty(editText.getText().toString())){
            editText.setError(getString(R.string.enter_data));
        }else {
            text=editText.getText().toString();
            if(text.contains("")){


                editText.setError(getString(R.string.enter_data));
            }else{
                return text;}

        }
        return text;

    }

}
