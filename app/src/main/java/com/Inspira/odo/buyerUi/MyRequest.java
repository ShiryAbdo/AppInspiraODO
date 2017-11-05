package com.Inspira.odo.buyerUi;


import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Inspira.odo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyRequest extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rooteView = inflater.inflate(R.layout.fragment_my_request, container, false);
        getActivity().findViewById(R.id.filter).setVisibility(View.VISIBLE);
        getActivity().setTitle(R.string.MyRequest);
         return rooteView;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getActivity().setTitle(R.string.MyRequest);

    }

}
