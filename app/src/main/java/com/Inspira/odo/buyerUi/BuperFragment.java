package com.Inspira.odo.buyerUi;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.Inspira.odo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BuperFragment extends Fragment {


     Button Campany_acount ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rooteView= inflater.inflate(R.layout.fragment_buper, container, false);
        Campany_acount=(Button)rooteView.findViewById(R.id.Campany_acount);
        Campany_acount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),NavigationDrawerBuyer.class);
                startActivity(intent);

            }
        });
        return rooteView;
    }

}
