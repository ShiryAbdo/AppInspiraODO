package com.Inspira.odo.util;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ahmed on 11/11/2017.
 */

public class GeoAddress {

    private static LatLng latLng;

    public static JSONObject getLocationInfo(String address) {
        StringBuilder stringBuilder = new StringBuilder();


            address = address.replaceAll(" ","%20");
            HttpDataHandler http = new HttpDataHandler();
            String url = "http://maps.google.com/maps/api/geocode/json?address=" + address + "&sensor=false";
        String response = http.GetHTTPData(url);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(response);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return jsonObject;
    }

    public static LatLng getLatLong(JSONObject jsonObject) {

        try {

            double longitute = ((JSONArray) jsonObject.get("results")).getJSONObject(0)
                    .getJSONObject("geometry").getJSONObject("location")
                    .getDouble("lng");

            double latitude = ((JSONArray) jsonObject.get("results")).getJSONObject(0)
                    .getJSONObject("geometry").getJSONObject("location")
                    .getDouble("lat");

            latLng = new LatLng(latitude,longitute);

        } catch (JSONException e) {
            return null;

        }

        return latLng;
    }
}
