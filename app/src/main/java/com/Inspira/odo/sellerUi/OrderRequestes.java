package com.Inspira.odo.sellerUi;


import android.os.Bundle;
import android.app.Fragment;
 import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Inspira.odo.R;


public class OrderRequestes extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rooteView = inflater.inflate(R.layout.fragment_order_requestes, container, false);
        getActivity().setTitle(R.string.Order_Requestes);
         return  rooteView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.Order_Requestes);
    }

}
