package com.Inspira.odo.sellerUi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.WorkingOn.RecyclerViewClickListener;
import com.Inspira.odo.WorkingOn.ThreeLevelListAdapter;
import com.Inspira.odo.buyerUi.NavigationDrawerBuyer;
import com.Inspira.odo.data.ApiClient;
import com.Inspira.odo.data.ApiInterface;
import com.Inspira.odo.data.Model.BuyerRegistration;
import com.Inspira.odo.data.Model.CompanyOnMap;
import com.Inspira.odo.data.Model.DataCar;
import com.Inspira.odo.data.Model.SellerRegistration;
import com.Inspira.odo.data.Model.WorkingOn;
import com.Inspira.odo.database.CarsDataBase;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.helper.LocaleHelper;
import com.Inspira.odo.mainLuncher.SinInRegis;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.facebook.FacebookSdk.getApplicationContext;


public class WorkingOnOne extends AppCompatActivity implements RecyclerViewClickListener {
    private ExpandableListView expandableListView;
    DataCar dataCar=new DataCar();
    ImageView competRegister;
    SharedPreferencesManager sharedPreferencesManager ;
    Bundle bundle ;
    String  fName, phoneNo, password, email ,latitude,longitude, companyName,company_address ,your_Type_requse ,area;
    LocaleHelper localeHelper ;
    ImageView go_back ;
    ArrayList<WorkingOn> workingOns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_on_one);
        sharedPreferencesManager= new SharedPreferencesManager(getApplicationContext());
        go_back= (ImageView)findViewById(R.id.go_back);
        localeHelper= new LocaleHelper();
        String lange=  localeHelper.getLanguage(WorkingOnOne.this);
        if(lange.equals("ar")){
            go_back.setImageResource(R.drawable.back_right);
        }else if(lange.equals("en")){
            go_back.setImageResource(R.drawable.back);
        }
        bundle=getIntent().getExtras();
        if(bundle!=null){

            latitude=bundle.getString("latitude");
            longitude=bundle.getString("longitude");
            fName=bundle.getString("fName");
            phoneNo= bundle.getString("phoneNo");
            password=bundle.getString("password");
            email=bundle.getString("email");
            companyName= bundle.getString("companyName");
            company_address= bundle.getString("company_address");
            your_Type_requse=bundle.getString("your_Type_requse");
            area= bundle.getString("area");

        }
        competRegister=(ImageView)findViewById(R.id.competRegister);
        competRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                workingOns=CarsDataBase.commenData(CarsDataBase.readAllReadLater(WorkingOnOne.this,"cars"));
                CompanyOnMap companyOnMap = new CompanyOnMap(longitude,latitude);
                if(fName!=null&&email!=null&&password!=null&&companyName!=null&&company_address!=null&&your_Type_requse!=null){
                    if(phoneNo!=null){
                        addSeller(phoneNo, fName,email,password,"1bu4i3iug262bi6u22j2ij3bug5ug45i",companyName,company_address ,area,companyOnMap ,your_Type_requse,workingOns);
                        Toast.makeText(getApplicationContext(), phoneNo +"",Toast.LENGTH_LONG).show();

                    }else {

                    }
                    Toast.makeText(getApplicationContext(),getString(R.string.enter_data),Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getApplicationContext(),getString(R.string.enter_data),Toast.LENGTH_SHORT).show();

                }



            }
        });
        competRegister.setVisibility(View.GONE);
        CarsDataBase.delateAll(this,"cars");
        setUpAdapter();
    }

    private void setUpAdapter() {
        expandableListView = (ExpandableListView) findViewById(R.id.expandible_listview);
        //passing three level of information to constructor
        ThreeLevelListAdapter threeLevelListAdapterAdapter = new ThreeLevelListAdapter(this, dataCar.getCarTypesEnglish(), dataCar.getAllDataList(),this);
        expandableListView.setAdapter(threeLevelListAdapterAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });


    }

    @Override
    public void recyclerViewListClicked(boolean checked,String parent,String header,String year) {
        if(checked){
            CarsDataBase.storeReadLater(this,"cars", parent, header,year);
            if(competRegister.getVisibility()==View.GONE) {
                competRegister.setVisibility(View.VISIBLE);
            }




        }
        else {
            CarsDataBase.delateOne(this,"cars", parent, header,year);
            if(CarsDataBase.readAllReadLater(this,"cars").size()==0){
                competRegister.setVisibility(View.GONE);
            }
        }
    }
    public  void addSeller (final String phoneNumber, final String fullName, String email, String password, String hashVal, String companyName, String companyAddress ,String area, CompanyOnMap companyOnMap, String companyType, List<WorkingOn> workingOn){
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiService.doSellerRegister(new SellerRegistration(phoneNumber, fullName,email,password,hashVal,companyName,companyAddress ,area ,companyOnMap ,companyType ,workingOn));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody>call, Response<ResponseBody> response) {
                int responseCode = response.code();
                Log.d("CODE", "ResponseCode: " + responseCode);
                if(responseCode==200){
                    sharedPreferencesManager.setLogin(true);
                    sharedPreferencesManager.setUser_Name(fullName);
                    sharedPreferencesManager.setUser_Phoe(phoneNumber);
                    sharedPreferencesManager.setUserType("seller");

                    Toast.makeText(getApplicationContext(),getString(R.string.successfully_registered),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), NavigationDrawerSeler.class);
                    startActivity(intent);
                    finish();
                }else {

                    Toast.makeText(getApplicationContext(), getString(R.string.enter_data),Toast.LENGTH_SHORT).show();
                 }
            }

            @Override
            public void onFailure(Call<ResponseBody>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

    }
}