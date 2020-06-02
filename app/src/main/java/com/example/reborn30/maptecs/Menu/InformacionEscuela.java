package com.example.reborn30.maptecs.Menu;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reborn30.maptecs.ImageAdapter;
import com.example.reborn30.maptecs.R;
import com.example.reborn30.maptecs.TECMapActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class InformacionEscuela extends AppCompatActivity {
    Context c = this;
    TextView unidad,evento1,evento2,txtdesc1;
    TextView descripcion;
    Button btnmapa;
    FirebaseDatabase _FirebaseDatabase;
    DatabaseReference _DatabaseReference;
    String Valor,Valor2,titulo,desc,_img;
    ImageView img,img2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_escuela);

        final String nombre = getIntent().getStringExtra("Nombre");
        final String des = getIntent().getStringExtra("descripcion");
        String unidadS;
        if(nombre.equals("Tomas")) {
            unidadS = "Unidad " + nombre + " aquino";
        } else {
            unidadS = "Unidad " + nombre;
        }
        unidad = findViewById(R.id.lblunidad);
        descripcion = findViewById(R.id.txtdesc2);
        btnmapa = findViewById(R.id.mapa);
        evento1 = findViewById(R.id.lblevent1);
        evento2 = findViewById(R.id.lblevent2);
        txtdesc1 = findViewById(R.id.txtdescrip1);

        img = findViewById(R.id.imgevento1);
        img2 = findViewById(R.id.imgevento2);

        //checar si hay conexion a firebase
        _DatabaseReference = FirebaseDatabase.getInstance().getReference("tomas_aquino");
        _DatabaseReference.child("/menu_principal_1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                desc = String.valueOf(dataSnapshot.child("descripcion").getValue());
                titulo = String.valueOf(dataSnapshot.child("titulo").getValue());
                _img = String.valueOf(dataSnapshot.child("img").getValue());
                txtdesc1.setText(desc);
                evento1.setText(titulo);
                Picasso.get().load(_img).into(img);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        _DatabaseReference.child("/menu_principal_2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                desc = String.valueOf(dataSnapshot.child("descripcion").getValue());
                titulo = String.valueOf(dataSnapshot.child("titulo").getValue());
                _img = String.valueOf(dataSnapshot.child("img").getValue());
                evento2.setText(titulo);
                descripcion.setText(desc);
                Picasso.get().load(_img).into(img2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ViewPager vp = findViewById(R.id.imageView2);
        ImageAdapter adp = new ImageAdapter(c, nombre);
        vp.setAdapter(adp);
        unidad.setText(unidadS);
        descripcion.setText(des);
        Log.i("nombre: ", nombre);
        btnmapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), TECMapActivity.class);
                i.putExtra("Nombre", nombre);
                startActivity(i);
            }
        });
    }
}
