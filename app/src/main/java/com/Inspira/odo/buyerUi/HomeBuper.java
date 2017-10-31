package com.Inspira.odo.buyerUi;


import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.Inspira.odo.R;
import com.Inspira.odo.adaptors.CustomArrayAdapter_Spinner;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeBuper extends Fragment {

    CustomArrayAdapter_Spinner  myAdaptor ;
    Spinner SpinnerCarType ;
    ArrayList<String>categories ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
   View rooteView = inflater.inflate(R.layout.fragment_home_buper, container, false);
        SpinnerCarType =(Spinner)rooteView.findViewById(R.id.SpinnerCarType);




        // Spinner Drop down elements
        categories= new ArrayList<>();
        categories.add("Automobile");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");

        myAdaptor = new CustomArrayAdapter_Spinner(getActivity(),
                R.layout.customspinneritem, categories);

        SpinnerCarType.setAdapter(myAdaptor);


        return rooteView;
    }

}
