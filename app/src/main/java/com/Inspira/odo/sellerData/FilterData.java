package com.Inspira.odo.sellerData;


import com.Inspira.odo.data.Model.Response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class FilterData {
    private List<RelatedOrder> mList = new ArrayList<>();

    public List<RelatedOrder> getmList() {
        return mList;
    }

    public void setmList(List<RelatedOrder> mList) {
        this.mList = mList;
    }

    public FilterData(List<RelatedOrder> mList) {
        this.mList = mList;
    }

    public FilterData() {
    }

    public List<RelatedOrder> getAllData() {
        return mList;
    }



    public List<RelatedOrder> getCarType(List<String> category, List<RelatedOrder> mList) {
        List<RelatedOrder> tempList = new ArrayList<>();
        for (RelatedOrder Response : mList) {
            for (String c : category) {
                if (Response.getCarDetails().getCarType().equalsIgnoreCase(c)) {
                    tempList.add(Response);
                }
            }

        }
        return tempList;
    }
    public List<RelatedOrder> getModle(List<String> category, List<RelatedOrder> mList) {
        List<RelatedOrder> tempList = new ArrayList<>();
        for (RelatedOrder Response : mList) {
            for (String c : category) {
                if (Response.getCarDetails().getCarModel().equalsIgnoreCase(c)) {
                    tempList.add(Response);
                }
            }

        }
        return tempList;
    }



    public List<RelatedOrder> getYeat(List<String> price, List<RelatedOrder> mList) {
        List<RelatedOrder> tempList = new ArrayList<>();
        List<Double> doubleList = new ArrayList<>();
        for(String s : price) doubleList.add(Double.valueOf(s));
        for (RelatedOrder MyOrder : mList) {
            if (MyOrder.getCarDetails(). getCarYear().equals(price)) {
                tempList.add(MyOrder);
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


    public class compareMyOrder implements Comparator<RelatedOrder> {

        @Override
        public int compare(RelatedOrder relatedOrder, RelatedOrder t1) {
            if (relatedOrder.getCarDetails().getCarModel().equals(t1.getCarDetails().getCarModel()) )
                return -1; // highest value first
            if (relatedOrder.getCarDetails().getCarModel().equals(t1.getCarDetails().getCarModel()) )
                return 0;
            return 1;
        }
    }

    public RelatedOrder getMax(){
        if (!mList.isEmpty())
            return Collections.min(mList, new compareMyOrder());
        else
            return null;
    }

    public RelatedOrder getMin(){
        return Collections.min(mList, new compareMyOrder());

    }
}
