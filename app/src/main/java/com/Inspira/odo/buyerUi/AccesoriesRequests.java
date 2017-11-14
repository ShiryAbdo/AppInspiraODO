package com.Inspira.odo.buyerUi;


import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.Inspira.odo.R;
import com.Inspira.odo.data.Model.OrderImage;
import com.Inspira.odo.data.Model.OrderList;

import java.util.List;

public class AccesoriesRequests extends Fragment {
    EditText PartId ;
    TextView add_anther_part_detalis ;
    Button submet_requst ;
    List<OrderList> orderList ;
    List<OrderImage> orderImages ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View rooteViw =   inflater.inflate(R.layout.fragment_accesories_requests, container, false);
        getActivity().setTitle(R.string.AccesoriesRequests);
        PartId=(EditText)rooteViw.findViewById(R.id.PartId);
        orderList.add(new OrderList("partType","part","engineCapacity","color","ampere","size"));
        orderImages.add(new OrderImage("photo.jpg"));
        add_anther_part_detalis=(TextView)rooteViw.findViewById(R.id.add_anther_part_detalis);
        submet_requst= (Button)rooteViw.findViewById(R.id.submet_requst);

        return  rooteViw;
    }

}
