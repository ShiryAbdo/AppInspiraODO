package com.Inspira.odo.sellerUi;


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
public class SellerFragment extends Fragment {


    Button CreatCampanyAcount ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View roooteView = inflater.inflate(R.layout.fragment_seller, container, false);
        getActivity().setTitle("Home");
        CreatCampanyAcount=(Button)roooteView.findViewById(R.id.CreatCampanyAcount);
        CreatCampanyAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContinuingRegSeler.class);
                startActivity(intent);
            }
        });
        return  roooteView;
    }

}
