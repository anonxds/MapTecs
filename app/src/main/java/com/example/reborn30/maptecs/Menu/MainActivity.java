package com.example.reborn30.maptecs.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.reborn30.maptecs.ListaDeEscuelas.IUni;
import com.example.reborn30.maptecs.ListaDeEscuelas.TEC;
import com.example.reborn30.maptecs.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    IUni universidad;
    public void tomas(View view){
        universidad = new TEC();
        Intent i = new Intent(getBaseContext(),InformacionEscuela.class);
        i.putExtra("descripcion",universidad.tomas());
        i.putExtra("Nombre","Tomas");
        startActivity(i);
    }
    public void otay(View view){
        universidad = new TEC();
        Intent i = new Intent(getBaseContext(),InformacionEscuela.class);
        i.putExtra("descripcion",universidad.otay());
        i.putExtra("Nombre","Otay");
        startActivity(i);
    }

}
