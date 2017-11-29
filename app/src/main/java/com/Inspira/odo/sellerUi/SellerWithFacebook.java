package com.Inspira.odo.sellerUi;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.helper.CheckValidation;
import com.facebook.FacebookSdk;

import static com.facebook.FacebookSdk.getApplicationContext;


public class SellerWithFacebook extends Fragment {
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
        roote = inflater.inflate(R.layout.fragment_seller_with_facebook, container, false);
        FacebookSdk.sdkInitialize(getApplicationContext());
        user_name = getArguments().getString("user_name");
        email=  getArguments().getString("email");
        sharedPreferencesManager= new SharedPreferencesManager(getActivity());
        checkValidation= new CheckValidation(getApplicationContext());
        Mobile_Number=(EditText)roote.findViewById(R.id.Mobile_Number);
        passworred=(EditText)roote.findViewById(R.id.passworred);
        Continue=(Button)roote.findViewById(R.id.Continue);
        Confirm_Password=(EditText)roote.findViewById(R.id.Confirm_Password);


        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(user_name!=null&&
                        !Mobile_Number.getText().toString().trim().equals("")&&
                        email!=null&&
                        !Confirm_Password.getText().toString().trim().equals("")){

                    String  phone = getDatafromEditText(Mobile_Number);
                    String  Confirm_Pasword= getDatafromEditText(Confirm_Password);
                    String  passwrd= getDatafromEditText(passworred);
                     if(user_name!=null&&!Confirm_Pasword.isEmpty()&&!phone.isEmpty()&&!passwrd.isEmpty() ){
                         boolean checkPassword =checkValidation.ComfierPassord(passworred ,Confirm_Password);

                            if (checkPassword == true) {
                                Intent intent = new Intent(getActivity(), ContinuingRegSeler.class);
                                intent.putExtra("fName",user_name);
                                intent.putExtra("phoneNo",Mobile_Number.getText().toString().trim());
                                intent.putExtra("password",passworred.getText().toString().trim());
                                intent.putExtra("email",email);
                                startActivity(intent);

                            }else {
                                Toast.makeText(getApplicationContext(),getString(R.string.Password_Not_matching),Toast.LENGTH_SHORT).show();

                            }


                    }else {
                        Toast.makeText(getContext(),getString(R.string.enter_data),Toast.LENGTH_SHORT).show();
                    }
                }else {

                    Toast.makeText(getContext(),getString(R.string.enter_data),Toast.LENGTH_SHORT).show();
                }


            }
        });
        return  roote ;
    }

    private String getDatafromEditText(EditText editText){
        String text="";
        if(TextUtils.isEmpty(editText.getText().toString())){
            editText.setError(getString(R.string.enter_data));
        }else {
            text=editText.getText().toString();
            if(text.contains("")){

                editText.setError("This can't contain a space ");
            }else{
                return text;}

        }
        return text;

    }

}
