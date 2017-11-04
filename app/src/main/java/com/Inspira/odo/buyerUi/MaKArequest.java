package com.Inspira.odo.buyerUi;


import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.Inspira.odo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MaKArequest extends Fragment {


    Button submet_requst ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rooteView = inflater.inflate(R.layout.fragment_ma_karequest, container, false);
        getActivity().setTitle(R.string.MaK_Arequest);
        submet_requst =(Button)rooteView.findViewById(R.id.submet_requst);
        submet_requst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity(), R.style.custom_dialog_theme);
                dialog.setContentView(R.layout.comfirm_layout);
                final Button ok = (Button) dialog.findViewById(R.id.ok);
                Button no  =(Button)dialog.findViewById(R.id.no);
                // if button is clicked, close the custom dialog
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                         dialog.dismiss();
                    }
                });


                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final Dialog okdialog = new Dialog(getActivity(), R.style.custom_dialog_theme);
                        okdialog.setContentView(R.layout.ok_dialog);
                        Button OK_d =(Button)okdialog.findViewById(R.id.ok);
                        OK_d.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                okdialog.dismiss();

                            }
                        });
                        okdialog.show();

                    }
                });

                dialog.show();
            }
        });
        return  rooteView ;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getActivity().setTitle(R.string.MaK_Arequest);

    }

}
