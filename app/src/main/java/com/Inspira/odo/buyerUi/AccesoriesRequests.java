package com.Inspira.odo.buyerUi;


import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.data.ApiClient;
import com.Inspira.odo.data.ApiInterface;
import com.Inspira.odo.data.Model.MakOrder;
import com.Inspira.odo.data.Model.OrderImage;
import com.Inspira.odo.data.Model.OrderList;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.helper.UploadImageHelper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;
import static com.facebook.FacebookSdk.getApplicationContext;

public class AccesoriesRequests extends Fragment {
    EditText PartId ;
    TextView add_anther_part_detalis ;
    Button submet_requst ;
    List<OrderList> orderList ;
    List<OrderImage> orderImages ;
    ImageView addImage ;
    SharedPreferencesManager sharedPreferencesManager ;
    String carType ,carYear ,carePar ,carModle ,PHONE_number;
    private static final int INTENT_REQUEST_CODE = 100;
    UploadImageHelper uploadImageHelper ;
    private String imagepath=null;
    String imageName = null ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View rooteViw =   inflater.inflate(R.layout.fragment_accesories_requests, container, false);
        sharedPreferencesManager= new SharedPreferencesManager(getActivity());
        carModle =sharedPreferencesManager.getCar_Modle();
        carYear =sharedPreferencesManager.getCar_Year() ;
        carType =sharedPreferencesManager.getCar_Type();
        PHONE_number= sharedPreferencesManager.getUser_Phoe();
        uploadImageHelper= new UploadImageHelper();
        uploadImageHelper.requestStoragePermission(getActivity());
        carePar="Accessories";
        getActivity().setTitle(R.string.AccesoriesRequests);
        PartId=(EditText)rooteViw.findViewById(R.id.PartId);
        addImage= (ImageView)rooteViw.findViewById(R.id.addImage);
        orderList= new ArrayList<>();
        orderImages = new ArrayList<>();
        if(PartId.getText().toString().trim()!=null){
            orderList.add(new OrderList(carePar,PartId.getText().toString().trim(),"","","",""));
        }else{
            Toast.makeText(getApplicationContext(),"add Accessories detalies",Toast.LENGTH_LONG).show();

        }
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");

                try {
                    startActivityForResult(intent, INTENT_REQUEST_CODE);

                } catch (ActivityNotFoundException e) {

                    e.printStackTrace();
                }

            }
        });

        if (imageName!=null){
//      it seen null
             Toast.makeText(getApplicationContext(),imageName,Toast.LENGTH_SHORT).show();
        }


        add_anther_part_detalis=(TextView)rooteViw.findViewById(R.id.add_anther_part_detalis);
        add_anther_part_detalis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(PartId.getText().toString().trim()!=null){
                    if(PHONE_number!=null){
                        if(imageName!=null){
                            Toast.makeText(getApplicationContext(),imageName,Toast.LENGTH_SHORT).show();
                            orderImages.add(new OrderImage(imageName));
                            sendOrder(PHONE_number,carType,carModle ,carYear,orderList ,orderImages);
                             AccesoriesRequests newFragment = new AccesoriesRequests();
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment_container, newFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();

                        }else {
                            Toast.makeText(getApplicationContext(),"إختار صوره",Toast.LENGTH_SHORT).show();

                        }


                    }else {
                        Toast.makeText(getApplicationContext(),"رقم الهاتف غير موجود",Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(getApplicationContext(),"add Accessories detalies",Toast.LENGTH_LONG).show();

                }


            }
        });
        submet_requst= (Button)rooteViw.findViewById(R.id.submet_requst);
        submet_requst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(PartId.getText().toString().trim()!=null){
                        if(PHONE_number!=null){
                            if(imageName!=null){
                                Toast.makeText(getApplicationContext(),imageName,Toast.LENGTH_SHORT).show();

                                orderImages.add(new OrderImage(imageName));
                                sendOrder(PHONE_number,carType,carModle ,carYear,orderList ,orderImages);

                            }else {
                                Toast.makeText(getApplicationContext(),"إختار صوره",Toast.LENGTH_SHORT).show();

                            }


                        }else {
                            Toast.makeText(getApplicationContext(),"رقم الهاتف غير موجود",Toast.LENGTH_SHORT).show();

                        }
                }else{
                    Toast.makeText(getApplicationContext(),"add Accessories detalies",Toast.LENGTH_LONG).show();

                }

            }
        });


        return  rooteViw;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == INTENT_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {


                try {
                    Uri selectedImageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
                    addImage.setImageBitmap(bitmap);
                    imagepath = getPath(selectedImageUri);
                    File f = new File(imagepath);
                    imageName = f.getName();

                    InputStream is = getActivity().getContentResolver().openInputStream(data.getData());

                    UploadImageHelper.uploadImage(uploadImageHelper.getBytes(is));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public String getPath(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getActivity().getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }

    public  void sendOrder (String phone ,String carType ,String carModel ,String   carYear ,  List<OrderList> orderList ,List<OrderImage> orderImages ){


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiService.addOrders(new MakOrder(phone ,carType,carModel,carYear,  orderList ,orderImages));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody>call, Response<ResponseBody> response) {
                int responseCode = response.code();
                Toast.makeText(getApplicationContext(),getString(R.string.Request_successfully_submitted ),Toast.LENGTH_LONG).show();
             }

            @Override
            public void onFailure(Call<ResponseBody>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

    }

}
