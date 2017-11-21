package com.Inspira.odo.sellerUi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.Inspira.odo.R;
import com.Inspira.odo.WorkingOn.RecyclerViewClickListener;
import com.Inspira.odo.WorkingOn.ThreeLevelListAdapter;
import com.Inspira.odo.data.Model.DataCar;
import com.Inspira.odo.database.CarsDataBase;



public class WorkingOnOne extends AppCompatActivity implements RecyclerViewClickListener {
    private ExpandableListView expandableListView;
    DataCar dataCar=new DataCar();
    ImageView competRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_on_one);
        competRegister=(ImageView)findViewById(R.id.competRegister);
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