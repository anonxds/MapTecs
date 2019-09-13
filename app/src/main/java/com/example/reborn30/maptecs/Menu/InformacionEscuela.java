package com.example.reborn30.maptecs.Menu;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.reborn30.maptecs.ImageAdapter;
import com.example.reborn30.maptecs.R;
import com.example.reborn30.maptecs.TECMapActivity;

public class InformacionEscuela extends AppCompatActivity {
Context c = this;
TextView descripcion;
Button btnmapa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_escuela);
        btnmapa = findViewById(R.id.mapa);
       final String nombre = getIntent().getStringExtra("Nombre");
        ViewPager vp = findViewById(R.id.imageView);
        ImageAdapter adp = new ImageAdapter(c,nombre);
        vp.setAdapter(adp);
        String des = getIntent().getStringExtra("descripcion");
        descripcion = findViewById(R.id.lbldes);
        descripcion.setText(des);

        btnmapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), TECMapActivity.class);
                i.putExtra("Nombre",nombre);
                startActivity(i);
            }
        });
    }
}
