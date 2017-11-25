package com.Inspira.odo.model;


import com.Inspira.odo.data.Model.Response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class FilterData {
    private List<Response> mList = new ArrayList<>();

    public List<Response> getmList() {
        return mList;
    }

    public void setmList(List<Response> mList) {
        this.mList = mList;
    }

    public FilterData(List<Response> mList) {
        this.mList = mList;
    }

    public FilterData() {
    }

    public List<Response> getAllData() {
        return mList;
    }



    public List<Response> getAddressFilteredResponse(List<String> category, List<Response> mList) {
        List<Response> tempList = new ArrayList<>();
        for (Response Response : mList) {
            for (String c : category) {
                if (Response.getSellerData().getCompanyAddress().equalsIgnoreCase(c)) {
                    tempList.add(Response);
                }
            }

        }
        return tempList;
    }


    public List<Response> getPriceFilteredMyOrderMin(List<String> price, List<Response> mList) {
        List<Response> tempList = new ArrayList<>();
        List<Double> doubleList = new ArrayList<>();
        for(String s : price) doubleList.add(Double.valueOf(s));
        for (Response MyOrder : mList) {
            for (Double b : doubleList) {
                if (MyOrder.getPrice()<= b) {
                    tempList.add(MyOrder);
                }
            }
        }
        return tempList;
    }

    public List<Response> getPriceFilteredMyOrderMax(List<String> price, List<Response> mList) {
        List<Response> tempList = new ArrayList<>();
        List<Double> doubleList = new ArrayList<>();
        for(String s : price) doubleList.add(Double.valueOf(s));
        for (Response MyOrder : mList) {
            for (Double b : doubleList) {
                if (MyOrder.getPrice()>= b) {
                    tempList.add(MyOrder);
                }
            }
        }
        return tempList;
    }


    public class compareMyOrder implements Comparator<Response> {
        public int compare(Response a, Response b) {
            if (a.getPrice() > b.getPrice())
                return -1; // highest value first
            if (a.getPrice() == b.getPrice())
                return 0;
            return 1;
        }
    }

    public Response getMax(){
        if (!mList.isEmpty())
            return Collections.min(mList, new compareMyOrder());
        else
            return null;
    }

    public Response getMin(){
        return Collections.min(mList, new compareMyOrder());

    }
}
