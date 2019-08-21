package com.example.reborn30.maptecs.Menu;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.reborn30.maptecs.ListaDeEscuelas.IUni;
import com.example.reborn30.maptecs.ModelInfo;
import com.example.reborn30.maptecs.R;
import com.example.reborn30.maptecs.TypeUniFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


List<DataItem> lsData;
IUni uni;
ListView ls;
Button vermapa;
String ITT="Instituto Tecnologico de Tijuana",UABC ="Universidad Autonoma";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lsData = new ArrayList<>();
        lsData.add(new DataItem(R.drawable.i,ITT));
       lsData.add(new DataItem(R.drawable.u,UABC));
         ls = (ListView) findViewById(R.id.listauni);
        CustomAdapter adapter = new CustomAdapter(this,R.layout.itemrow,lsData);
        ls.setAdapter(adapter);
         vermapa = findViewById(R.id.btnmapa);
         vermapa.setVisibility(View.GONE);
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

             ModelInfo i = new ModelInfo();
                Bundle b = new Bundle();
                String iv = (String) lsData.get(position).Uniname;
                TypeUniFragment f = new TypeUniFragment();


                  switch (String.valueOf(iv)){

                      case "Instituto Tecnologico de Tijuana":

                          getSupportFragmentManager().beginTransaction().replace(R.id.container,f).commit();
                          break;

                      case "Universidad Autonoma":

                          new AlertDialog.Builder(MainActivity.this)
                                  .setTitle("Mapa no disponible")
                                  .setMessage("Esta aplicacion esta en proceso de desarrollo")
                                  .setPositiveButton("Cerrar", new DialogInterface.OnClickListener(){
                          @Override
                          public void onClick(DialogInterface dialog, int which) {

                      }
                  }).show();

                break;
                  }
            }
        });
    }

}
