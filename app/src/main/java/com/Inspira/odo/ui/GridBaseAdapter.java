package com.Inspira.odo.ui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.Inspira.odo.R;

import java.security.PublicKey;
import java.util.ArrayList;

/**
 * Created by Rp on 3/22/2016.
 */
public class GridBaseAdapter extends BaseAdapter {

    Context context;
    ArrayList<Beanclass> beans;

    public GridBaseAdapter(Context context, ArrayList<Beanclass> beans) {
        this.context = context;
        this.beans = beans;
    }


    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public Object getItem(int position) {
        return beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            viewHolder = new ViewHolder();

            convertView = layoutInflater.inflate(R.layout.gridview, null);
            viewHolder.image= (ImageView)convertView.findViewById(R.id.image);


            convertView.setTag(viewHolder);


        } else {

            viewHolder = (ViewHolder)convertView.getTag();

        }

        Beanclass beans = (Beanclass)getItem(position);

        viewHolder.image.setImageResource(beans.getImage());


            return convertView;
    }


    private class ViewHolder {
        ImageView image;
    }
}