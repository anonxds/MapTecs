package com.example.reborn30.maptecs.ListaDeEscuelas;

import android.support.v7.app.AppCompatActivity;

import com.example.reborn30.maptecs.R;

public class TEC extends AppCompatActivity implements IUni {
    @Override
    public String TypoUniversidad() {
     return "Instituto Tecnologico de Tijuana";

    }

    @Override
    public String Descripcion() {

      return  getString(R.string.DescripcionITT);

    }
}
