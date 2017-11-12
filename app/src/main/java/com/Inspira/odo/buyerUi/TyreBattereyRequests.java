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
public class TyreBattereyRequests extends Fragment {


    public TyreBattereyRequests() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
 View rooteView=     inflater.inflate(R.layout.fragment_tyre_batterey_requests, container, false);
        getActivity().setTitle(R.string.TyreBattereyRequests);

        return  rooteView;
    }

}
