package com.Inspira.odo.buyerUi;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.data.ApiClient;
import com.Inspira.odo.data.ApiInterface;
import com.Inspira.odo.data.Model.MakOrder;
import com.Inspira.odo.data.Model.OrderImage;
import com.Inspira.odo.data.Model.OrderList;
import com.Inspira.odo.database.SharedPreferencesManager;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.facebook.FacebookSdk.getApplicationContext;


public class MakTyreRequest extends Fragment {
    EditText Size ;
    CheckBox  Run_flot_tyres ;
    RadioButton one_Tyre  ,two_tyre ,four_tyre ;
    Button saveData;
    SharedPreferencesManager sharedPreferencesManager ;
    String carType ,carYear ,carePar ,carModle ,PHONE_number ,Run_flot_tyrest ,TyrT;
    List<OrderList> orderList ;
    List<OrderImage> orderImages ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View rooteview = inflater.inflate(R.layout.fragment_mak_tyre_request, container, false);
        getActivity().setTitle(R.string.MakTyreRequest);
        sharedPreferencesManager= new SharedPreferencesManager(getActivity());
        carModle =sharedPreferencesManager.getCar_Modle();
        carYear =sharedPreferencesManager.getCar_Year() ;
        carType =sharedPreferencesManager.getCar_Type();
        PHONE_number= sharedPreferencesManager.getUser_Phoe();
        carePar="Tyres";
        Size= (EditText)rooteview.findViewById(R.id.Size);
        Run_flot_tyres=(CheckBox)rooteview.findViewById(R.id.Run_flot_tyres);
        one_Tyre=(RadioButton)rooteview.findViewById(R.id.one_Tyre);
        two_tyre=(RadioButton)rooteview.findViewById(R.id.two_tyre);
        four_tyre=(RadioButton)rooteview.findViewById(R.id.four_tyre);
        saveData=(Button)rooteview.findViewById(R.id.saveData);
        if( Run_flot_tyres.isChecked()){
            Run_flot_tyrest=getString(R.string.Run_flot_tyres);
        }
        one_Tyre.setTextColor(Color.WHITE);
        two_tyre.setTextColor(Color.WHITE);
        four_tyre.setTextColor(Color.WHITE);
        one_Tyre.setText(R.string.one_Tyre);
        two_tyre.setText(R.string.two_tyre);
        four_tyre.setText(R.string.four_tyre);


        if(one_Tyre.isChecked()){
            TyrT=getString(R.string.one_Tyre);
            two_tyre.setChecked(false);
            four_tyre.setChecked(false);
        }else if(two_tyre.isChecked()){
            TyrT=getString(R.string.two_tyre);
            one_Tyre.setChecked(false);
            four_tyre.setChecked(false);
        }else if(four_tyre.isChecked()){
            TyrT=getString(R.string.four_tyre);
            one_Tyre.setChecked(false);
            two_tyre.setChecked(false);
        }else {
            Toast.makeText(getApplicationContext(),getString(R.string.Select_the_frame_type ),Toast.LENGTH_LONG).show();

        }

        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!Size.getText().toString().trim().equals("")) {
                    orderList.add(new OrderList(carePar, TyrT, "-", "-", "-", Size.getText().toString().trim()));
                    if (PHONE_number != null) {
                        orderImages.add(new OrderImage("image"));
                        sendOrder(PHONE_number, carType, carModle, carYear, orderList, orderImages);

                    } else {
                        Toast.makeText(getApplicationContext(),getString(R.string.Phone_number_does_not_exist),Toast.LENGTH_SHORT).show();

                    }
                } else {


                }



            }
        });

        return  rooteview;
    }


    public  void sendOrder (String phone ,String carType ,String carModel ,String   carYear ,  List<OrderList> orderList ,List<OrderImage> orderImages ){


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiService.addOrders(new MakOrder(phone ,carType,carModel,carYear,  orderList ,orderImages));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody>call, Response<ResponseBody> response) {
                int responseCode = response.code();
                Toast.makeText(getApplicationContext(),"ResponseCode: " + responseCode,Toast.LENGTH_LONG).show();
                Log.d("CODE", "ResponseCode: " + responseCode);
            }

            @Override
            public void onFailure(Call<ResponseBody>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

    }

}
