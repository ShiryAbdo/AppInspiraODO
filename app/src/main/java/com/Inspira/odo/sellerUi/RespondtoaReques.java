package com.Inspira.odo.sellerUi;


import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Inspira.odo.R;


public class RespondtoaReques extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         View rooteiew = inflater.inflate(R.layout.fragment_respondtoa_reques, container, false);
        getActivity().setTitle(R.string.Respond_to_aReques);
        return  rooteiew ;
    }


    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.Respond_to_aReques);
    }

}
