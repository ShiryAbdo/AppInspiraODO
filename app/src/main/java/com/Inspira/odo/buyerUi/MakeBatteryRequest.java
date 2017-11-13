package com.Inspira.odo.buyerUi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.adaptors.CustomArrayAdapter_Spinner;

import java.util.ArrayList;


public class MakeBatteryRequest extends Fragment {

  Spinner  KNOW_BATTERY;
    EditText Size ;
    ArrayList<String> catogires ;
    CustomArrayAdapter_Spinner  customArrayAdapter_spinner ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rooteView = inflater.inflate(R.layout.fragment_make_battery_request, container, false);
        KNOW_BATTERY = (Spinner)rooteView.findViewById(R.id.KNOW_BATTERY);
        Size =(EditText)rooteView.findViewById(R.id.Size);
        catogires= new ArrayList<>();
        catogires.add(getString(R.string.donot_know));
        catogires.add(getString(R.string.know));
        customArrayAdapter_spinner = new CustomArrayAdapter_Spinner(getActivity(),
                R.layout.customspinneritem, catogires);
        KNOW_BATTERY.setAdapter(customArrayAdapter_spinner);


        // Spinner click listener
        KNOW_BATTERY.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                // On selecting a spinner item
                String itemTyp= parent.getItemAtPosition(position).toString();

                    Toast.makeText(parent.getContext(),itemTyp, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return  rooteView;
    }

}
