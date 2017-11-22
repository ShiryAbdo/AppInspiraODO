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
import com.Inspira.odo.adaptors.DataAdapter;
import com.Inspira.odo.adaptors.MyRequestAdapter;
import com.Inspira.odo.data.ApiClient;
import com.Inspira.odo.data.ApiInterface;
import com.Inspira.odo.data.Model.*;
import com.Inspira.odo.data.Model.MyRequest;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.model.FilterData;
import com.Inspira.odo.model.SellerHomeData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.facebook.FacebookSdk.getApplicationContext;

public class SparepartsRequests extends Fragment {
    RecyclerView recycler_view;
//    odo.eu-gb.mybluemix.net/profile/buyer/myOrders
    SharedPreferencesManager sharedPreferencesManager ;
    String PHONE_number ;
    MyRequestAdapter myRequestAdapter ;
    private ArrayList<MyOrder> MyOrderList;
    private FilterData data = new FilterData();
    private ArrayMap<String, List<String>> applied_filters = new ArrayMap<>();



    View rooteView ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rooteView = inflater.inflate(R.layout.fragment_my_request, container, false);
        setHasOptionsMenu(true);
        sharedPreferencesManager= new SharedPreferencesManager(getActivity());
        getActivity().setTitle(R.string.SparepartsRequests);
        getActivity().findViewById(R.id.filter).setVisibility(View.VISIBLE);
        PHONE_number= sharedPreferencesManager.getUser_Phoe();
        MyOrderList= new ArrayList<>();

        initViews() ;

        //onClick

//        addToSelectedMap("price_max", String.valueOf(price_max));
//        addToSelectedMap("price_min", String.valueOf(price_min));
//
//
//        if (result != null) {
//            ArrayMap<String, List<String>> applied_filter = (ArrayMap<String, List<String>>) result;
//            if (applied_filter.size() != 0) {
//                List<MyOrder> filteredList = data.getAllData();
//                //iterate over arraymap
//                for (Map.Entry<String, List<String>> entry : applied_filter.entrySet()) {
//                    Log.d("k9res", "entry.key: " + entry.getKey());
//                    switch (entry.getKey()) {
//                        case "price_max":
//                            filteredList = data.getPriceFilteredMyOrderMax(entry.getValue(), filteredList);
//                            break;
//                        case "price_min":
//                            filteredList = data.getPriceFilteredMyOrderMin(entry.getValue(), filteredList);
//                            break;
//                    }
//
//                    myRequestAdapter.clear();
//                    myRequestAdapter.addAll(filteredList);
//                }
//            }
//        }
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


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<MyOrder>> call = apiService.domyOrders(new MyRequest("01009560620")) ;
        call.enqueue(new Callback<ArrayList<MyOrder>>() {
            @Override
            public void onResponse(Call<ArrayList<MyOrder>>call, Response<ArrayList<MyOrder>> response) {
                int responseCode = response.code();
                if(responseCode==200){
                    ArrayList<MyOrder> bankJSONResponse = response.body();
                    if(!bankJSONResponse.isEmpty()){
                        ArrayList<MyOrder> MyOrderList= new ArrayList<MyOrder>();
                        MyOrderList.addAll(bankJSONResponse);
                        Toast.makeText(getApplicationContext(),bankJSONResponse.get(0).getBuyerPhoneNumber()+"",Toast.LENGTH_LONG).show();
                        myRequestAdapter = new MyRequestAdapter(MyOrderList,getActivity());
                        recycler_view.setAdapter(myRequestAdapter);
                        Toast.makeText(getApplicationContext(),"ResponseCode: " + responseCode,Toast.LENGTH_LONG).show();
                        Log.d("CODE", "ResponseCode: " + responseCode);
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

    private void addToSelectedMap(String key, String value) {
        if (applied_filters.get(key) != null && !applied_filters.get(key).contains(value)) {
            applied_filters.get(key).add(value);
        } else {
            List<String> temp = new ArrayList<>();
            temp.add(value);
            applied_filters.put(key, temp);
        }
    }




}
