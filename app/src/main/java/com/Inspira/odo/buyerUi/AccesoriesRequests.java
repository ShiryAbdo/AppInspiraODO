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

public class AccesoriesRequests extends Fragment {
 EditText PartId ;
 TextView add_anther_part_detalis ;
    Button submet_requst ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View rooteViw =   inflater.inflate(R.layout.fragment_accesories_requests, container, false);
        getActivity().setTitle(R.string.AccesoriesRequests);
        PartId=(EditText)rooteViw.findViewById(R.id.PartId);
        add_anther_part_detalis=(TextView)rooteViw.findViewById(R.id.add_anther_part_detalis);
        submet_requst= (Button)rooteViw.findViewById(R.id.submet_requst);

        return  rooteViw;
    }

}
