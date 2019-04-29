package com.example.reborn30.maptecs;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<DataItem> {
    Context context;
    int layoutRID;
    List<DataItem> data = null;

    public CustomAdapter(Context context, int resource,List<DataItem> objects) {
        super(context, resource,objects);
     this.layoutRID = resource;
     this.context = context;
     this.data = objects;
    }


    static class Dataholder{
        ImageView ivUNI;
        TextView txtUNI;
    }


    @NonNull
    @Override
    public View getView (int position, View convertView, ViewGroup parent){
        Dataholder holder = null;
        if(convertView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(layoutRID, parent,false);
            holder = new Dataholder();
            holder.ivUNI = (ImageView)convertView.findViewById(R.id.imgUNI);
            holder.txtUNI = (TextView)convertView.findViewById(R.id.txtdes);
            convertView.setTag(holder);
        }
        else {
            holder =  (Dataholder)convertView.getTag();
        }
        DataItem dataItem = data.get(position);
        holder.txtUNI.setText(dataItem.Uniname);
        holder.ivUNI.setImageResource(dataItem.redIdtThumb);
        return  convertView;
    }

}
