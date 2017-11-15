package com.Inspira.odo.buyerUi;


import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.data.ApiClient;
import com.Inspira.odo.data.ApiInterface;
import com.Inspira.odo.data.Model.Order;
import com.Inspira.odo.data.Model.OrderImage;
import com.Inspira.odo.data.Model.OrderList;
import com.Inspira.odo.database.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.facebook.FacebookSdk.getApplicationContext;

public class AccesoriesRequests extends Fragment {
    EditText PartId ;
    TextView add_anther_part_detalis ;
    Button submet_requst ;
    List<OrderList> orderList ;
    List<OrderImage> orderImages ;
    ImageView addImage ;
    SharedPreferencesManager sharedPreferencesManager ;
    String carType ,carYear ,carePar ,carModle ,PHONE_number;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View rooteViw =   inflater.inflate(R.layout.fragment_accesories_requests, container, false);
        sharedPreferencesManager= new SharedPreferencesManager(getActivity());
        carModle =sharedPreferencesManager.getCar_Modle();
        carYear =sharedPreferencesManager.getCar_Year() ;
        carType =sharedPreferencesManager.getCar_Type();
        PHONE_number= sharedPreferencesManager.getUser_Phoe();
        carePar="Accessories";
        getActivity().setTitle(R.string.AccesoriesRequests);
        PartId=(EditText)rooteViw.findViewById(R.id.PartId);
        addImage= (ImageView)rooteViw.findViewById(R.id.addImage);
        orderList= new ArrayList<>();
        orderImages = new ArrayList<>();
        if(PartId.getText().toString().trim()!=null){
            orderList.add(new OrderList(carePar,PartId.getText().toString().trim(),"","","",""));
        }else{
            Toast.makeText(getApplicationContext(),"add Accessories detalies",Toast.LENGTH_LONG).show();

        }

        orderImages.add(new OrderImage("photo.jpg"));
        add_anther_part_detalis=(TextView)rooteViw.findViewById(R.id.add_anther_part_detalis);
        submet_requst= (Button)rooteViw.findViewById(R.id.submet_requst);
        submet_requst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(PartId.getText().toString().trim()!=null){
                        if(PHONE_number!=null){
                            sendOrder(PHONE_number,carType,carModle ,carYear,orderList ,orderImages);

                        }else {
                            Toast.makeText(getApplicationContext(),"رقم الهاتف غير موجود",Toast.LENGTH_SHORT).show();

                        }
                }else{
                    Toast.makeText(getApplicationContext(),"add Accessories detalies",Toast.LENGTH_LONG).show();

                }

            }
        });


        return  rooteViw;
    }
    public  void sendOrder (String phone ,String carType ,String carModel ,String   carYear ,  List<OrderList> orderList ,List<OrderImage> orderImages ){


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiService.addOrders(new Order(phone ,carType,carModel,carYear,  orderList ,orderImages));
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
