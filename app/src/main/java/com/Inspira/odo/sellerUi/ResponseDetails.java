package com.Inspira.odo.sellerUi;


import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Inspira.odo.R;



public class ResponseDetails extends Fragment {





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rooteViwe = inflater.inflate(R.layout.fragment_response_details, container, false);
        getActivity().setTitle(R.string.Response_Details);
        return  rooteViwe ;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.Response_Details);
    }

}
