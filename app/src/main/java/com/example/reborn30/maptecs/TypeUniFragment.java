package com.example.reborn30.maptecs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        // Inflate the layout for this fragment
        String s = getArguments().getString("Nombre");
        String d = getArguments().getString("Descripcion");
        TextView t2 = v.findViewById(R.id.textView2);
        TextView t = v.findViewById(R.id.txtNombreUni);
        t2.setText(d);
        t.setText(s);



        return v;
    }

}
