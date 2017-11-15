package com.Inspira.odo.buyerUi;

 import android.os.Bundle;
import android.app.Fragment;
 import android.support.design.widget.TextInputLayout;
 import android.util.Log;
 import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
 import android.widget.Button;
 import android.widget.CheckBox;
 import android.widget.EditText;
 import android.widget.LinearLayout;
 import android.widget.RadioButton;
 import android.widget.RadioGroup;
 import android.widget.Spinner;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.adaptors.CustomArrayAdapter_Spinner;
 import com.Inspira.odo.data.ApiClient;
 import com.Inspira.odo.data.ApiInterface;
 import com.Inspira.odo.data.Model.Order;
 import com.Inspira.odo.data.Model.OrderImage;
 import com.Inspira.odo.data.Model.OrderList;
 import com.Inspira.odo.database.SharedPreferencesManager;

 import java.util.ArrayList;
 import java.util.List;

 import okhttp3.ResponseBody;
 import retrofit2.Call;
 import retrofit2.Callback;
 import retrofit2.Response;

 import static android.content.ContentValues.TAG;
 import static com.facebook.FacebookSdk.getApplicationContext;


public class MakeBatteryRequest extends Fragment {

    Spinner  KNOW_BATTERY;
    EditText Size ;
    ArrayList<String> catogires ;
    CustomArrayAdapter_Spinner  customArrayAdapter_spinner ;
    SharedPreferencesManager sharedPreferencesManager ;
    String carType ,carYear ,carePar ,carModle ,PHONE_number;
    List<OrderList> orderList ;
    List<OrderImage> orderImages ;
    EditText amper ;
    CheckBox Run_flot_tyres  ,Reversed;
    String  Run_flot_tyresT ,polesT ,ReversedT  ,KNOW_BATTERYS;
    RadioButton  larg_poles ,small_poles ,not_know ;
    TextInputLayout tapampar ,tapsize ;
    RadioGroup radioG ;
    Button saveData ;
    LinearLayout linear ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rooteView = inflater.inflate(R.layout.fragment_make_battery_request, container, false);
         sharedPreferencesManager= new SharedPreferencesManager(getActivity());
        carModle =sharedPreferencesManager.getCar_Modle();
        carYear =sharedPreferencesManager.getCar_Year() ;
        carType =sharedPreferencesManager.getCar_Type();
        PHONE_number= sharedPreferencesManager.getUser_Phoe();
        carePar="Battery";
        KNOW_BATTERY = (Spinner)rooteView.findViewById(R.id.KNOW_BATTERY);
        Size =(EditText)rooteView.findViewById(R.id.Size);
        amper=(EditText)rooteView.findViewById(R.id.amper);
        catogires= new ArrayList<>();
        catogires.add(getString(R.string.donot_know));
        catogires.add(getString(R.string.know));
        larg_poles=(RadioButton)rooteView.findViewById(R.id.larg_poles);
        small_poles=(RadioButton)rooteView.findViewById(R.id.small_poles);
        not_know=(RadioButton)rooteView.findViewById(R.id.not_know);
        Run_flot_tyres=(CheckBox)rooteView.findViewById(R.id.Run_flot_tyres);
        Reversed=(CheckBox)rooteView.findViewById(R.id.Reversed);
        tapampar=(TextInputLayout)rooteView.findViewById(R.id.tapampar);
        tapsize=(TextInputLayout)rooteView.findViewById(R.id.tapsize);
        radioG=(RadioGroup)rooteView.findViewById(R.id.radioG);
        saveData=(Button)rooteView.findViewById(R.id.saveData);
        linear=(LinearLayout)rooteView.findViewById(R.id.linear);

        customArrayAdapter_spinner = new CustomArrayAdapter_Spinner(getActivity(),
                R.layout.customspinneritem, catogires);
        KNOW_BATTERY.setAdapter(customArrayAdapter_spinner);
        orderList= new ArrayList<>();
        orderImages = new ArrayList<>();
        if( Reversed.isChecked()){
            ReversedT=getString(R.string.Reversed);
        }

        if (Run_flot_tyres.isChecked()){
            Run_flot_tyresT =getString(R.string.Run_flot_tyres);
        }

        if(larg_poles.isChecked()){
            polesT=getString(R.string.larg_poles);
            small_poles.setChecked(false);
            not_know.setChecked(false);
        }else if(small_poles.isChecked()){
            polesT=getString(R.string.small_poles);
            larg_poles.setChecked(false);
            not_know.setChecked(false);
        }else if(not_know.isChecked()){
            polesT=getString(R.string.i_do_not_know);
            larg_poles.setChecked(false);
            small_poles.setChecked(false);
        }else {
            Toast.makeText(getApplicationContext(),"إختار نوع  البطاريات  ",Toast.LENGTH_SHORT).show();

        }


        // Spinner click listener
        KNOW_BATTERY.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                // On selecting a spinner item
                KNOW_BATTERYS= parent.getItemAtPosition(position).toString();
                         if(KNOW_BATTERYS.equals(getString(R.string.donot_know))){
                             tapampar.setVisibility(View.GONE);
                             tapsize.setVisibility(View.GONE);
                             tapsize.setVisibility(View.GONE);
                             amper.setVisibility(View.GONE);
                             Reversed.setVisibility(View.GONE);
                             radioG.setVisibility(View.GONE);
                             Run_flot_tyres.setVisibility(View.GONE);
                             linear.setVisibility(View.GONE);
                         }else{
                             tapampar.setVisibility(View.VISIBLE);
                             tapsize.setVisibility(View.VISIBLE);
                             tapsize.setVisibility(View.VISIBLE);
                             amper.setVisibility(View.VISIBLE);
                             Reversed.setVisibility(View.VISIBLE);
                             radioG.setVisibility(View.VISIBLE);
                             Run_flot_tyres.setVisibility(View.VISIBLE);
                             linear.setVisibility(View.VISIBLE);
                         }
                    Toast.makeText(parent.getContext(),KNOW_BATTERYS, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        orderImages.add(new OrderImage(""));

        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!Size.getText().toString().trim().equals("") &&!amper.getText().toString().trim().equals("")){
                    orderList.add(new OrderList(carePar,Run_flot_tyresT,"","","",Size.getText().toString().trim()));
                    if(PHONE_number!=null){
                        sendOrder(PHONE_number,carType,carModle ,carYear,orderList ,orderImages);

                    }else {
                        Toast.makeText(getApplicationContext(),"رقم الهاتف غير موجود",Toast.LENGTH_SHORT).show();

                    }
                }else{
                    if(PHONE_number!=null){
                        sendOrder(PHONE_number,carType,carModle ,carYear,orderList ,orderImages);

                    }else {
                        Toast.makeText(getApplicationContext(),"رقم الهاتف غير موجود",Toast.LENGTH_SHORT).show();

                    }
                    orderList.add(new OrderList(carePar,Run_flot_tyresT,"","","",""));
                    Toast.makeText(getApplicationContext(),"add Bsttery detalies",Toast.LENGTH_LONG).show();

                }

            }
        });


        return  rooteView;
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
