package com.Inspira.odo.buyerUi;


import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Inspira.odo.R;

public class MyFavourites extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View rooteView = inflater.inflate(R.layout.fragment_my_favourites, container, false);
        getActivity().setTitle(R.string.My_Favorites);
        return rooteView;
    }

}
