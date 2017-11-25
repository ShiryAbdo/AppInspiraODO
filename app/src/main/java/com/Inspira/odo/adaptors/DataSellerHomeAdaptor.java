package com.Inspira.odo.adaptors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.Inspira.odo.sellerData.RelatedOrder;
import com.Inspira.odo.sellerUi.RespondtoaReques;

import java.util.ArrayList;

/**
 * Created by shirya on 03/11/17.
 */

public class DataSellerHomeAdaptor  extends RecyclerView.Adapter<DataSellerHomeAdaptor.ViewHolder> {
    private ArrayList<RelatedOrder> androidList;
    private Context context;
    private int lastPosition=-1;
    Activity activity ;

    public DataSellerHomeAdaptor(ArrayList<RelatedOrder> android, Context c , Activity activity) {
        this.androidList = android;
        this.context=c;
        this.activity =activity ;
    }

    @Override
    public DataSellerHomeAdaptor.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout. seler_home_item, viewGroup, false);


        return new DataSellerHomeAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataSellerHomeAdaptor.ViewHolder viewHolder, final int i) {
        viewHolder.Name_request.setText(androidList.get(i).getCarDetails().getCarType());
        viewHolder.name_car.setText(androidList.get(i).getOrderPartType());
        viewHolder.Type_car.setText(androidList.get(i).getCarDetails().getCarType());
        viewHolder.year_car.setText(androidList.get(i).getCarDetails().getCarYear());
        viewHolder.model_car.setText(androidList.get(i).getCarDetails().getCarModel());
        viewHolder.color_car.setText(androidList.get(i).getOrder().getColor());
        viewHolder.time_of_post.setText("time");

        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Toast.makeText(context,"this",Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(context, RespondtoaReques.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
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
    public int getItemCount() {
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