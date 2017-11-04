package com.Inspira.odo.mainLuncher;


import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Inspira.odo.R;


public class EditProfile extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View  rooteiew = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        getActivity().setTitle(R.string.Edit_Profile);

        return  rooteiew;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getActivity().setTitle(R.string.Edit_Profile);

    }

}
