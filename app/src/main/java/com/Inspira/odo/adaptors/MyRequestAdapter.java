package com.Inspira.odo.adaptors;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
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
import com.Inspira.odo.buyerUi.NavigationDrawerBuyer;
import com.Inspira.odo.buyerUi.RequestResponses;
import com.Inspira.odo.data.ApiClient;
import com.Inspira.odo.data.ApiInterface;
import com.Inspira.odo.data.Model.BuyerAddsFavourite;
import com.Inspira.odo.data.Model.BuyerRegistration;
import com.Inspira.odo.database.MyOrder;
import com.Inspira.odo.data.Model.Response;
import com.Inspira.odo.database.SharedPreferencesManager;
import com.Inspira.odo.helper.DateTimeHelper;
import com.Inspira.odo.mainLuncher.MyApplication;
import com.github.thunder413.datetimeutils.DateTimeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

import static android.content.ContentValues.TAG;
import static com.facebook.FacebookSdk.getApplicationContext;


public class MyRequestAdapter  extends RecyclerView.Adapter<MyRequestAdapter.ViewHolder> {
    private ArrayList<MyOrder> androidList;
    private Context context;
    private int lastPosition=-1;
    SimpleDateFormat formater ;
    DateTimeHelper dateTimeHelper ;
    DateTimeUtils obj ;
    SharedPreferencesManager sharedPreferencesManager ;
    MyApplication myApplication ;

    public MyRequestAdapter(ArrayList<MyOrder> android, Context c) {
        this.androidList = android;
        this.context=c;
        this.obj = new DateTimeUtils();
        this.formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.dateTimeHelper= new DateTimeHelper(context);
        myApplication= new MyApplication();
        sharedPreferencesManager= new SharedPreferencesManager(context);

    }

    @Override
    public MyRequestAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout. seler_home_item, viewGroup, false);


        return new MyRequestAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyRequestAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.Name_request.setText(androidList.get(i).getOrder().getPart());
        viewHolder.name_car.setText(androidList.get(i).getCarDetails().getCarModel());
        viewHolder.Type_car.setText(androidList.get(i).getCarDetails().getCarType());
        viewHolder.year_car.setText(androidList.get(i).getCarDetails().getCarYear());
        viewHolder.model_car.setText(androidList.get(i).getCarDetails().getCarModel());
        viewHolder.color_car.setText(androidList.get(i).getOrder().getColor());


//        responses =androidList.get(i).getResponses();
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
   viewHolder.Favorite_image.setVisibility(View.GONE);

        setAnimation(viewHolder.card, i);







//        if(androidList.get(i).isFavorite()==false){
//            viewHolder.Favorite_image.setImageResource(R.drawable.star);
//
//        }else {
//            viewHolder.Favorite_image.setImageResource(R.drawable.staryellow);
//        }





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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView Name_request, name_car, Type_car, year_car, model_car, color_car, time_of_post;
        private CardView card;
        private ImageView Favorite_image, image_itme_selle;

        public ViewHolder(View view) {
            super(view);
            card = (CardView) view.findViewById(R.id.card);
            card.setOnClickListener(this);
            Name_request = (TextView) view.findViewById(R.id.Name_request);
            name_car = (TextView) view.findViewById(R.id.name_car);
            Type_car = (TextView) view.findViewById(R.id.Type_car);
            year_car = (TextView) view.findViewById(R.id.year_car);
            model_car = (TextView) view.findViewById(R.id.model_car);
            color_car = (TextView) view.findViewById(R.id.color_car);
            time_of_post = (TextView) view.findViewById(R.id.time_of_post);
            Favorite_image = (ImageView) view.findViewById(R.id.Favorite_image);
            image_itme_selle = (ImageView) view.findViewById(R.id.image_itme_selle);


        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            ArrayList<Response> responses = new ArrayList<>();
            responses.addAll(androidList.get(position).getResponses());
            myApplication.setResponses(responses);
            Intent intent = new Intent(context, RequestResponses.class);
            intent.putParcelableArrayListExtra("Response", (ArrayList<? extends Parcelable>)  responses);
            intent.putExtra("orderId",androidList.get(position).getId());
//            intent.putExtra("Response", responses);
            Toast.makeText(getApplicationContext(), responses.size() + "", Toast.LENGTH_LONG).show();
            context.startActivity(intent);
        }



        }


    }
