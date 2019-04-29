package com.example.reborn30.maptecs.ListaDeEscuelas;

import android.support.v7.app.AppCompatActivity;

import com.example.reborn30.maptecs.ModelInfo;
import com.example.reborn30.maptecs.R;

public class UABC extends AppCompatActivity implements IUni  {
    ModelInfo l;

    @Override
    public String TypoUniversidad() {
return  "Universidad tecnologica de mamadas";
    }

    @Override
    public String Descripcion() {
        String d = getString(R.string.DescripcionUABC);
  return d;
    }
}
