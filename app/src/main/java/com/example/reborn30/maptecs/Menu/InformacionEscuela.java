package com.example.reborn30.maptecs.Menu;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.reborn30.maptecs.ImageAdapter;
import com.example.reborn30.maptecs.R;
import com.example.reborn30.maptecs.TECMapActivity;

public class InformacionEscuela extends AppCompatActivity {
    Context c = this;
    TextView unidad;
    TextView descripcion;
    Button btnmapa;

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
