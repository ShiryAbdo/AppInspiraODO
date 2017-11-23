package com.Inspira.odo.buyerUi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.adaptors.MyRequestAdapter;
import com.Inspira.odo.data.Model.MyOrder;
import com.Inspira.odo.data.Model.Response;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.mainLuncher.MyApplication;
import com.Inspira.odo.model.FilterData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class RequestResponses extends AppCompatActivity {
    RecyclerView recycler_view;
    SharedPreferencesManager sharedPreferencesManager ;
    String PHONE_number;
    MyRequestAdapter myRequestAdapter ;
    private ArrayList<MyOrder> MyOrderList;
    private FilterData data = new FilterData();
    private ArrayMap<String, List<String>> applied_filters = new ArrayMap<>();
    MyApplication myApplication ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_responses);
//        ArrayList<Response> myList = (ArrayList<Response>) getIntent().getSerializableExtra("mylist");
        myApplication= new MyApplication();
//        ArrayList<Response> myList  =myApplication.getResponses();
        //For default value, just to get no errors while getting no value from the SharedPreferences
        Gson gson = new Gson();
        String empty_list = gson.toJson(new ArrayList<Response>());
        SharedPreferences sp = this.getSharedPreferences("KEY", Context.MODE_PRIVATE);


        ArrayList<Response> mSelectedList = gson.fromJson(sp.getString("KEY", empty_list),
                new TypeToken<ArrayList<Response>>() {
                }.getType());

        mSelectedList.get(0).getDescription();
        Toast.makeText(getApplicationContext(), mSelectedList.get(0).getPrice()+"this",Toast.LENGTH_LONG).show();

        sharedPreferencesManager= new SharedPreferencesManager(this);
        PHONE_number= sharedPreferencesManager.getUser_Phoe();
        MyOrderList= new ArrayList<>();
        initViews();
    }



    private void initViews(){
        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((this));
        recycler_view.setLayoutManager(layoutManager);


        loadJSON();

    }

    private void loadJSON() {

    }
}
