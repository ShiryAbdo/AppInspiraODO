package com.Inspira.odo.adaptors;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.data.ApiClient;
import com.Inspira.odo.data.ApiInterface;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.model.MyRequest;
import com.Inspira.odo.model.ObjectDrawerItem;
import com.Inspira.odo.sellerData.RelatedOrder;
import com.Inspira.odo.sellerUi.NavigationDrawerSeler;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * Created by Andy on 10-Dec-14.
 */
public class DrawerItemCustomAdapter extends BaseAdapter {

    Context mContext;
    int mLayoutResourceId;
    ArrayList<ObjectDrawerItem> mData = new ArrayList<>();
    Activity activiy ;
    int x = 0 ;
    SharedPreferencesManager sharedPreferencesManager ;
    String phone ;

    public DrawerItemCustomAdapter(Activity activit,Context context, int layoutResourceId,  ArrayList<ObjectDrawerItem>  data) {
        this.mContext = context;
        this.mLayoutResourceId = layoutResourceId;
        this.mData = data;
        activiy =activit;
        sharedPreferencesManager= new SharedPreferencesManager(context);

        phone= sharedPreferencesManager.getUser_Phoe();

    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;
        ObjectDrawerItem objectDrawerItem = mData.get(position);

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(mLayoutResourceId, parent, false);
        if(position==2){
//            listItem.setBackgroundColor(Color.BLACK);
            listItem.setBackground( mContext.getResources().getDrawable(R.drawable.rounded_border));
        }else {
            listItem.setBackground( mContext.getResources().getDrawable(R.drawable.rounded_border));
        }


         TextView nameTextView = (TextView) listItem.findViewById(R.id.drawer_item_name);
        final TextView numbers = (TextView)listItem.findViewById(R.id.numbers);

         nameTextView.setText(objectDrawerItem.getName());

        if (activiy instanceof NavigationDrawerSeler && position==0){
            phone= sharedPreferencesManager.getUser_Phoe();
//"01009560622"
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);
            Call<ArrayList<RelatedOrder>> call = apiService.getRelatedOrder(new MyRequest(phone));
            call.enqueue(new Callback<ArrayList<RelatedOrder>>() {
                @Override
                public void onResponse(Call<ArrayList<RelatedOrder>> call, Response<ArrayList<RelatedOrder>> response) {
                    int responseCode = response.code();

                    if (responseCode == 200) {
                        ArrayList<RelatedOrder> bankJSONResponse = response.body();
                        if (!bankJSONResponse.isEmpty()) {
                            ArrayList<RelatedOrder> array  = bankJSONResponse;
                            x = array.size();
                            numbers.setText(" "+x+" ");
                            numbers.setBackgroundResource(R.drawable.round);


                            Toast.makeText(getApplicationContext(), "ResponseCode: " + responseCode, Toast.LENGTH_LONG).show();
//                        Log.d("CODE", "ResponseCode: " + responseCode);
                        }
                    }


                }

                @Override
                public void onFailure(Call<ArrayList<RelatedOrder>> call, Throwable t) {
                    // Log error here since request failed
                    Log.e(TAG, t.toString());
                }
            });




        }


        return listItem;
    }


    private int loadJSON() {

         ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<RelatedOrder>> call = apiService.getRelatedOrder(new MyRequest("01009560622"));
        call.enqueue(new Callback<ArrayList<RelatedOrder>>() {
            @Override
            public void onResponse(Call<ArrayList<RelatedOrder>> call, Response<ArrayList<RelatedOrder>> response) {
                int responseCode = response.code();

                if (responseCode == 200) {
                    ArrayList<RelatedOrder> bankJSONResponse = response.body();
                    if (!bankJSONResponse.isEmpty()) {
                        ArrayList<RelatedOrder> array  = bankJSONResponse;
                        x = array.size();

                        Toast.makeText(getApplicationContext(), "ResponseCode: " + responseCode, Toast.LENGTH_LONG).show();
//                        Log.d("CODE", "ResponseCode: " + responseCode);
                    }
                }


            }

            @Override
            public void onFailure(Call<ArrayList<RelatedOrder>> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
        return x;
    }
}
