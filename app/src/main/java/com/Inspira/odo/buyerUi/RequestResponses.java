package com.Inspira.odo.buyerUi;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.adaptors.MyRequestAdapter;
import com.Inspira.odo.adaptors.ResponseAdaptor;
import com.Inspira.odo.data.Model.MyOrder;
import com.Inspira.odo.data.Model.Response;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.mainLuncher.MyApplication;
import com.Inspira.odo.model.FilterData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RequestResponses extends AppCompatActivity {
    RecyclerView recycler_view;
    SharedPreferencesManager sharedPreferencesManager ;
    String PHONE_number;
    MyRequestAdapter myRequestAdapter ;
    private ArrayList<MyOrder> MyOrderList;
      MyApplication myApplication ;
    private FilterData data = new FilterData();
    private ArrayMap<String, List<String>> applied_filters = new ArrayMap<>();
    ResponseAdaptor responseAdaptor ;
    ArrayList<Response> mSelectedList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_responses);
         myApplication= new MyApplication();
          Gson gson = new Gson();
        String empty_list = gson.toJson(new ArrayList<Response>());
        SharedPreferences sp = this.getSharedPreferences("KEY", Context.MODE_PRIVATE);


          mSelectedList = gson.fromJson(sp.getString("KEY", empty_list),
                new TypeToken<ArrayList<Response>>() {
                }.getType());

         Toast.makeText(getApplicationContext(), mSelectedList.get(0).getPrice()+"this",Toast.LENGTH_LONG).show();

        sharedPreferencesManager= new SharedPreferencesManager(this);
        PHONE_number= sharedPreferencesManager.getUser_Phoe();
        MyOrderList= new ArrayList<>();
        initViews();
      findViewById(R.id.filter).setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              final Dialog okdialog = new Dialog(RequestResponses.this, R.style.custom_dialog_theme);
              okdialog.setContentView(R.layout.dialog_response_filter);
              Button OK_d = okdialog.findViewById(R.id.ok);
              EditText price_from =okdialog.findViewById(R.id.price_from);
              EditText to_price =okdialog.findViewById(R.id.to_price);
              EditText Area =okdialog.findViewById(R.id.Area);
              OK_d.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      okdialog.dismiss();

                  }
              });
              okdialog.show();

          }
      });
    }




    private void initViews(){
        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((this));
        recycler_view.setLayoutManager(layoutManager);
        responseAdaptor= new ResponseAdaptor(mSelectedList ,this);
        recycler_view.setAdapter(responseAdaptor);


    }


}
