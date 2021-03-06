package com.Inspira.odo.buyerUi;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.adaptors.MyRequestAdapter;
import com.Inspira.odo.data.ApiClient;
import com.Inspira.odo.data.ApiInterface;
import com.Inspira.odo.model.MyRequest;
import com.Inspira.odo.database.MyOrder;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.model.FilterData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class SparepartsRequests extends Fragment {
    RecyclerView recycler_view;
//    odo.eu-gb.mybluemix.net/profile/buyer/myOrders
    SharedPreferencesManager sharedPreferencesManager ;
    String PHONE_number ;
    MyRequestAdapter myRequestAdapter ;
    private ArrayList<MyOrder> MyOrderList;
    ArrayList<String>dataDisription ;
    private FilterData data = new FilterData();
    private ArrayMap<String, List<String>> applied_filters = new ArrayMap<>();



    View rooteView ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rooteView = inflater.inflate(R.layout.fragment_my_request, container, false);
        getActivity().findViewById(R.id.filter).setVisibility(View.GONE);
        setHasOptionsMenu(true);
        sharedPreferencesManager= new SharedPreferencesManager(getActivity());
        getActivity().setTitle(R.string.SparepartsRequests);
         PHONE_number= sharedPreferencesManager.getUser_Phoe();
        Toast.makeText(getActivity(),PHONE_number,Toast.LENGTH_LONG).show();
        MyOrderList= new ArrayList<>();

        initViews() ;


        return  rooteView ;
    }



    private void initViews(){
        recycler_view = (RecyclerView)rooteView.findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((this.getActivity()));
        recycler_view.setLayoutManager(layoutManager);


        loadJSON();

    }
    private void loadJSON() {
        PHONE_number= sharedPreferencesManager.getUser_Phoe();

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<MyOrder>> call = apiService.domyOrders(new MyRequest(PHONE_number)) ;
        call.enqueue(new Callback<ArrayList<MyOrder>>() {
            @Override
            public void onResponse(Call<ArrayList<MyOrder>>call, Response<ArrayList<MyOrder>> response) {
                int responseCode = response.code();
                Toast.makeText(getActivity(),responseCode+"",Toast.LENGTH_LONG).show();
                if(responseCode==200){

                    ArrayList<MyOrder> bankJSONResponse = response.body();
                    if(!bankJSONResponse.isEmpty()){
                        ArrayList<MyOrder> MyOrderList= new ArrayList<MyOrder>();
                        MyOrderList.addAll(bankJSONResponse);
                        myRequestAdapter = new MyRequestAdapter(MyOrderList,getActivity());
                        recycler_view.setAdapter(myRequestAdapter);
                     }
                }


            }

            @Override
            public void onFailure(Call<ArrayList<MyOrder>>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }






}
