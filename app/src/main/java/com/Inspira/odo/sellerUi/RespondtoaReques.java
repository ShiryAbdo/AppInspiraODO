package com.Inspira.odo.sellerUi;

import android.content.Intent;
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
import com.Inspira.odo.buyerUi.NavigationDrawerBuyer;
import com.Inspira.odo.data.ApiClient;
import com.Inspira.odo.data.ApiInterface;
import com.Inspira.odo.data.Model.BuyerRegistration;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.helper.LocaleHelper;
import com.Inspira.odo.sellerData.RespondToOrder;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class RespondtoaReques extends AppCompatActivity {
    TextView name_of_part  ,Date_of_day;
    EditText price  ,Describetion;
    ImageView image ;
     Bundle bundle ;
    String buyerPhoneNumber ,orderID  ,part ,PhoneNumber;
    SharedPreferencesManager sharedPreferencesManager ;
    LocaleHelper localeHelper ;
    Calendar cal ;
    Button Respon ;
    AdView adView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_respondtoa_reques);
        // Load an ad into the AdMob banner view.
        adView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);
        setTitle(R.string.Respond_to_aReques);
        image=(ImageView)findViewById(R.id.image);
//        Calendar cal = Calendar.getInstance();
        cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        System.out.println(dateFormat.format(cal.getTime()));
        localeHelper= new LocaleHelper();
        String lange=  localeHelper.getLanguage(RespondtoaReques.this);
        sharedPreferencesManager=new SharedPreferencesManager(RespondtoaReques.this);

        PhoneNumber= sharedPreferencesManager.getUser_Phoe();
        if(lange.equals("ar")){
            image.setImageResource(R.drawable.back_wiht);
        }else if(lange.equals("en")){
            image.setImageResource(R.drawable.back_eft_whit);
        }
        name_of_part= (TextView)findViewById(R.id.name_of_part);
        Date_of_day=(TextView)findViewById(R.id.Date_of_day);

        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        String test = sdf.format(cal.getTime());

        Date_of_day.setText(test);
        price=(EditText)findViewById(R.id.price);
        Describetion=(EditText)findViewById(R.id.Describetion);
        bundle=getIntent().getExtras();
        if(bundle!=null){
            buyerPhoneNumber = bundle.getString("buyerPhoneNumber");
            orderID=bundle.getString("orderID");
            part = bundle.getString("part");
        }
        name_of_part.setText(part);
        Respon=(Button)findViewById(R.id.Respon);
        Respon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!price.getText().toString().trim().equals("")&&!Describetion.getText().toString().trim().equals("")){
                    makRespone(PhoneNumber,buyerPhoneNumber,orderID,price.getText().toString().trim(),Describetion.getText().toString().trim());

                }else {
                    Toast.makeText(getApplicationContext(),getString(R.string.enter_data),Toast.LENGTH_SHORT).show();

                }
            }
        });



    }
    public  void makRespone(String sellerPhoneNumber, String buyerPhoneNumber, String orderID, String price,
                            String description){
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiService.doRespondtoaReques(new RespondToOrder (sellerPhoneNumber,buyerPhoneNumber ,
                orderID,price,description));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody>call, Response<ResponseBody> response) {
                int responseCode = response.code();
                Log.d("CODE", "ResponseCode: " + responseCode);
                if(responseCode==200){

                    Toast.makeText(getApplicationContext(),getString(R.string.Response),Toast.LENGTH_SHORT).show();


                }else {

                    Toast.makeText(getApplicationContext(),getString(R.string.Not_sent),Toast.LENGTH_SHORT).show();
                 }
            }

            @Override
            public void onFailure(Call<ResponseBody>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });


    }
    @Override
    protected void onResume() {
        super.onResume();
       setTitle(R.string.Respond_to_aReques);
    }
}
