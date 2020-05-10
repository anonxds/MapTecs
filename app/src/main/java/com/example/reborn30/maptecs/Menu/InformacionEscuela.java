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

import java.util.ArrayList;
import java.util.List;

public class InformacionEscuela extends AppCompatActivity {
    Context c = this;
    TextView unidad,evento1,evento2;
    TextView descripcion;
    Button btnmapa;
    FirebaseDatabase _FirebaseDatabase;
    DatabaseReference _DatabaseReference,_ImgDatabse;
    String Valor,Valor2;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_escuela);

        final String nombre = getIntent().getStringExtra("Nombre");
        String des = getIntent().getStringExtra("descripcion");
        String unidadS;
        if(nombre.equals("Tomas")) {
            unidadS = "Unidad " + nombre + " aquino";
        } else {
            unidadS = "Unidad " + nombre;
        }
        unidad = findViewById(R.id.lblunidad);
        descripcion = findViewById(R.id.lbldes);
        btnmapa = findViewById(R.id.mapa);
        evento1 = findViewById(R.id.lblevent1);
        evento2 = findViewById(R.id.lblevent2);
        img = findViewById(R.id.imgevento1);

        //checar si hay conexion a firebase
        _DatabaseReference = FirebaseDatabase.getInstance().getReference("aquino");
        _ImgDatabse = FirebaseDatabase.getInstance().getReference("event");
        _DatabaseReference.child("/evento1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Valor = String.valueOf( dataSnapshot.getValue());
                evento1.setText(Valor);
                Log.d("key",Valor);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        _DatabaseReference.child("/evento2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Valor2 = String.valueOf( dataSnapshot.getValue());
                Log.d("key2",Valor2);
                evento2.setText(Valor2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        _ImgDatabse.child("/-M26cgHl7OV8nnKq57w0/img").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Valor2 = String.valueOf( dataSnapshot.getValue());

                Picasso.get().load(Valor2).into(img);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //

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
