package com.Inspira.odo.buyerUi;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Inspira.odo.R;

public class SparepartsRequests extends Fragment {
    RecyclerView recycler_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View rooteView = inflater.inflate(R.layout.fragment_my_request, container, false);
        getActivity().setTitle(R.string.SparepartsRequests);
        getActivity().findViewById(R.id.filter).setVisibility(View.VISIBLE);
        recycler_view= (RecyclerView)rooteView.findViewById(R.id.recycler_view);

        return  rooteView ;
    }

}
