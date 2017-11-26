package com.Inspira.odo.sellerUi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.Inspira.odo.R;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.helper.LocaleHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RespondtoaReques extends AppCompatActivity {
    TextView name_of_part  ,Date_of_day;
    EditText price  ,Describetion;
    ImageView image ;
     Bundle bundle ;
    String buyerPhoneNumber ,orderID  ,part;
    SharedPreferencesManager sharedPreferencesManager ;
    LocaleHelper localeHelper ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_respondtoa_reques);
        setTitle(R.string.Respond_to_aReques);
        image=(ImageView)findViewById(R.id.image);
//        Calendar cal = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        System.out.println(dateFormat.format(cal.getTime()));
        localeHelper= new LocaleHelper();
        String lange=  localeHelper.getLanguage(RespondtoaReques.this);
        sharedPreferencesManager=new SharedPreferencesManager(RespondtoaReques.this);
        if(lange.equals("ar")){
            image.setImageResource(R.drawable.back_wiht);
        }else if(lange.equals("en")){
            image.setImageResource(R.drawable.back_eft_whit);
        }
        name_of_part= (TextView)findViewById(R.id.name_of_part);
        Date_of_day=(TextView)findViewById(R.id.Date_of_day);
        Date_of_day.setText(dateFormat.format(cal.getTime()));
        price=(EditText)findViewById(R.id.price);
        Describetion=(EditText)findViewById(R.id.Describetion);
        bundle=getIntent().getExtras();
        if(bundle!=null){
            buyerPhoneNumber = bundle.getString("buyerPhoneNumber");
            orderID=bundle.getString("orderID");
            part = bundle.getString("part");
        }
        name_of_part.setText(part);



    }
    @Override
    protected void onResume() {
        super.onResume();
       setTitle(R.string.Respond_to_aReques);
    }
}
