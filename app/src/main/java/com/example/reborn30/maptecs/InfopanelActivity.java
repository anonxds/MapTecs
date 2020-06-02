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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class InfopanelActivity extends BottomSheetDialogFragment {
    ImageView foto,Fimg;
    TextView titulo,_Ftitulo,Fdescripcion;
    DatabaseReference _DatabaseReference;
    String _titulo,desc,_img;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.infopanel,container,false);
        foto = v.findViewById(R.id.idimg);
        ViewPager vp = v.findViewById(R.id.imageView2);
        String ubicacion = getArguments().getString("id");
        titulo = v.findViewById(R.id.idinformation1);
        _Ftitulo = v.findViewById(R.id.lblevent1);
        Fdescripcion = v.findViewById(R.id.txtdesc2);
        Fimg = v.findViewById(R.id.imgevento1);
        _DatabaseReference = FirebaseDatabase.getInstance().getReference("tomas_aquino");


        switch (ubicacion){
            case  "100":
                titulo.setText("Edificio 100");
                ImageAdapter _cien = new ImageAdapter(getContext(),"100");
                vp.setAdapter(_cien);
                break;
            case "200":
                ImageAdapter _dos = new ImageAdapter(getContext(),"200");
                vp.setAdapter(_dos);
                titulo.setText("Edificio 200");
                break;
            case "300":
                ImageAdapter _tres = new ImageAdapter(getContext(),"300");
                vp.setAdapter(_tres);
                titulo.setText("Salones del 300 al 312 \n Baños");
                break;
            case "400":
                titulo.setText("Edificio 400");
                ImageAdapter _cuatro = new ImageAdapter(getContext(),"400");
                vp.setAdapter(_cuatro);
                break;
            case "500":
                titulo.setText("Edificio 500");
                ImageAdapter _cinco = new ImageAdapter(getContext(),"500");
                vp.setAdapter(_cinco);
                break;
            case "Q":
                ImageAdapter q = new ImageAdapter(getContext(),"Q");
                vp.setAdapter(q);
                titulo.setText("Edificio Q");
                break;
            case "600":
                titulo.setText("Edificio 600");
                ImageAdapter _seis = new ImageAdapter(getContext(),"600");
                vp.setAdapter(_seis);
                break;
            case "s_bio":

                break;
            case "cafeteria":
                titulo.setText("Cafeteria");
                ImageAdapter _cafe = new ImageAdapter(getContext(),"cafeteria");
                vp.setAdapter(_cafe);
                break;

                case "sc":
                    titulo.setText("Coordinacion \n de Sistemas Computaciones");
                    ImageAdapter _sc = new ImageAdapter(getContext(),"sc");
                    vp.setAdapter(_sc);
                break;
            case "ic":
                titulo.setText("Coordinacion \n de Industrial");

                ImageAdapter _ic = new ImageAdapter(getContext(),"ic");
                vp.setAdapter(_ic);
                break;
            case "bc":
                titulo.setText("Coordinacion \n de Bioquimica");
                ImageAdapter _bc = new ImageAdapter(getContext(),"bc");
                vp.setAdapter(_bc);
                break;
            case "mc":
                titulo.setText("Coordinacion \n de Metal Mecanica");
                ImageAdapter _mc = new ImageAdapter(getContext(),"mc");
                vp.setAdapter(_mc);
                break;
            case "bio":
                titulo.setText("Laboratorio de micrologia");
                ImageAdapter _bio = new ImageAdapter(getContext(),"bio");
                vp.setAdapter(_bio);
                break;
            case "teatro":
                titulo.setText("Teatro calafornix");
                ImageAdapter _teatro = new ImageAdapter(getContext(),"teatro");
                vp.setAdapter(_teatro);
                _DatabaseReference.child("calafornix_1").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        desc = String.valueOf(dataSnapshot.child("descripcion").getValue());
                        _titulo = String.valueOf(dataSnapshot.child("titulo").getValue());
                        _img = String.valueOf(dataSnapshot.child("img").getValue());
                        Fdescripcion.setText(desc);
                        _Ftitulo.setText(_titulo);
                        Picasso.get().load(_img).into(Fimg);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            case "metal":
                titulo.setText("Laboratorio de metalmecanica y mecanica");
                ImageAdapter _metal = new ImageAdapter(getContext(),"metal");
                vp.setAdapter(_metal);
                break;

            case  "bioblioteca":
                ImageAdapter adap = new ImageAdapter(getContext(),"bioblioteca");
                vp.setAdapter(adap);
                titulo.setText("Biblioteca, \n Computadoras, Cubiculos");

            case "misc_r":

                break;

            case "misc_m":

                break;

            case "misc_p":

                break;

            case "misc_au":

                titulo.setText("Audio visual, \n Asesorias");
                ImageAdapter _audio = new ImageAdapter(getContext(),"audio");
                vp.setAdapter(_audio);
                break;

            case "direccion":

                break;

            case "est_1":
                break;
            case "est_2":
                break;
            case "est_3":
                break;
            case "gym":
                break;
            case "lab_h":
                break;
            case "lab_q":
                break;


        }
        return v;
    }
}
