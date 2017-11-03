package com.Inspira.odo.adaptors;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Inspira.odo.R;
import com.Inspira.odo.model.SellerHomeData;
import com.Inspira.odo.sellerUi.DialogeSellerData;

import java.util.ArrayList;

import static android.R.attr.data;
import static android.R.attr.theme;

/**
 * Created by shirya on 03/11/17.
 */

public class DataSellerHomeAdaptor  extends RecyclerView.Adapter<DataSellerHomeAdaptor.ViewHolder> {
    private ArrayList<SellerHomeData> androidList;
    private Context context;
    private int lastPosition=-1;
    Activity activity ;

    public DataSellerHomeAdaptor(ArrayList<SellerHomeData> android, Context c , Activity activity) {
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
        viewHolder.Name_request.setText(androidList.get(i).getNameRequest());
        viewHolder.name_car.setText(androidList.get(i).getNameCar());
        viewHolder.Type_car.setText(androidList.get(i).getTypeCare());
        viewHolder.year_car.setText(androidList.get(i).getYearCar());
        viewHolder.model_car.setText(androidList.get(i).getModleCare());
        viewHolder.color_car.setText(androidList.get(i).getColorcar());
        viewHolder.time_of_post.setText(androidList.get(i).getTimePost());
//        if(androidList.get(i).isFavorite()==false){
//            viewHolder.Favorite_image.setImageResource(R.drawable.star);
//
//        }else {
//            viewHolder.Favorite_image.setImageResource(R.drawable.staryellow);
//        }


//        viewHolder.card.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                final Dialog okdialog = new Dialog(context.getApplicationContext(), R.style.custom_dialog_theme);
//                okdialog.setContentView(R.layout.dialog_seller_home_data);
//                Button OK_d =(Button)okdialog.findViewById(R.id.ok);
//                Spinner SpinnerCarType = (Spinner)okdialog.findViewById(R.id.SpinnerCarType);
//                Spinner your_car_model = (Spinner)okdialog.findViewById(R.id.your_car_model);
//                Spinner your_car_year =(Spinner)okdialog.findViewById(R.id.your_car_year);
//                ArrayList<String>categories ;
//                CustomArrayAdapter_Spinner  myAdaptor ;
//
//                // Spinner Drop down elements
//                categories= new ArrayList<>();
//                categories.add("Automobile");
//                categories.add("Business Services");
//                categories.add("Computers");
//                categories.add("Education");
//                categories.add("Personal");
//                categories.add("Travel");
//
//                myAdaptor = new CustomArrayAdapter_Spinner(context.getApplicationContext(),
//                        R.layout.customspinneritem, categories);
//                SpinnerCarType.setAdapter(myAdaptor);
//                your_car_model.setAdapter(myAdaptor);
//                your_car_year.setAdapter(myAdaptor);
//                OK_d.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        okdialog.dismiss();
//
//                    }
//                });okdialog.show();
//
//                return false;
//            }
//        });

        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Dialog okdialog = new Dialog(activity, R.style.custom_dialog_theme);
                okdialog.setContentView(R.layout.dialog_seller_home_data);
                Button OK_d =(Button)okdialog.findViewById(R.id.ok);
                Spinner SpinnerCarType = (Spinner)okdialog.findViewById(R.id.SpinnerCarType);
                Spinner your_car_model = (Spinner)okdialog.findViewById(R.id.your_car_model);
                Spinner your_car_year =(Spinner)okdialog.findViewById(R.id.your_car_year);
                ArrayList<String>categories ;
                CustomArrayAdapter_Spinner  myAdaptor ;

                // Spinner Drop down elements
                categories= new ArrayList<>();
                categories.add("Automobile");
                categories.add("Business Services");
                categories.add("Computers");
                categories.add("Education");
                categories.add("Personal");
                categories.add("Travel");

                myAdaptor = new CustomArrayAdapter_Spinner(context.getApplicationContext(),
                        R.layout.customspinneritem, categories);
                SpinnerCarType.setAdapter(myAdaptor);
                your_car_model.setAdapter(myAdaptor);
                your_car_year.setAdapter(myAdaptor);
                OK_d.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        okdialog.dismiss();

                    }
                });okdialog.show();
//
//                Toast.makeText(context,"this",Toast.LENGTH_SHORT).show();
//                Intent intent= new Intent(context, DialogeSellerData.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
//                context.startActivity(intent);


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