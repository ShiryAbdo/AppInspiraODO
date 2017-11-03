package com.Inspira.odo.sellerUi;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Inspira.odo.R;
import com.Inspira.odo.adaptors.DataAdapter;
import com.Inspira.odo.adaptors.DataSellerHomeAdaptor;
import com.Inspira.odo.model.SellerHomeData;

import java.util.ArrayList;



public class SellerHome extends Fragment {

    RecyclerView recycler_view ;
    View rooteView ;
    private ArrayList<SellerHomeData> data;
    private DataSellerHomeAdaptor dataSellerHomeAdaptor ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rooteView =inflater.inflate(R.layout.fragment_seller_home, container, false);
         data = new ArrayList<>();
        getActivity().findViewById(R.id.filter);
        initViews();
         return rooteView ;

    }


    private void initViews(){
        recycler_view = (RecyclerView)rooteView.findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((this.getActivity()));
        recycler_view.setLayoutManager(layoutManager);


        loadJSON();
    }

    private void loadJSON() {
        data.add(new SellerHomeData(0,"Front Bark Pads","Frarry","Verna","1999","1500vv" ,"red","1:13",true) );
        data.add(new SellerHomeData(0," Pads","Hundia","Verna","1990","1500vv" ,"black","5:13",false) );
        data.add(new SellerHomeData(0,"Front ","Marsedis","Verna","1559","1500vv" ,"blue","7:13",true) );
        data.add(new SellerHomeData(0,"part part","Hundia","Verna","1459","1500vv" ,"selver","11:13",false) );
        data.add(new SellerHomeData(0,"Bark ","Marsedis","Verna","1339","1500vv" ,"black","1:13",true) );
        data.add(new SellerHomeData(0,"Front Bark Pads","Frarry","Verna","1909","1500vv" ,"red","1:13",false) );
        dataSellerHomeAdaptor= new DataSellerHomeAdaptor(data,getActivity().getBaseContext() ,getActivity());
        recycler_view.setAdapter(dataSellerHomeAdaptor);
        dataSellerHomeAdaptor.notifyDataSetChanged();

    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.home);
    }



}
