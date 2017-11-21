package com.Inspira.odo.helper;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.Inspira.odo.R;

import java.util.regex.Pattern;

import static com.facebook.FacebookSdk.getApplicationContext;


public class CheckValidation {
      Context context;

    public CheckValidation(Context context) {
        this.context = context;
    }

    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9+._%-+]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                    "(" +
                    "." +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                    ")+"
    );
    public boolean Emailvalidate(EditText editText){
        boolean temp=false;
        String checkemail = editText.getText().toString();
        if(!EMAIL_ADDRESS_PATTERN.matcher(checkemail).matches()){
//            "Invalid Email Address"

            temp=false;
        }else {
             temp=true;

        }
        return temp;
    }
    public boolean  ComfierPassord( EditText pw ,EditText cpw ){
        boolean temp=false;
        String pass=pw.getText().toString();
        String cpass=cpw.getText().toString();
        if(!pass.equals(cpass)){
//            "Password Not matching"
            temp=false;
        } else {
            temp=true;
        }
        return temp;
    }
    private String getDatafromEditText(EditText editText){
        String text="";
        if(TextUtils.isEmpty(editText.getText().toString())){
            editText.setError(context.getString(R.string.enter_data));
        }else {
            text=editText.getText().toString();
            if(text.contains("")){


                editText.setError(context.getString(R.string.cant_contain_a_space));
            }else{
                return text;}

        }
        return text;

    }

}
