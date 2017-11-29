package com.Inspira.odo.buyerUi;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.adaptors.CustomArrayAdapter_Spinner;
import com.Inspira.odo.adaptors.MyRequestAdapter;
import com.Inspira.odo.adaptors.ResponseAdaptor;
import com.Inspira.odo.database.MyOrder;
import com.Inspira.odo.data.Model.Response;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.helper.LocaleHelper;
import com.Inspira.odo.mainLuncher.MyApplication;
import com.Inspira.odo.model.FilterData;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RequestResponses extends AppCompatActivity {
    RecyclerView recycler_view;
    SharedPreferencesManager sharedPreferencesManager ;
    String PHONE_number;
       MyApplication myApplication ;
    private FilterData data = new FilterData();
    private ArrayMap<String, List<String>> applied_filters = new ArrayMap<>();
    ResponseAdaptor responseAdaptor ;
    List<Response> mSelectedList ;
    LocaleHelper localeHelper ;
    ImageView go_back ;
    String orderId ,arraType ;
    ArrayList<String>AreaArray ;
AdView adView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_responses);
        // Load an ad into the AdMob banner view.
        adView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);
        go_back= (ImageView)findViewById(R.id.go_back);
        localeHelper= new LocaleHelper();
        String lange=  localeHelper.getLanguage(RequestResponses.this);
        if(lange.equals("ar")){
            go_back.setImageResource(R.drawable.back_right);
        }else if(lange.equals("en")){
            go_back.setImageResource(R.drawable.back);
        }
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RequestResponses.this, NavigationDrawerBuyer.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        mSelectedList = intent.getParcelableArrayListExtra("Response");
        Bundle bundle = getIntent().getExtras();
        orderId=bundle.getString("orderId");
        data.setmList(mSelectedList);
        myApplication= new MyApplication();
        initViews();

//         Toast.makeText(getApplicationContext(), mSelectedList.get(0).getPrice()+"this",Toast.LENGTH_LONG).show();

        sharedPreferencesManager= new SharedPreferencesManager(this);
        PHONE_number= sharedPreferencesManager.getUser_Phoe();
       findViewById(R.id.filter).setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              final Dialog okdialog = new Dialog(RequestResponses.this, R.style.custom_dialog_theme);
              okdialog.setContentView(R.layout.dialog_response_filter);
              Button OK_d = okdialog.findViewById(R.id.Search);
              final EditText price_from =okdialog.findViewById(R.id.price_from);
              final EditText to_price =okdialog.findViewById(R.id.to_price);
              final Spinner addArea =okdialog.findViewById(R.id.addArea);
              final  EditText Describetion=okdialog.findViewById(R.id.Describetion);
              AreaArray= new ArrayList<>();
              AreaArray.add(getString(R.string.choseArea));
              AreaArray.add(getString(R.string.cairo));
              AreaArray.add(getString(R.string.ma3adi));
              AreaArray.add(getString(R.string.giza));
               CustomArrayAdapter_Spinner  Adaptor = new CustomArrayAdapter_Spinner(RequestResponses.this,
                      R.layout.customspinneritem, AreaArray);
              addArea.setAdapter(Adaptor);


              // Spinner click listener
              addArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                  @Override
                  public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                      // On selecting a spinner item
                      // On selecting a spinner item
                      String  itemReques= parent.getItemAtPosition(position).toString();
                      if(itemReques.equals(getString(R.string.choseArea))){
                          arraType=null;
                          Toast.makeText(parent.getContext(),getString(R.string.choseArea) , Toast.LENGTH_LONG).show();
                      }else {
                          arraType=parent.getItemAtPosition(position).toString() ;
                          sharedPreferencesManager.setArea(arraType);
                          Toast.makeText(parent.getContext(), arraType, Toast.LENGTH_LONG).show();
                      }
                  }

                  @Override
                  public void onNothingSelected(AdapterView<?> adapterView) {

                  }
              });
              OK_d.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      addToSelectedMap("price_max", to_price.getText().toString());
                      addToSelectedMap("price_min", price_from.getText().toString());
                      addToSelectedMap("area", arraType);
                      addToSelectedMap("describetion", Describetion.getText().toString());
                      if (mSelectedList != null) {
                          ArrayMap<String, List<String>> applied_filter = applied_filters;
                          if (applied_filter.size() != 0) {
                              List<Response> filteredList = data.getAllData();
                              //iterate over arraymap
                              for (Map.Entry<String, List<String>> entry : applied_filter.entrySet()) {
                                  Log.d("k9res", "entry.key: " + entry.getKey());
                                  switch (entry.getKey()) {
                                      case "price_max":
                                          filteredList = data.getPriceFilteredMyOrderMax(entry.getValue(), filteredList);
                                          break;
                                      case "price_min":
                                          filteredList = data.getPriceFilteredMyOrderMin(entry.getValue(), filteredList);
                                          break;
                                      case "area":
                                          filteredList = data.getAddressFilteredResponse(entry.getValue(),filteredList);
                                          break;
                                      case "describetion":
                                          filteredList = data.getPriceFilteredMyOrderDIS(entry.getValue(),filteredList);
                                          break;

                                  }

                                  responseAdaptor.clear();
                                  responseAdaptor.addAll(filteredList);
                              }
                          }
                      }
                      okdialog.dismiss();
                  }
              });
              okdialog.show();


//              addToSelectedMap("area", Area.getText().toString());

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




    private void initViews(){
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((this));
        recycler_view.setLayoutManager(layoutManager);
        responseAdaptor= new ResponseAdaptor(this, (ArrayList<Response>) mSelectedList , orderId);
        recycler_view.setAdapter(responseAdaptor);


    }


}
