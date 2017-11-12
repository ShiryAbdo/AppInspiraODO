package com.Inspira.odo.buyerUi;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class AddAntherPartDetails extends AppCompatActivity {

    Button submet_requst ;
    List<OrderList> orderList ;
    List<OrderImage> orderImages ;
    EditText part , enginCapasty ,color ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_anther_part_details);
        orderList= new ArrayList<>();
        orderImages= new ArrayList<>();
        part=(EditText)findViewById(R.id.part) ;
        enginCapasty=(EditText)findViewById(R.id.enginCapasty) ;
        color=(EditText)findViewById(R.id.color) ;
        orderList.add(new OrderList("partType","part","engineCapacity","color","ampere","size"));
        orderImages.add(new OrderImage("photo.jpg"));



        submet_requst =(Button)findViewById(R.id.submet_requst);
        submet_requst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(AddAntherPartDetails.this, R.style.custom_dialog_theme);
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

                        Call<ResponseBody> call = apiService.addOrders(new Order("012039489038","carType","carModel","carYear",  orderList ,orderImages));
                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody>call, Response<ResponseBody> response) {
                                int responseCode = response.code();
                                Toast.makeText(getApplicationContext(),"ResponseCode: " + responseCode,Toast.LENGTH_LONG).show();
                                Log.d("CODE", "ResponseCode: " + responseCode);
                            }

                            @Override
                            public void onFailure(Call<ResponseBody>call, Throwable t) {
                                // Log error here since request failed
                                Log.e(TAG, t.toString());
                            }
                        });





                        final Dialog okdialog = new Dialog(AddAntherPartDetails.this, R.style.custom_dialog_theme);
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

    }
}
