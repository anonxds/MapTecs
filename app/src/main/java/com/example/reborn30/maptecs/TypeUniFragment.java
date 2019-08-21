package com.example.reborn30.maptecs;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TypeUniFragment extends Fragment {


    public TypeUniFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_type_uni, container, false);
        ViewPager viewPager = v.findViewById(R.id.imageView);
        ImageAdapter adapter = new ImageAdapter(v.getContext());
        viewPager.setAdapter(adapter);
        Button map;
        map = v.findViewById(R.id.mapa);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(),TECMapActivity.class);
                startActivity(i);
            }
        });


        return v;
    }

}
