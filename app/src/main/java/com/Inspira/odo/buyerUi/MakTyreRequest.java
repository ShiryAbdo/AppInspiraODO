package com.Inspira.odo.buyerUi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Inspira.odo.R;


public class MakTyreRequest extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View rooteview = inflater.inflate(R.layout.fragment_mak_tyre_request, container, false);
        return  rooteview;
    }

}
