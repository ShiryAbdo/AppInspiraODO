package com.Inspira.odo.model;


import com.Inspira.odo.data.Model.MyOrder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class FilterData {
    private List<MyOrder> mList = new ArrayList<>();

    public List<MyOrder> getmList() {
        return mList;
    }

    public void setmList(List<MyOrder> mList) {
        this.mList = mList;
    }

    public FilterData(List<MyOrder> mList) {
        this.mList = mList;
    }

    public FilterData() {
    }

    public List<MyOrder> getAllData() {
        return mList;
    }




//    public List<MyOrder> getPriceFilteredMyOrderMin(List<String> price, List<MyOrder> mList) {
//        List<MyOrder> tempList = new ArrayList<>();
//        List<Double> doubleList = new ArrayList<>();
//        for(String s : price) doubleList.add(Double.valueOf(s));
//        for (MyOrder MyOrder : mList) {
//            for (Double b : doubleList) {
//                if (MyOrder.getPrice()<= b) {
//                    tempList.add(MyOrder);
//                }
//            }
//        }
//        return tempList;
//    }
//
//    public List<MyOrder> getPriceFilteredMyOrderMax(List<String> price, List<MyOrder> mList) {
//        List<MyOrder> tempList = new ArrayList<>();
//        List<Double> doubleList = new ArrayList<>();
//        for(String s : price) doubleList.add(Double.valueOf(s));
//        for (MyOrder MyOrder : mList) {
//            for (Double b : doubleList) {
//                if (MyOrder.getPrice()>= b) {
//                    tempList.add(MyOrder);
//                }
//            }
//        }
//        return tempList;
//    }
//
//
//
//    public class compareMyOrder implements Comparator<MyOrder> {
//        public int compare(MyOrder a, MyOrder b) {
//            if (a.getPrice() > b.getPrice())
//                return -1; // highest value first
//            if (a.getPrice() == b.getPrice())
//                return 0;
//            return 1;
//        }
//    }
//
//    public MyOrder getMax(){
//        if (!mList.isEmpty())
//            return Collections.min(mList, new compareMyOrder());
//        else
//            return null;
//    }

//    public MyOrder getMin(){
//        return Collections.min(mList, new compareMyOrder());
//
//    }
}
