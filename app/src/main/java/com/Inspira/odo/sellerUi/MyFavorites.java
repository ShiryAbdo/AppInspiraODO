package com.Inspira.odo.sellerUi;


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
public class MyFavorites extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
          View rooteViw =inflater.inflate(R.layout.fragment_my_favorites, container, false);
        getActivity().setTitle(R.string.My_Favorites);
        return rooteViw ;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.My_Favorites);
    }

}
