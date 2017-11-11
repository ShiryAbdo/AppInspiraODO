package com.Inspira.odo.buyerUi;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
 import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.Inspira.odo.R;
import com.Inspira.odo.adaptors.CustomArrayAdapter_Spinner;

import java.util.ArrayList;


public class HomeBuper extends Fragment {

    CustomArrayAdapter_Spinner  myAdaptor_CarType ,myAdaptor_car_model ,myAdaptor_car_year ,myAdaptor_Type_of_requset;
    Spinner SpinnerCarType ,your_car_model ,your_car_year ,Type_of_requset;
    ArrayList<String>categories_CarType ,categories_car_model ,categories_car_year ,categories_Type_of_requset;
    Button saveData ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
   View rooteView = inflater.inflate(R.layout.fragment_home_buper, container, false);

        getActivity().setTitle(R.string.Home);
        getActivity().findViewById(R.id.filter).setVisibility(View.GONE);
        SpinnerCarType =(Spinner)rooteView.findViewById(R.id.SpinnerCarType);
        your_car_model= (Spinner)rooteView.findViewById(R.id.your_car_model);
        your_car_year=(Spinner)rooteView.findViewById(R.id.your_car_year);
        saveData=(Button)rooteView.findViewById(R.id.saveData);
        Type_of_requset=(Spinner) rooteView.findViewById(R.id.Type_of_requset);
        categories_CarType= new ArrayList<>();
        categories_car_model= new ArrayList<>();
        categories_car_year= new ArrayList<>();
        categories_Type_of_requset= new ArrayList<>();

//        when clicked save data   and when opened again show what selected before
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent =new Intent(getActivity(),AddAntherPartDetails.class);
                startActivity(intent);

            }
        });



        // Spinner Drop down elements
        categories_CarType.add(R.string.your_car_type+"");
        categories_CarType.add("Business Services");
        categories_CarType.add("Computers");
        categories_CarType.add("Education");
        categories_CarType.add("Personal");
        categories_CarType.add("Travel");

        // Spinner Drop down elements
        categories_car_model.add(R.string.your_car_modle+"");
        categories_car_model.add("Business Services");
        categories_car_model.add("Computers");
        categories_car_model.add("Education");
        categories_car_model.add("Personal");
        categories_car_model.add("Travel");
        // Spinner Drop down elements
        categories_car_year.add(R.string.your_car_year+"");
        categories_car_year.add("Business Services");
        categories_car_year.add("Computers");
        categories_car_year.add("Education");
        categories_car_year.add("Personal");
        categories_car_year.add("Travel");
        // Spinner Drop down elements
        categories_Type_of_requset.add(R.string.your_Type_requse+"");
        categories_Type_of_requset.add("Business Services");
        categories_Type_of_requset.add("Computers");
        categories_Type_of_requset.add("Education");
        categories_Type_of_requset.add("Personal");
        categories_Type_of_requset.add("Travel");



        myAdaptor_CarType = new CustomArrayAdapter_Spinner(getActivity(),
                R.layout.customspinneritem, categories_CarType);
        myAdaptor_car_model = new CustomArrayAdapter_Spinner(getActivity(),
                R.layout.customspinneritem, categories_car_model);
        myAdaptor_car_year = new CustomArrayAdapter_Spinner(getActivity(),
                R.layout.customspinneritem, categories_car_year);
        myAdaptor_Type_of_requset = new CustomArrayAdapter_Spinner(getActivity(),
                R.layout.customspinneritem, categories_Type_of_requset);

        SpinnerCarType.setAdapter(myAdaptor_CarType);
        your_car_model.setAdapter(myAdaptor_car_model);
        your_car_year.setAdapter(myAdaptor_car_year);
        Type_of_requset.setAdapter(myAdaptor_Type_of_requset);


        return rooteView;
    }


    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.home);
    }


}
