package com.example.reborn30.maptecs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BottomFilter extends BottomSheetDialogFragment {
    ListView simpleList;
    TextView text;
    private IbottomFilter mListener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.filtracion,container,false);
        // return super.onCreateView(inflater, container, savedInstanceState);
        ArrayList<String> data = new ArrayList<>();
        data.add("Todo");
        data.add("Salones");
        data.add("Coordinaciones");
        data.add("Oficina");
        simpleList = v.findViewById(R.id._filtracion);
        text = v.findViewById(R.id.idtext);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.filtracion,R.id.idtext, data);
        simpleList.setAdapter(arrayAdapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = (String)parent.getItemAtPosition(position);
                mListener.onClickList(value);
                dismiss();
            }
        });
        return v;
    }
    public  interface IbottomFilter{
        void onClickList(String item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mListener = (IbottomFilter) context;
        }catch (ClassCastException e){
            throw  new ClassCastException(context.toString() + "Must implement Bottomsheetlistener");
        }

    }
}
