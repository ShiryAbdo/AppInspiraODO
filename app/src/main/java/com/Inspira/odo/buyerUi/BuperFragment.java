package com.Inspira.odo.buyerUi;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.Inspira.odo.R;
import com.Inspira.odo.data.ApiClient;
import com.Inspira.odo.data.ApiInterface;
import com.Inspira.odo.data.Model.BuyerRegistration;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class BuperFragment extends Fragment {


     Button creatSelerAcout ;
    EditText fName, phoneNo, password, email;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rooteView= inflater.inflate(R.layout.fragment_buper, container, false);
        creatSelerAcout =(Button)rooteView.findViewById(R.id.creatSelerAcout);
        fName = rooteView.findViewById(R.id.fname);
        phoneNo = rooteView.findViewById(R.id.phone_no);
        password = rooteView.findViewById(R.id.password);
        email = rooteView.findViewById(R.id.email);
        creatSelerAcout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ApiInterface apiService =
                        ApiClient.getClient().create(ApiInterface.class);

                Call<ResponseBody> call = apiService.doBuyerRegister(new BuyerRegistration(fName.getText().toString(),phoneNo.getText().toString(),password.getText().toString(),email.getText().toString()));
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody>call, Response<ResponseBody> response) {
                        int responseCode = response.code();
                        Log.d("CODE", "ResponseCode: " + responseCode);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody>call, Throwable t) {
                        // Log error here since request failed
                        Log.e(TAG, t.toString());
                    }
                });

                Intent intent = new Intent(getActivity(), NavigationDrawerBuyer.class);
                startActivity(intent);


            }
        });
        return rooteView;
    }

}
