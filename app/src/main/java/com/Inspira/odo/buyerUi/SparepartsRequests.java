package com.Inspira.odo.buyerUi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Inspira.odo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SparepartsRequests extends Fragment {


    public SparepartsRequests() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View rooteView = inflater.inflate(R.layout.fragment_spareparts_requests, container, false);
        getActivity().setTitle(R.string.SparepartsRequests);
        return  rooteView ;
    }

}
