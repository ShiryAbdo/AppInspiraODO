package com.Inspira.odo.adaptors;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.Inspira.odo.R;
import com.Inspira.odo.buyerUi.NavigationDrawerBuyer;
import com.Inspira.odo.model.ObjectDrawerItem;
import com.Inspira.odo.sellerUi.NavigationDrawerSeler;

import java.util.ArrayList;

import static com.Inspira.odo.R.drawable.rounded_border;


/**
 * Created by Andy on 10-Dec-14.
 */
public class DrawerItemCustomAdapter extends BaseAdapter {

    Context mContext;
    int mLayoutResourceId;
    ArrayList<ObjectDrawerItem> mData = new ArrayList<>();
    Activity activiy ;

    public DrawerItemCustomAdapter(Activity activit,Context context, int layoutResourceId,  ArrayList<ObjectDrawerItem>  data) {
        this.mContext = context;
        this.mLayoutResourceId = layoutResourceId;
        this.mData = data;
        activiy =activit;
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
        TextView numbers = (TextView)listItem.findViewById(R.id.numbers);

         nameTextView.setText(objectDrawerItem.getName());

        if (activiy instanceof NavigationDrawerSeler && position==0){

            numbers.setText(" "+objectDrawerItem.getNumber()+" ");
            numbers.setBackgroundResource(R.drawable.round);



        }


        return listItem;
    }
}
