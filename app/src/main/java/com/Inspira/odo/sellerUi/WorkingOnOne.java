package com.Inspira.odo.sellerUi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.WorkingOn.RecyclerViewClickListener;
import com.Inspira.odo.WorkingOn.ThreeLevelListAdapter;
import com.Inspira.odo.data.Model.DataCar;
import com.Inspira.odo.data.Model.WorkingOn;
import com.Inspira.odo.database.CarsDataBase;
import com.Inspira.odo.helper.LocaleHelper;
import com.Inspira.odo.mainLuncher.SinInRegis;

import java.util.ArrayList;


public class WorkingOnOne extends AppCompatActivity implements RecyclerViewClickListener {
    private ExpandableListView expandableListView;
    DataCar dataCar=new DataCar();
    ImageView competRegister;
    Bundle bundle ;
    String  fName, phoneNo, password, email ,latitude,longitude, companyName,company_address ,your_Type_requse;
    LocaleHelper localeHelper ;
    ImageView go_back ;
    ArrayList<WorkingOn> workingOns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_on_one);
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

        }
        competRegister=(ImageView)findViewById(R.id.competRegister);
        competRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                workingOns=CarsDataBase.commenData(CarsDataBase.readAllReadLater(WorkingOnOne.this,"cars"));

                Toast.makeText(getApplicationContext(),    workingOns.get(0).getCarType()  ,Toast.LENGTH_LONG).show();

                Toast.makeText(getApplicationContext(), workingOns.get(0).getCarModels().get(0).getYears().size()  +"",Toast.LENGTH_LONG).show();
                int size =workingOns.get(0).getCarModels().get(0).getYears().size();
                Toast.makeText(getApplicationContext(), workingOns.get(0).getCarModels().get(0).getYears().get(0) +"",Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), workingOns.get(0).getCarModels().get(0).getYears().get(size) +"",Toast.LENGTH_LONG).show();

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
}