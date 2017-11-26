package com.Inspira.odo.database;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.Inspira.odo.data.Model.CarModel;
import com.Inspira.odo.data.Model.WorkingOn;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class CarsDataBase {

    public static void storeReadLater(Context context,String dataBaseName ,String carType,String carModel,String carYear) {
        TinyDB tinydb = new TinyDB(context);
        Toast.makeText(getApplicationContext(),carType+ "/" + carModel + "/" + carYear,Toast.LENGTH_SHORT).show();
        String readLater = carType+ "/" + carModel + "/" + carYear;
        ArrayList<String> readLaterList = tinydb.getListString(dataBaseName);
        if (readLaterList == null) {
            readLaterList = new ArrayList<>();
        }
        readLaterList.add(readLater);
        tinydb.putListString(dataBaseName, readLaterList);
    }

    public static ArrayList<CarItems> readAllReadLater(Context context, String dataBaseName) {
        ArrayList<CarItems> readLaterArrayList = new ArrayList<>();

        TinyDB tinydb;
        tinydb = new TinyDB(context);

        ArrayList<String> readLaterList = tinydb.getListString(dataBaseName);
        for (String string : readLaterList) {
            String segments[] = string.split("/");
            if (segments.length == 3 ) {
                String type = segments[0];
                String model = segments[1];
                String year = segments[2];
                CarItems later = new CarItems();
                later.setCarModel(model);
                later.setCarType(type);
                later.setCarYear(year);
                readLaterArrayList.add(later);
            }
        }
        return readLaterArrayList;
    }
    public static   ArrayList<WorkingOn>commenData (ArrayList<CarItems> arrayList){
        ArrayList<WorkingOn> commendArray = new ArrayList<>();
        ArrayList<CarModel> carModelArray = new ArrayList<>();
        List<String> years = new ArrayList<>();

        for(int i =0 ; i<arrayList.size();i++){
            if(i+1 ==arrayList.size()){

                if (arrayList.get(i).getCarType().equals(arrayList.get(i-1).getCarType())&&arrayList.get(i).getCarModel().equals(arrayList.get(i-1).getCarModel())){
                    CarModel  carModel= new CarModel();
                    if(!years.contains(arrayList.get(i).getCarYear())){
                        years.add(arrayList.get(i).getCarYear());
                    }
                    carModel.setModel(arrayList.get(i).getCarModel());
                    carModel.setYears(years);
                    carModelArray.add(carModel);
                    commendArray.add(new  WorkingOn (arrayList.get(i).getCarType(),carModelArray) );
                    Toast.makeText(getApplicationContext(),"add",Toast.LENGTH_SHORT).show();


                }else {
                    Toast.makeText(getApplicationContext(),"els",Toast.LENGTH_SHORT).show();
                    years.clear();
                }
             }else {

                if (arrayList.get(i).getCarType().equals(arrayList.get(i+1).getCarType())&&arrayList.get(i).getCarModel().equals(arrayList.get(i+1).getCarModel())){
                    CarModel  carModel= new CarModel();
                    if(!years.contains(arrayList.get(i).getCarYear())){
                        years.add(arrayList.get(i).getCarYear());
                    }
                    carModel.setModel(arrayList.get(i).getCarModel());
                    carModel.setYears(years);
                    carModelArray.add(carModel);
                    commendArray.add(new  WorkingOn (arrayList.get(i).getCarType(),carModelArray) );
                    Toast.makeText(getApplicationContext(),"add",Toast.LENGTH_SHORT).show();


                }else {
                    Toast.makeText(getApplicationContext(),"els",Toast.LENGTH_SHORT).show();
                    years.clear();
                }
            }


        }
        return commendArray;
    }
    public static void delateOne(Context context,String dataBaseName ,String carType,String carModel,String carYear) {
        TinyDB tinydb = new TinyDB(context);
        ArrayList<String> readLaterList = tinydb.getListString(dataBaseName);
        readLaterList.remove(carType+ "/" + carModel + "/" + carYear);
        tinydb.putListString(dataBaseName, readLaterList);
    }
    public static void delateAll(Context context,String dataBaseName ) {
        TinyDB tinydb = new TinyDB(context);
        ArrayList<String> readLaterList = tinydb.getListString(dataBaseName);
        readLaterList.clear();
        tinydb.putListString(dataBaseName, readLaterList);
    }
}
