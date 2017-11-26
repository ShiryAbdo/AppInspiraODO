package com.Inspira.odo.adaptors;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.buyerUi.detalisOfRequest;
import com.Inspira.odo.data.ApiClient;
import com.Inspira.odo.data.ApiInterface;
import com.Inspira.odo.data.Model.AddsFavourite;
import com.Inspira.odo.data.Model.BuyerAddsFavourite;
import com.Inspira.odo.data.Model.Response;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.model.SellerHomeData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

import static android.content.ContentValues.TAG;
import static com.facebook.FacebookSdk.getApplicationContext;


public class ResponseAdaptor  extends RecyclerView.Adapter<ResponseAdaptor.ViewHolder> implements Filterable {
    private ArrayList<Response> androidList;
    private Context context;
    private int lastPosition=-1;
    SharedPreferencesManager sharedPreferencesManager ;
    String orderId ;

    public ResponseAdaptor(Context c, ArrayList<Response> responses ,String orderId) {
        this.androidList = responses;
        this.context=c;
        sharedPreferencesManager= new SharedPreferencesManager(context);
        this.orderId=orderId ;
    }

    @Override
    public ResponseAdaptor.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout. response_itm, viewGroup, false);


        return new ResponseAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ResponseAdaptor.ViewHolder viewHolder, final int i) {
        viewHolder.price.setText(androidList.get(i).getPrice().toString());
        viewHolder.description_request.setText(androidList.get(i).getDescription());
        viewHolder.location.setText(androidList.get(i).getSellerData().getCompanyAddress());
        viewHolder.time.setText("time");
        viewHolder.Favorite_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferencesManager= new SharedPreferencesManager(context);
                addFairtes(sharedPreferencesManager.getUser_Phoe(),androidList.get(i).getSellerData().getSellerPhoneNumber(),orderId,viewHolder);

            }
        });

//        Double.parseDouble




        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidList.get(i).getSellerData().getSellerPhoneNumber();
                androidList.get(i).getSellerData().getCompanyOnMap().getLatitude();
                androidList.get(i).getSellerData().getCompanyOnMap().getLongitude();

                Intent intent = new Intent(context,detalisOfRequest.class);
                intent.putExtra("price",androidList.get(i).getPrice());
                intent.putExtra("SellerPhoneNumber", androidList.get(i).getSellerData().getSellerPhoneNumber());
                intent.putExtra("Latitude",androidList.get(i).getSellerData().getCompanyOnMap().getLatitude());
                intent.putExtra("Longitude",androidList.get(i).getSellerData().getCompanyOnMap().getLongitude());
                intent.putExtra("name",androidList.get(i).getDescription());
                intent.putExtra("CompanyAddress",androidList.get(i).getSellerData().getCompanyAddress());
                intent.putParcelableArrayListExtra("Response", (ArrayList<? extends Parcelable>) androidList );

                context.startActivity(intent);

            }
        });
        setAnimation(viewHolder.card, i);

    }



    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount()
    {
        return androidList.size();
    }

    @Override
    public Filter getFilter() {
//        Filter filter = new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                FilterResults filterResults = new FilterResults();
//                if (constraint != null) {
//                    List locations = findLocations(context, constraint.toString());
//
//                    // Assign the data to the FilterResults
//                    filterResults.values = locations;
//                    filterResults.count = locations.size();
//                }
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//                if (results != null && results.count > 0) {
//                    resultList = (List) results.values;
//                    notifyDataSetChanged();
//                } else {
//                    notifyDataSetChanged();
//                }
//            }
//        };
        return null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView description_request,price,location ,time ;
        private CardView card;
        private ImageView Favorite_image  ,image_itme_selle;
        public ViewHolder(View view) {
            super(view);
            card=(CardView)view.findViewById(R.id.card);
            description_request = (TextView)view.findViewById(R.id.description_request);
            price = (TextView)view.findViewById(R.id.price);
            location = (TextView)view.findViewById(R.id.location);
            time = (TextView)view.findViewById(R.id.time);
            Favorite_image = (ImageView) view.findViewById(R.id.Favorite_image);




        }
    }

    public String getGeocodeName(double latitude, double longitude) {
        Context context = getApplicationContext();

        Geocoder geocoder = new Geocoder( context);
        List<Address> addresses = null;
        String unknown ="Unknown Location";
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return unknown;
        }
        if ( addresses == null ||addresses.size() == 0) {
            return unknown;
        }
        Address address = addresses.get(0);



        String cn = address.getCountryName();

        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        String knownName = addresses.get(0).getFeatureName();
        String mainLocality = address.getSubAdminArea();

        return city + ", " + state+ ", " +country;
    }

    public void add(Response r) {
        androidList.add(r);
        notifyItemInserted(androidList.size() -1);
    }

    public void addAll(List<Response> moveResults) {
        for (Response result : moveResults) {
//            if (!result.getProducts().isEmpty())
            add(result);
        }
    }

    public void remove(Response r) {
        int position = androidList.indexOf(r);
        if (position > -1) {
            androidList.remove(position);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    private Response getItem(int position) {
        return androidList.get(position);
    }

    public  void addFairtes(String buyerPhoneNumber, String sellerPhoneNumber, String orderId , final  ViewHolder viewHolder){
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiService.addfavourite(new AddsFavourite( buyerPhoneNumber,sellerPhoneNumber,orderId));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody>call, retrofit2.Response<ResponseBody> response) {
                int responseCode = response.code();
                Log.d("CODE", "ResponseCode: " + responseCode);
                if(responseCode==200){
                    viewHolder.Favorite_image.setImageResource(R.drawable.staryellow);

                    Toast.makeText(context,"ResponseCode: " + responseCode,Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(context,"ResponseCode: " + responseCode,Toast.LENGTH_SHORT).show();
                    Toast.makeText(context," else",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

    }


}