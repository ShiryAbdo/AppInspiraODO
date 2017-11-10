package com.Inspira.odo.buyerUi;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.Inspira.odo.R;
import com.Inspira.odo.data.ApiClient;
import com.Inspira.odo.data.ApiInterface;
import com.Inspira.odo.data.Model.Order;
import com.Inspira.odo.data.Model.OrderImage;
import com.Inspira.odo.data.Model.OrderList;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;



// image sends in anther url ;
public class AddAntherPartDetails extends Fragment {

    Button submet_requst ;
    List<OrderList> orderList ;
    List<OrderImage> orderImages ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rooteView = inflater.inflate(R.layout.fragment_add_anther_part_details, container, false);
        orderList= new ArrayList<>();
        orderImages= new ArrayList<>();

        submet_requst =(Button)rooteView.findViewById(R.id.submet_requst);
        submet_requst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getContext(), R.style.custom_dialog_theme);
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





                        ApiInterface apiService =
                                ApiClient.getClient().create(ApiInterface.class);

                        Call<ResponseBody> call = apiService.addOrders(new Order("userPhone","carType","carModel","carYear",  orderList));
                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody>call, Response<ResponseBody> response) {
                                int responseCode = response.code();
                                Log.d("CODE", "ResponseCode: " + responseCode);
                            }

                            @Override
                            public void onFailure(Call<ResponseBody>call, Throwable t) {
                                // Log error here since request failed
                                Log.e(TAG, t.toString());
                            }
                        });





                        final Dialog okdialog = new Dialog(getContext(), R.style.custom_dialog_theme);
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

        return  rooteView;
    }

}
