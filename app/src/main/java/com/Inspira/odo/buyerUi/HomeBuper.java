package com.Inspira.odo.buyerUi;


import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.Inspira.odo.R;
import com.Inspira.odo.adaptors.CustomArrayAdapter_Spinner;

import java.util.ArrayList;


public class HomeBuper extends Fragment {

    CustomArrayAdapter_Spinner  myAdaptor ;
    Spinner SpinnerCarType ,your_car_model ,your_car_year ,Type_of_requset;
    ArrayList<String>categories ;
    Button saveData ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
   View rooteView = inflater.inflate(R.layout.fragment_home_buper, container, false);

        getActivity().setTitle("Home");
        SpinnerCarType =(Spinner)rooteView.findViewById(R.id.SpinnerCarType);
        your_car_model= (Spinner)rooteView.findViewById(R.id.your_car_model);
        your_car_year=(Spinner)rooteView.findViewById(R.id.your_car_year);
        saveData=(Button)rooteView.findViewById(R.id.saveData);
        Type_of_requset=(Spinner) rooteView.findViewById(R.id.Type_of_requset);


//        when clicked save data   and when opened again show what selected before
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



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
        your_car_model.setAdapter(myAdaptor);
        your_car_year.setAdapter(myAdaptor);
        Type_of_requset.setAdapter(myAdaptor);


        return rooteView;
    }

}
