package com.Inspira.odo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Ahmed on 11/11/2017.
 */

public class UserRegistrationPref {
    public static String USER_FUll_NAME = "Registration.Seller.User.Fname";
    public static String USER_PHONE_NO = "Registration.Seller.User.Phone";
    public static String USER_PASSWORD = "Registration.Seller.User.Password";
    public static String USER_HASH_VALUE = "Registration.Seller.User.HashVal";
    public static String USER_EMAIL = "Registration.Seller.User.Email";
    public static String USER_COMPANY_NAME = "Registration.Seller.User.CompanyName";
    public static String USER_COMPANY_TYPE = "Registration.Seller.User.CompanyType";
    public static String USER_COMPANY_ADDRESS = "Registration.Seller.User.CompanyAddress";
    public static String USER_COMPANY_LAT = "Registration.Seller.User.CompanyAddress.lat";
    public static String USER_COMPANY_LNG = "Registration.Seller.User.CompanyAddress.lng";
    public static void persist(Context context, String key, String value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(key, value);
        editor.apply();
    }

    public static String getPersistedData(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, "null");
    }
}
