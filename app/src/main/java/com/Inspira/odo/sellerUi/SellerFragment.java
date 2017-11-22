package com.Inspira.odo.sellerUi;


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
import com.Inspira.odo.buyerUi.NavigationDrawerBuyer;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.helper.CheckValidation;

import static com.facebook.FacebookSdk.getApplicationContext;


public class SellerFragment extends Fragment {


    Button CreatCampanyAcount ;

    Button creatSelerAcout ;
    EditText fName, phoneNo, password, email ,Confirm_Password;
    SharedPreferencesManager sharedPreferencesManager ;
    CheckValidation checkValidation ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View roooteView = inflater.inflate(R.layout.fragment_seller, container, false);
        sharedPreferencesManager= new SharedPreferencesManager(getApplicationContext());
        checkValidation= new  CheckValidation(getContext());
        if (sharedPreferencesManager.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(getActivity(), NavigationDrawerBuyer.class);
            startActivity(intent);
            getActivity().finish();
        }
        fName =(EditText) roooteView.findViewById(R.id.fname);
        phoneNo = (EditText)roooteView.findViewById(R.id.phone_no);
        password = (EditText)roooteView.findViewById(R.id.password);
        email = (EditText)roooteView.findViewById(R.id.email);
        Confirm_Password=(EditText)roooteView.findViewById(R.id.Confirm_Password);
         CreatCampanyAcount=(Button)roooteView.findViewById(R.id.CreatCampanyAcount);
        CreatCampanyAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                 if(!fName.getText().toString().trim().equals("")&&
                        !phoneNo.getText().toString().trim().equals("")&&
                        !password.getText().toString().trim().equals("")&&
                        !email.getText().toString().trim().equals("")&&
                        !Confirm_Password.getText().toString().trim().equals("")){
                    String name =getDatafromEditText(fName);
                    String  phone = getDatafromEditText(phoneNo);
                    String  passwrd= getDatafromEditText(password);
                    String emaile = getDatafromEditText(email);
                    if(fName.getText().toString().trim()!=null&&!name.isEmpty()&&!phone.isEmpty()&&!passwrd.isEmpty() &&!emaile.isEmpty()){
                        boolean checkedEmail = checkValidation.Emailvalidate(email);
                        boolean checkPassword =checkValidation.ComfierPassord(password ,Confirm_Password);
                        if(checkedEmail==true) {
                            if (checkPassword == true) {
                                Intent intent = new Intent(getActivity(), ContinuingRegSeler.class);
                                intent.putExtra("fName",fName.getText().toString().trim());
                                intent.putExtra("phoneNo",phoneNo.getText().toString().trim());
                                intent.putExtra("password",password.getText().toString().trim());
                                intent.putExtra("email",email.getText().toString().trim());
                                startActivity(intent);

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


        return  roooteView;
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
