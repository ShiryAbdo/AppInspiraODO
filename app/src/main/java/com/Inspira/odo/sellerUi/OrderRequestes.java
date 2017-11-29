package com.Inspira.odo.sellerUi;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.adaptors.DataSellerHomeAdaptor;
import com.Inspira.odo.data.ApiClient;
import com.Inspira.odo.data.ApiInterface;
import com.Inspira.odo.model.MyRequest;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.mainLuncher.MyApplication;
import com.Inspira.odo.sellerData.CarDetails;
import com.Inspira.odo.sellerData.FilterData;
import com.Inspira.odo.sellerData.Order;
import com.Inspira.odo.sellerData.RelatedOrder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.facebook.FacebookSdk.getApplicationContext;


public class OrderRequestes extends Fragment {
    RecyclerView recycler_view;
    View rooteView;
     ArrayList<RelatedOrder> MyOrderList;
     SharedPreferencesManager sharedPreferencesManager;
    MyApplication myApplication;
     private DataSellerHomeAdaptor dataSellerHomeAdaptor;
    private FilterData data = new FilterData();
    String phone_number ;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rooteView= inflater.inflate(R.layout.fragment_order_requestes, container, false);
        sharedPreferencesManager = new SharedPreferencesManager(getActivity());
        phone_number =   sharedPreferencesManager.getUser_Phoe() ;
        getActivity().setTitle(R.string.Order_Requestes);
        getActivity().findViewById(R.id.filter).setVisibility(View.GONE);
        initViews();
        return  rooteView;
    }

    private void initViews() {
        recycler_view = (RecyclerView) rooteView.findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((this.getActivity()));
        recycler_view.setLayoutManager(layoutManager);


        loadJSON();
    }


    private void loadJSON() {

//"01009560622"
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<RelatedOrder>> call = apiService.getRelatedOrder(new MyRequest(phone_number));
        call.enqueue(new Callback<ArrayList<RelatedOrder>>() {
            @Override
            public void onResponse(Call<ArrayList<RelatedOrder>> call, Response<ArrayList<RelatedOrder>> response) {
                int responseCode = response.code();
                if (responseCode == 200) {
                    ArrayList<RelatedOrder> bankJSONResponse = response.body();
                    if (!bankJSONResponse.isEmpty()) {
                        MyOrderList = new ArrayList<RelatedOrder>();
                        RelatedOrder gg= new RelatedOrder();
                        gg.setBuyerPhoneNumber("");
                        gg.setCarDetails(new CarDetails());
                        gg.setDate("");
                        gg.setId("");
                        gg.setOrder(new Order());
                        gg.setOrderPartType("");

                        for (int i =0; i<bankJSONResponse.size(); i++){
                            if(i==2){
                                MyOrderList.add(gg);
                            }else {
                                MyOrderList.add(bankJSONResponse.get(i));
                            }

                        }
                        MyOrderList.addAll(bankJSONResponse);

                        dataSellerHomeAdaptor = new DataSellerHomeAdaptor(MyOrderList, getActivity(), getActivity());
                        recycler_view.setAdapter(dataSellerHomeAdaptor);
                        data.setmList(MyOrderList);
                        myApplication = new MyApplication();
                        dataSellerHomeAdaptor.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "ResponseCode: " + responseCode, Toast.LENGTH_LONG).show();
//                        Log.d("CODE", "ResponseCode: " + responseCode);
                    }
                }


            }

            @Override
            public void onFailure(Call<ArrayList<RelatedOrder>> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.Order_Requestes);
    }

}
