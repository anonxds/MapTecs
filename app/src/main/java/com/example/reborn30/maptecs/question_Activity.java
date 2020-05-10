package com.example.reborn30.maptecs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class question_Activity extends AppCompatActivity {
    TextView titulo,descripcion;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_);

        titulo = findViewById(R.id.id_question);
        descripcion = findViewById(R.id.id_description);
        imagen = findViewById(R.id.id_img);
        Long getid = getIntent().getLongExtra("id",0);
        String tituloS = getIntent().getStringExtra("titulo");

        int identify = getid.intValue();
        set_information(identify,titulo,descripcion,tituloS,imagen);

    }

    public void set_information(int id, TextView titulo, TextView descripcion,String tituloS, ImageView img){
        String _description = null;
        int i = 0;
        switch (id){
            case 0:
                _description = "blah, blah";
                 i = R.drawable.question;

                break;
            case 1:
                _description = "blah, blah,blah";
                i = R.drawable.q_1;

                break;
            case 2:
                _description = "blah, blah,blah,blah";
                i = R.drawable.q_2;

                break;
        }
        titulo.setText(tituloS);
        descripcion.setText(_description);
        img.setImageResource(i);    }
}
