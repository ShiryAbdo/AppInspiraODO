package com.Inspira.odo.buyerUi;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

 import com.Inspira.odo.R;
import com.Inspira.odo.adaptors.CustomArrayAdapter_Spinner;
import com.Inspira.odo.data.Model.DataCar;
import com.Inspira.odo.database.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class HomeBuper extends Fragment {

    CustomArrayAdapter_Spinner  myAdaptor_CarType ,myAdaptor_car_model ,myAdaptor_car_year ,myAdaptor_Type_of_requset;
    Spinner SpinnerCarType ,your_car_model ,your_car_year ,Type_of_requset;
    ArrayList<String>categories_CarType ,categories_car_model ,categories_car_year ,categories_Type_of_requset;
    Button saveData ;
    DataCar dataCar ;
    String  item_model ,itemType,itemTYear ,itemRequest ,   carType ,carYear ,carePar ,carModle;
    Map<String, ArrayList<String> > AllData ;
    SharedPreferencesManager sharedPreferencesManager ;
    int positionModle ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
   View rooteView = inflater.inflate(R.layout.fragment_home_buper, container, false);
        sharedPreferencesManager= new SharedPreferencesManager(getActivity());

        getActivity().setTitle(R.string.Home);
        AllData = new HashMap<>() ;
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
        categories_CarType.add(  getString(R.string.your_car_type));
        dataCar= new DataCar();
        AllData =dataCar.getAllData();
        dataCar.getCarTypesEnglish() ;
        categories_CarType.addAll(dataCar.getCarTypesEnglish() );
        categories_CarType.add(getString(R.string.your_car_type));
        categories_car_model.add(getString(R.string.your_car_modle));
        categories_car_year.add(getString(R.string.your_car_year));
        categories_car_year.addAll(dataCar.getYears());

        // Spinner Drop down elements
        categories_Type_of_requset.add(getString(R.string.your_Type_requse));
        categories_Type_of_requset.add("spareParts");
        categories_Type_of_requset.add("batteries");
        categories_Type_of_requset.add("tyres");
        categories_Type_of_requset.add("accessories");



        myAdaptor_CarType = new CustomArrayAdapter_Spinner(getActivity(),
                R.layout.customspinneritem, categories_CarType);
        myAdaptor_car_year = new CustomArrayAdapter_Spinner(getActivity(),
                R.layout.customspinneritem, categories_car_year);
        myAdaptor_Type_of_requset = new CustomArrayAdapter_Spinner(getActivity(),
                R.layout.customspinneritem, categories_Type_of_requset);
        SpinnerCarType.setAdapter(myAdaptor_CarType);
        your_car_year.setAdapter(myAdaptor_car_year);
        Type_of_requset.setAdapter(myAdaptor_Type_of_requset);
          carType =sharedPreferencesManager.getCar_Type();

        if (carType != null) {
             carModle =sharedPreferencesManager.getCar_Modle();
            carYear =sharedPreferencesManager.getCar_Year() ;
              carePar =sharedPreferencesManager.getPartType();
             int  positionType = categories_CarType.indexOf(carType);
            categories_car_model.addAll(AllData.get(carType));
              positionModle =categories_car_model.indexOf(carModle);
            int positioYear = categories_car_year.indexOf(carYear);
            int positionPART=categories_Type_of_requset.indexOf(carePar);


            SpinnerCarType.setSelection(positionType);
            your_car_model.setSelection(positionModle);
            your_car_year.setSelection(positioYear);
            Type_of_requset.setSelection(positionPART);


        }


        // Spinner click listener
        SpinnerCarType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                // On selecting a spinner item
             String itemTyp= parent.getItemAtPosition(position).toString();
                if(itemTyp.equals(getString(R.string.your_car_type))){
                    itemType= null ;
//                    Toast.makeText(parent.getContext(),getString(R.string.chose_type) , Toast.LENGTH_LONG).show();
                }else {
                    itemType= parent.getItemAtPosition(position).toString();
                    sharedPreferencesManager.setCar_Type(itemType);
                    categories_car_model.clear();
                    categories_car_model.add(getString(R.string.your_car_modle));
                    categories_car_model.addAll(AllData.get(itemType));
                    if(carType !=null){
                        your_car_model.setSelection(positionModle);
                    }

//                    Toast.makeText(parent.getContext(), itemType, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        myAdaptor_car_model = new CustomArrayAdapter_Spinner(getActivity(),
                R.layout.customspinneritem, categories_car_model);
        your_car_model.setAdapter(myAdaptor_car_model);

        your_car_model.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                // On selecting a spinner item
                 String item_mode= parent.getItemAtPosition(position).toString();
                if(item_mode.equals(getString(R.string.your_car_modle))){
                    item_model=null;
//                    Toast.makeText(parent.getContext(),getString(R.string.chose_modle) , Toast.LENGTH_LONG).show();
                }else {

                    item_model=parent.getItemAtPosition(position).toString() ;
                    sharedPreferencesManager.setCar_Modle(item_model);
//                    Toast.makeText(parent.getContext(), item_model, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        your_car_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                // On selecting a spinner item
                 String itemTYea= parent.getItemAtPosition(position).toString();
                if(itemTYea.equals(getString(R.string.your_car_year))){
                    itemTYear=null;
//                    Toast.makeText(parent.getContext(),getString(R.string.chose_modle) , Toast.LENGTH_LONG).show();
                }else {
                    itemTYear=parent.getItemAtPosition(position).toString() ;
                    sharedPreferencesManager.setCar_Year(itemTYear);
//                    Toast.makeText(parent.getContext(), itemTYear, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Type_of_requset.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                // On selecting a spinner item
                String  itemReques= parent.getItemAtPosition(position).toString();
                if(itemReques.equals(getString(R.string.your_Type_requse))){
                    itemRequest=null;
                    Toast.makeText(parent.getContext(),getString(R.string.chose_modle) , Toast.LENGTH_LONG).show();
                }else {
                    itemRequest=parent.getItemAtPosition(position).toString() ;
                    sharedPreferencesManager.setPartType(itemRequest);
                    Toast.makeText(parent.getContext(), itemRequest, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(item_model!=null&&itemType!=null&&itemTYear!=null&&itemRequest!=null){
                    Intent  intent =new Intent(getActivity(),AddAntherPartDetails.class);
                     intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                     intent.putExtra("car_type" ,itemType);
                     intent.putExtra("car_modle" ,item_model);
                     intent.putExtra("car_year" ,itemTYear);
                     intent.putExtra("request_type",itemRequest);
                     getActivity().getApplicationContext().startActivity(intent);
                }else if (carType!=null){
                     Intent  intent =new Intent(getActivity(),AddAntherPartDetails.class);
                     intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                     intent.putExtra("car_type" ,carType);
                     intent.putExtra("car_modle" ,carModle);
                     intent.putExtra("car_year" ,carYear);
                     intent.putExtra("request_type",carePar);
                     getActivity().getApplicationContext().startActivity(intent);

                 }else{
              Toast.makeText(getActivity().getApplicationContext(),getString(R.string.enter_data),Toast.LENGTH_SHORT).show();

                 }

            }
        });


        return rooteView;
    }


    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.home);
    }


}
