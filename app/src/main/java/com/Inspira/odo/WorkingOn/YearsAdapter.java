package com.Inspira.odo.WorkingOn;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import com.Inspira.odo.R;
import java.util.ArrayList;



public class YearsAdapter extends RecyclerView.Adapter<YearsAdapter.ViewHolder>{
Context context;
    ArrayList<String> list;
    RecyclerViewClickListener itemlist;
    String parent;
    String header;
    public YearsAdapter(Context context, String parent, String header,ArrayList<String> list, RecyclerViewClickListener itemlist){
        this.context=context;
        this.parent=parent;
        this.header=header;
        this.itemlist=itemlist;
        this.list=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.car_year_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.year.setText(list.get(position));
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CheckBox year;
        public ViewHolder(View itemView) {
            super(itemView);
            year=(CheckBox)itemView.findViewById(R.id.rowThirdText);
            year.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.rowThirdText) {
                itemlist.recyclerViewListClicked(year.isChecked(),parent,header,list.get(getAdapterPosition()));
            }
        }
    }
}

