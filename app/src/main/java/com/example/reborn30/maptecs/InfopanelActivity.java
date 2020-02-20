package com.example.reborn30.maptecs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class InfopanelActivity extends BottomSheetDialogFragment {
    ImageView foto;
    TextView description;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.infopanel,container,false);
        foto = v.findViewById(R.id.idimg);
        ViewPager vp = v.findViewById(R.id.imageView2);
        String ubicacion = getArguments().getString("id");
        description = v.findViewById(R.id.idinformation1);
        vp.setVisibility(View.GONE);

        switch (ubicacion){
            case  "100":
              //  foto.setImageResource(R.drawable.);
                description.setText("Salones del 101 al 110 \n Baños");
                break;
            case "200":
                description.setText("Salones del 200 al 205 \n Aulamagna,Psiquiatra...");
                break;
            case "300":
                description.setText("Salones del 300 al 312 \n Baños");
                break;
            case "400":
                description.setText("Salones del 400 al 410");
                break;
            case "500":
                foto.setImageResource(R.drawable.iedificio500);
                description.setText("Salones del 400 al 410");
                break;
            case "Q":
                foto.setVisibility(View.GONE);
                vp.setVisibility(View.VISIBLE);
                ImageAdapter adp = new ImageAdapter(getContext(),"Q");
                vp.setAdapter(adp);
               // foto.setImageResource(R.drawable.iedificioq);
                description.setText("Salones del q1 al q3 \n Mesas de ping-pong");
                break;
            case "600":
                foto.setImageResource(R.drawable.i600);
                description.setText("Este es un salon de los 600");
                break;
            case "cafeteria":
                foto.setImageResource(R.drawable.icafeteria);
                break;

                case "sc":

                break;
            case "ic":

                break;
            case "bc":
                foto.setImageResource(R.drawable.ibioquimicac);

                break;
            case "mc":
                break;
            case "bio":
                foto.setImageResource(R.drawable.ilabbio);
                description.setText("Laboratorio de micrologia");
                break;
            case "teatro":
                foto.setImageResource(R.drawable.icalafornix);
                description.setText("Teatro calafornix");
                break;
            case "metal":
                foto.setImageResource(R.drawable.ilabmetal);
                description.setText("Laboratorio de metalmecanica y mecanica");
                break;
            case "audio":
                foto.setImageResource(R.drawable.iaudio);
                description.setText("Audio visual, \n Asesorias");
                break;
            case  "bioblioteca":
                foto.setVisibility(View.GONE);
                vp.setVisibility(View.VISIBLE);
                ImageAdapter adap = new ImageAdapter(getContext(),"bioblioteca");
                vp.setAdapter(adap);
                description.setText("Biblioteca, \n Computadoras, Cubiculos");


        }
        return v;
    }
}
