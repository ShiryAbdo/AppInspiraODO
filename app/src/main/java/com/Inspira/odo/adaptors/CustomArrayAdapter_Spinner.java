package com.Inspira.odo.adaptors;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.Inspira.odo.R;

import java.util.ArrayList;

/**
 * Created by shirya on 31/10/17.
 */

public class CustomArrayAdapter_Spinner  extends ArrayAdapter<String> {

private final LayoutInflater mInflater;
private final Context mContext;
private final ArrayList<String> items;
private final int mResource;

public CustomArrayAdapter_Spinner(@NonNull Context context, @LayoutRes int resource,
                                  @NonNull ArrayList<String> objects) {
        super(context, resource, 0, objects);

        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        items = objects;
        }
@Override
public View getDropDownView(int position, @Nullable View convertView,
                            @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
        }

@Override
public @NonNull
    View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
        }

private View createItemView(int position, View convertView, ViewGroup parent){
final View view = mInflater.inflate(mResource, parent, false);

        TextView offTypeTv = (TextView) view.findViewById(R.id.max_discount_txt);
        TextView numOffersTv = (TextView) view.findViewById(R.id.num_offers_txt);


        offTypeTv.setText(items.get(position) );
        numOffersTv.setText(items.get(position));

        return view;
        }
}