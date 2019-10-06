package com.example.reborn30.maptecs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class LugaresGrandes extends AppCompatActivity {
  ImageView _imgdept,imga,imgb,imgc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugares_grandes);
        imga = findViewById(R.id.img1);
        imgb = findViewById(R.id.img2);
        imgc = findViewById(R.id.img3);
        imga.setImageResource(R.drawable.sisycompu);
        imgc.setImageResource(R.drawable.tics);
        imgb.setImageResource(R.drawable.informatic);
        _imgdept = findViewById(R.id.imgdept);
        _imgdept.setImageResource(R.drawable.computacion);
    }
}
