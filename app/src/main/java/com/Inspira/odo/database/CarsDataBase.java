package com.Inspira.odo.database;

import android.content.Context;
import android.util.Log;
import java.util.ArrayList;

public class CarsDataBase {

    public static void storeReadLater(Context context,String dataBaseName ,String carType,String carModel,String carYear) {
        TinyDB tinydb = new TinyDB(context);
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

        TinyDB tinydb = new TinyDB(context);

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
