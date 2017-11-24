package com.Inspira.odo.adaptors;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.buyerUi.RequestResponses;
import com.Inspira.odo.data.Model.MyOrder;
import com.Inspira.odo.data.Model.Response;
import com.Inspira.odo.helper.DateTimeHelper;
import com.Inspira.odo.mainLuncher.MyApplication;
import com.Inspira.odo.model.SellerHomeData;
import com.github.thunder413.datetimeutils.DateTimeUtils;
import com.google.gson.Gson;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;


public class MyRequestAdapter  extends RecyclerView.Adapter<MyRequestAdapter.ViewHolder> {
    private ArrayList<MyOrder> androidList;
    private Context context;
    private int lastPosition=-1;
    SimpleDateFormat formater ;
    DateTimeHelper dateTimeHelper ;
    DateTimeUtils obj ;

    MyApplication myApplication ;

    public MyRequestAdapter(ArrayList<MyOrder> android, Context c) {
        this.androidList = android;
        this.context=c;
        this.obj = new DateTimeUtils();
        this.formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.dateTimeHelper= new DateTimeHelper(context);
        myApplication= new MyApplication();

    }

    @Override
    public MyRequestAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout. seler_home_item, viewGroup, false);


        return new MyRequestAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyRequestAdapter.ViewHolder viewHolder, final int i) {
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
        try {
            d = dfDate.parse("01/02/2012 ");
            d1 = dfDate.parse(dfDate.format(cal.getTime()));//Returns 15/10/2012
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
         String resul= dateTimeHelper.substractDates(d1,d ,dfDate);

         viewHolder.time_of_post.setText(resul);








//        if(androidList.get(i).isFavorite()==false){
//            viewHolder.Favorite_image.setImageResource(R.drawable.star);
//
//        }else {
//            viewHolder.Favorite_image.setImageResource(R.drawable.staryellow);
//        }




        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),androidList.get(i).getResponses().get(i).getPrice(),Toast.LENGTH_LONG).show();
//                if(!androidList.get(i).getResponses().isEmpty()){
//                    responses.addAll(androidList.get(i).getResponses());
//                }
//                Toast.makeText(getApplicationContext(),responses.get(i).getPrice(),Toast.LENGTH_LONG).show();
                ArrayList<Response> responses = new ArrayList<Response>();
                        responses.addAll(androidList.get(i).getResponses()) ;
                myApplication.setResponses(responses);
                Intent intent = new Intent(context, RequestResponses.class);
                Gson gson = new Gson();
                String jsonString = gson.toJson(responses);
                SharedPreferences sp = context.getSharedPreferences("KEY", Context.MODE_PRIVATE);

//Save to SharedPreferences
                sp.edit().putString("KEY", jsonString).commit();

//                Bundle intent1 = new Bundle();

//                        intent.putParcelableArrayListExtra("cars", responses);
                Toast.makeText(getApplicationContext(), responses.size()+"",Toast.LENGTH_LONG).show();

//                 intent.putParcelableArrayListExtra("responses", (ArrayList<? extends Parcelable>) responses);
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

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Name_request,name_car,Type_car ,year_car ,model_car ,color_car ,time_of_post;
        private CardView card;
        private ImageView Favorite_image  ,image_itme_selle;
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



        }
    }

}