package com.Inspira.odo.buyerUi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Inspira.odo.R;

public class AccesoriesRequests extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View rooteViw =   inflater.inflate(R.layout.fragment_accesories_requests, container, false);
        getActivity().setTitle(R.string.AccesoriesRequests);
        return  rooteViw;
    }

}