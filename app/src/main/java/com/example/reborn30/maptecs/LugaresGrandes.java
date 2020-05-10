package com.example.reborn30.maptecs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.reborn30.maptecs.Menu.InformacionEscuela;

import java.util.ArrayList;
import java.util.Arrays;

public class LugaresGrandes extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugares_grandes);

         final ListView _listquestion =findViewById(R.id.listquestion);
        ArrayList<String>  questions_list = new ArrayList<String>(Arrays.asList("Dónde solicito mi credencial?",
                "Dónde solicito mi constancia de estudio?",
                "Donde se encuentra la oficina de servicio social?"));


        final ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, questions_list);

        _listquestion.setAdapter(arrayAdapter);

        _listquestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                long id = arrayAdapter.getItemId(i);
                String titutlo = arrayAdapter.getItem(i);
                Intent q = new Intent(getBaseContext(), question_Activity.class);
                q.putExtra("id",id);
                q.putExtra("titulo",titutlo);
                startActivity(q);
            }
        });

    }
}
