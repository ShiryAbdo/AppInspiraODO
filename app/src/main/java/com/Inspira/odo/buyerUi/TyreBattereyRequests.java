package com.Inspira.odo.buyerUi;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
 import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.Inspira.odo.R;


public class TyreBattereyRequests extends Fragment {
    Button Tyre , Battery;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
 View rooteView=     inflater.inflate(R.layout.fragment_tyre_batterey_requests, container, false);
        getActivity().setTitle(R.string.TyreBattereyRequests);
        Tyre= (Button)rooteView.findViewById(R.id.Tyre);
        Battery= (Button)rooteView.findViewById(R.id.Battery);
        Tyre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MakTyreRequest newFragment = new MakTyreRequest();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        Battery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MakeBatteryRequest newFragment = new MakeBatteryRequest();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        return  rooteView;
    }

}
