package com.example.reborn30.maptecs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.reborn30.maptecs.ListaDeEscuelas.IUni;
import com.example.reborn30.maptecs.ListaDeEscuelas.TEC;
import com.example.reborn30.maptecs.ListaDeEscuelas.UABC;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


List<DataItem> lsData;
IUni uni;
ListView ls;
Button m;
String ITT="Instituto Tecnologico de Tijuana",UABC ="Universidad Autonoma";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lsData = new ArrayList<>();
        lsData.add(new DataItem(R.drawable.common_full_open_on_phone,ITT));
       lsData.add(new DataItem(R.drawable.common_google_signin_btn_icon_dark,UABC));
         ls = (ListView) findViewById(R.id.listauni);
        CustomAdapter adapter = new CustomAdapter(this,R.layout.itemrow,lsData);
        ls.setAdapter(adapter);
m = findViewById(R.id.btnmapa);
m.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(getApplicationContext(),TECMapActivity.class);
        startActivity(i);
    }
});



        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

             ModelInfo i = new ModelInfo();
                Bundle b = new Bundle();
                  String iv = (String) lsData.get(position).Uniname;
                TypeUniFragment f = new TypeUniFragment();


                  switch (String.valueOf(iv)){

                      case "Instituto Tecnologico de Tijuana":
                          uni = new TEC();
                          b.putString("Nombre",uni.TypoUniversidad());
                          b.putString("Descripcion",uni.Descripcion());
                          f.setArguments(b);
                          getSupportFragmentManager().beginTransaction().replace(R.id.container,f).commit();
                          break;

                      case "Universidad Autonoma":

                          uni = new UABC();
                          b.putString("Nombre",uni.TypoUniversidad());
                          b.putString("Descripcion",uni.Descripcion());
                          f.setArguments(b);
                          getSupportFragmentManager().beginTransaction().replace(R.id.container,f).commit();

                          break;
                  }
            }
        });
    }

}
