 package com.Inspira.odo.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.Inspira.odo.data.Model.Response;

import java.util.ArrayList;


 public class SharedPreferencesManager {
     int PRIVATE_MODE = 0;
     SharedPreferences pref;
     ArrayList<Response> responses = new ArrayList<Response>();
    static SharedPreferences.Editor editor;
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn" ;
    Context mContext;
    private  static final String PREFS_NAME = "Inspira";

    private  String User_type= "User_Type";
    private  String User_Name = "User_Name";
    private  String User_Email= "User_Email";
    private  String User_Password= "User_Password";
    private  String User_Phoe = "User_Phone";

    private  String Car_Type =  "Car_Type" ;
    private  String Car_Modle = "Car_Modle";
   private   String Car_Year =  "Car_Year"  ;
    private  String partTyp ="partType" ;

    private  String UserType = "UserType";
    private  String Token ="Token";
    private  String latitud ;
    private  String  longitud ;
     private  String  area ;



    public SharedPreferencesManager(Context mContext) {
        this.mContext = mContext;
        pref = mContext.getSharedPreferences(PREFS_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public String getUser_Email() {
        return pref.getString(User_Email,null);
    }

    public void setUser_Email(String user_Email) {
        editor.putString(User_Email,user_Email);
        editor.commit();
    }
    public String getUser_type() {
        return pref.getString(User_type,null);
    }

    public void setUser_type(String user_type) {
        editor.putString(User_type,user_type);
        editor.commit();
    }

    public String getUser_Password() {
        return pref.getString(User_Password,null);
    }

    public void setUser_Password(String user_Password) {
        editor.putString(User_Password,user_Password);
        editor.commit();
    }

    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.commit();
        Log.d("Tag", "User login session modified!");
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public String getUser_Name() {
        return pref.getString(User_Name,null);
    }

    public void setUser_Name(String user_Name) {
        editor.putString(User_Name,user_Name);
        editor.commit();

    }

    public String getUser_Phoe() {
        return pref.getString(User_Phoe,null);
    }

    public void setUser_Phoe(String user_Phoe) {
        editor.putString(User_Phoe,user_Phoe);
        editor.commit();
    }

    public String getCar_Type() {
        return  pref.getString(Car_Type,null);
    }

    public void setCar_Type(String car_Type) {
        editor.putString(Car_Type,car_Type);
        editor.commit();
     }

    public String getCar_Modle() {
        return  pref.getString(Car_Modle,null);
    }

    public void setCar_Modle(String car_Modle) {
        editor.putString(Car_Modle,car_Modle);
        editor.commit();
     }

    public String getCar_Year()
    {
        return  pref.getString(Car_Year,null);
    }

    public void setCar_Year(String car_Year) {
        editor.putString(Car_Year,car_Year);
        editor.commit();
     }

    public String getPartType() {
        return pref.getString(partTyp,null);
    }

    public void setPartType(String partType) {
        editor.putString(partTyp,partType);
        editor.commit();
        this.partTyp = partType;
    }

    public String getUserType()
    {
        return  pref.getString(UserType,null);
    }

    public void setUserType(String userType) {
        editor.putString(UserType,userType);
        editor.commit();
    }

    public String getToken() {
        return  pref.getString(Token,null);
    }

    public void setToken(String token) {
        editor.putString(Token, token);
        editor.commit();
    }

    public String getLatitude() {
        return  pref.getString(latitud,null);
    }

    public void setLatitude(String latitude) {
        editor.putString(latitud, latitude);
        editor.commit();
     }

    public String getLongitude() {
        return  pref.getString(longitud,null);
    }

    public void setLongitude(String longitude) {
        editor.putString(latitud, longitude);
        editor.commit();
     }

     public String getArea() {
         return  pref.getString(area,null);

     }

     public void setArea(String Area) {
         this.area = area;
         editor.putString(area, Area);
         editor.commit();
     }
 }
