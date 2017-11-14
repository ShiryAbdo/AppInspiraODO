package com.Inspira.odo.buyerUi;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.data.ApiClient;
import com.Inspira.odo.data.ApiInterface;
import com.Inspira.odo.data.Model.Order;
import com.Inspira.odo.data.Model.OrderImage;
import com.Inspira.odo.data.Model.OrderList;
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

import static android.content.ContentValues.TAG;

public class AddAntherPartDetails extends AppCompatActivity {

    Button submet_requst ;
    List<OrderList> orderList ;
    List<OrderImage> orderImages ;
    EditText part , enginCapasty ,color ;
    Bundle  bundle ;
    String  car_type ,car_modle ,car_year , request_type;
    ImageView add_image ;
    private static final int INTENT_REQUEST_CODE = 100;
    UploadImageHelper uploadImageHelper ;
    private String imagepath=null;
    TextView viewT ;
    String imageName = null ;

//    partType == request_type ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_anther_part_details);
        bundle = getIntent().getExtras();
         if(bundle !=null){
             car_type=bundle.getString("car_type");
             car_modle=bundle.getString("car_modle");
             car_year=bundle.getString("car_year");
             request_type=bundle.getString("request_type");

         }
        orderList= new ArrayList<>();
        orderImages= new ArrayList<>();
        uploadImageHelper= new UploadImageHelper();
        uploadImageHelper.requestStoragePermission(AddAntherPartDetails.this);

        viewT = (TextView)findViewById(R.id.view);
        part=(EditText)findViewById(R.id.part) ;
        enginCapasty=(EditText)findViewById(R.id.enginCapasty) ;
        color=(EditText)findViewById(R.id.color) ;
        add_image= (ImageView)findViewById(R.id.add_image);
        add_image.setOnClickListener(new View.OnClickListener() {
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
     viewT.setText(imageName);
     Toast.makeText(getApplicationContext(),imageName,Toast.LENGTH_SHORT).show();
 }



        submet_requst =(Button)findViewById(R.id.submet_requst);
        submet_requst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(part.getText().toString().trim().equals("")&&
                   enginCapasty.getText().toString().trim().equals("")&&
                    color.getText().toString().trim().equals("")){
                    orderList.add(new OrderList(request_type,part.getText().toString().trim(), enginCapasty.getText().toString().trim(),color.getText().toString().trim()," "," "));

                }
                if (imageName!=null) {
                    orderImages.add(new OrderImage(viewT.getText().toString().trim()));
                }else {
                    Toast.makeText(getApplicationContext(),"select image",Toast.LENGTH_SHORT).show();

                }

                final Dialog dialog = new Dialog(AddAntherPartDetails.this, R.style.custom_dialog_theme);
                dialog.setContentView(R.layout.comfirm_layout);
                final Button ok = dialog.findViewById(R.id.ok);
                Button no  = dialog.findViewById(R.id.no);
                 no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });


                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        dialog.dismiss();

                        sendOrder("01210287863",car_type ,car_modle,car_year ,orderList ,orderImages);




                        final Dialog okdialog = new Dialog(AddAntherPartDetails.this, R.style.custom_dialog_theme);
                        okdialog.setContentView(R.layout.ok_dialog);
                        Button OK_d = okdialog.findViewById(R.id.ok);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == INTENT_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {


                try {
                    Uri selectedImageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                    add_image.setImageBitmap(bitmap);
                    imagepath = getPath(selectedImageUri);
                    File f = new File(imagepath);
                      imageName = f.getName();

                    InputStream is = getContentResolver().openInputStream(data.getData());

//                    UploadImageHelper.uploadImage(uploadImageHelper.getBytes(is));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
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

         Call<ResponseBody> call = apiService.addOrders(new Order(phone ,carType,carModel,carYear,  orderList ,orderImages));
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

     }
}
