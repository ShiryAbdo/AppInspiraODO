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

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;



public class BuperFragment extends Fragment {


    Button creatSelerAcout ;
    EditText fName, phoneNo, password, email;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View rooteView= inflater.inflate(R.layout.fragment_buper, container, false);
        creatSelerAcout =(Button)rooteView.findViewById(R.id.creatSelerAcout);
        fName =(EditText) rooteView.findViewById(R.id.fname);
        phoneNo = (EditText)rooteView.findViewById(R.id.phone_no);
        password = (EditText)rooteView.findViewById(R.id.password);
        email = (EditText)rooteView.findViewById(R.id.email);
        creatSelerAcout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), NavigationDrawerBuyer.class);
                startActivity(intent);
                if(!fName.getText().toString().trim().equals("")&&
                        !phoneNo.getText().toString().trim().equals("")&&
                        !password.getText().toString().trim().equals("")&&
                        !email.getText().toString().trim().equals("")){
                    String name =getDatafromEditText(fName);
                    String  phone = getDatafromEditText(phoneNo);
                    String  passwrd= getDatafromEditText(password);
                    String emaile = getDatafromEditText(email);
                    if(fName.getText().toString().trim()!=null&&!name.isEmpty()&&!phone.isEmpty()&&!passwrd.isEmpty() &&!emaile.isEmpty()){
                        getData();

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

        Call<ResponseBody> call = apiService.doBuyerRegister(new BuyerRegistration(fName.getText().toString(),phoneNo.getText().toString(),password.getText().toString(),email.getText().toString(),"1bu4i3iug262bi6u22j2ij3bug5ug45i","buyer"));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody>call, Response<ResponseBody> response) {
                int responseCode = response.code();
                Log.d("CODE", "ResponseCode: " + responseCode);
                if(responseCode==200){
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

                editText.setError("This can't contain a space ");
            }else{
                return text;}

        }
        return text;

    }

}
