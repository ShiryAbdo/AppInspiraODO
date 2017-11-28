package com.Inspira.odo.adaptors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.Inspira.odo.R;
import com.Inspira.odo.adds.AdViewClass;
import com.Inspira.odo.data.ApiClient;
import com.Inspira.odo.data.ApiInterface;
import com.Inspira.odo.data.Model.AddsFavourite;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.helper.DateTimeHelper;
import com.Inspira.odo.mainLuncher.MyApplication;
import com.Inspira.odo.sellerData.RelatedOrder;
import com.Inspira.odo.sellerUi.RespondtoaReques;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

import static android.content.ContentValues.TAG;


public class DataSellerHomeAdaptor  extends RecyclerView.Adapter<DataSellerHomeAdaptor.ViewHolder> {
    private ArrayList<RelatedOrder> androidList;
    private Context context;
    private int lastPosition=-1;
    DateTimeHelper dateTimeHelper ;
    Activity activity ;
    SharedPreferencesManager sharedPreferencesManager ;
    AdViewClass adViewClass ;
    MyApplication myApplication ;
    AdView adView ;

    public DataSellerHomeAdaptor(ArrayList<RelatedOrder> android, Context c , Activity activity) {
        this.androidList = android;
        this.context=c;
        this.activity =activity ;
        this.dateTimeHelper= new DateTimeHelper(context);
        myApplication= new MyApplication();
        sharedPreferencesManager= new SharedPreferencesManager(context);
        adViewClass =new AdViewClass(activity, adView ,c);

    }

    @Override public DataSellerHomeAdaptor.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = null;

        if ( i == 2)
        {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout. activity_main5, viewGroup, false);
        }else{
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout. seler_home_item, viewGroup, false);

        }



        return new DataSellerHomeAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataSellerHomeAdaptor.ViewHolder viewHolder, final int i) {
        if(    viewHolder.getAdapterPosition()== 2){

//            adViewClass =new AdViewClass(activity, adView ,context);
            adView = (AdView)activity.findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder()
                    .setRequestAgent("android_studio:ad_template").build();
            viewHolder.adView.loadAd(adRequest);
             // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
            Toast.makeText(context, "loaded", Toast.LENGTH_LONG).show();
         }else {


            viewHolder.Name_request.setText(androidList.get(i).getOrder().getPart());
            viewHolder.name_car.setText(androidList.get(i).getCarDetails().getCarType());
            viewHolder.Type_car.setText(androidList.get(i).getCarDetails().getCarModel());
            viewHolder.year_car.setText(androidList.get(i).getCarDetails().getCarYear());
            viewHolder.model_car.setText(androidList.get(i).getOrder().getPartType());
            viewHolder.color_car.setText(androidList.get(i).getOrder().getColor());


            SimpleDateFormat dfDate  = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date d = null;
            java.util.Date d1 = null;
            Calendar cal = Calendar.getInstance();
//        you need to split date resived
            String dat= androidList.get(i).getDate();
            String CurrentString = dat;
            String[] separated = CurrentString.split("T");
            String dateString=  separated[0];
            String dataTime = separated[1];

            String[] seped = dateString.split("-");
            String time = seped[2]+"/"+seped[1]+"/"+seped[0];
            try {
                Date date=dfDate.parse(time);
                d = dfDate.parse(time);
                d1 = dfDate.parse(dfDate.format(cal.getTime()));//Returns 15/10/2012
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
            String resul= dateTimeHelper.substractDates(d1,d ,dfDate);

            viewHolder.time_of_post.setText(resul);
            viewHolder.Favorite_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addFairtes(androidList.get(i).getBuyerPhoneNumber(),sharedPreferencesManager.getUser_Phoe(),androidList.get(i).getId(),viewHolder);
                }
            });

            viewHolder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                    Toast.makeText(context,"this",Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(context, RespondtoaReques.class);
                    intent.putExtra("part",androidList.get(i).getOrder().getPart());
                    intent.putExtra("buyerPhoneNumber",androidList.get(i).getBuyerPhoneNumber());
                    intent.putExtra("orderID",androidList.get(i).getId());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    context.startActivity(intent);


                }
            });




            setAnimation(viewHolder.card, i);
         }

    }

    @Override
    public int getItemViewType(int position)
    {
        if(position==2)
            return 2;
        return position;
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
    public int getItemCount() {
        return androidList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Name_request,name_car,Type_car ,year_car ,model_car ,color_car ,time_of_post;
        private CardView card;
        private ImageView Favorite_image  ,image_itme_selle;
        AdView adView ;
        public ViewHolder(View view) {
            super(view);
            card=(CardView)view.findViewById(R.id.card);
            Name_request = (TextView)view.findViewById(R.id.Name_request);
            name_car = (TextView)view.findViewById(R.id.name_car);
            Type_car = (TextView)view.findViewById(R.id.Type_car);
            year_car = (TextView)view.findViewById(R.id.year_car);
            model_car = (TextView)view.findViewById(R.id.model_car);
            color_car = (TextView)view.findViewById(R.id.color_car);
            time_of_post = (TextView)view.findViewById(R.id.time_of_post);
            Favorite_image=(ImageView)view.findViewById(R.id.Favorite_image);
            image_itme_selle=(ImageView)view.findViewById(R.id.image_itme_selle);
            adView = (AdView) view.findViewById(R.id.adView);




        }
    }
    public void add(RelatedOrder r) {
        androidList.add(r);
        notifyItemInserted(androidList.size() -1);
    }

    public void addAll(List<RelatedOrder> moveResults) {
        for (RelatedOrder result : moveResults) {
//            if (!result.getProducts().isEmpty())
            add(result);
        }
    }

    public void remove(RelatedOrder r) {
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

    private RelatedOrder getItem(int position) {
        return androidList.get(position);
    }
    public  void addFairtes(String buyerPhoneNumber, String sellerPhoneNumber, String orderId , final  ViewHolder viewHolder){
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiService.addfavouriteSeller(new AddsFavourite( buyerPhoneNumber,sellerPhoneNumber,orderId));
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