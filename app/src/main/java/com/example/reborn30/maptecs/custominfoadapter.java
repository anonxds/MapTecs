package com.example.reborn30.maptecs;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class custominfoadapter  implements GoogleMap.InfoWindowAdapter {

//    public custominfoadapter(Context context) {
//        mWindow = LayoutInflater.from(context).inflate(R.layout.custom_info_window,null);
//        mContext = context;
//    }
//    private void redoWindowText(Marker marker,View view){
//        String title = marker.getTitle();
//        TextView tvTitle = (TextView) view.findViewById(R.id.name);
//        if(!title.equals("")){
//            tvTitle.setText(title);
//        }
//        String snippet = marker.getSnippet();
//        TextView tvSnipet = (TextView) view.findViewById(R.id.details);
//        if(!snippet.equals("")){
//            tvSnipet.setText(snippet);
//        }
//    }


    private Context context;

    public custominfoadapter(Context ctx){
        context = ctx;
    }


    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = ((Activity)context).getLayoutInflater()
                .inflate(R.layout.custom_info_window, null);

        TextView name_tv = view.findViewById(R.id.addressTxt);
     //   TextView details_tv = view.findViewById(R.id.details);
        ImageView img = view.findViewById(R.id.imageView);

        TextView hotel_tv = view.findViewById(R.id.addressTxt);
   //     TextView food_tv = view.findViewById(R.id.food);
     //   TextView transport_tv = view.findViewById(R.id.transport);

        name_tv.setText(marker.getTitle());
    //    details_tv.setText(marker.getSnippet());

        InfoWindowData infoWindowData = (InfoWindowData) marker.getTag();

        int imageId = context.getResources().getIdentifier(infoWindowData.getImage().toLowerCase(),
                "drawable", context.getPackageName());
        img.setImageResource(imageId);

        hotel_tv.setText(infoWindowData.getHotel());
      //  food_tv.setText(infoWindowData.getFood());
      //  transport_tv.setText(infoWindowData.getTransport());

        return view;
    }
}


