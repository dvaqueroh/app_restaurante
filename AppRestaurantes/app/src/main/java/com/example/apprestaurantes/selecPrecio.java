package com.example.apprestaurantes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class selecPrecio extends AppCompatActivity implements View.OnClickListener {
    private Button btHasta15,bt1525,bt2550,btmas50;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selec_precio);
        btHasta15=(Button)findViewById(R.id.bt15);
        bt1525=(Button)findViewById(R.id.bt1525);
        bt2550=(Button)findViewById(R.id.bt2550);
        btmas50=(Button)findViewById(R.id.bt50);
        btHasta15.setOnClickListener(this);
        bt1525.setOnClickListener(this);
        bt2550.setOnClickListener(this);
        btmas50.setOnClickListener(this);
    }// fin on create selecPrecio

    @Override
    public void onClick(View v) {
        Intent intentPre = new Intent(selecPrecio.this,PorPrecio.class);
        switch (v.getId()){
            case R.id.bt15:
                intentPre.putExtra("rango","<15");
                break;
            case R.id.bt1525:
                intentPre.putExtra("rango","15-25");
                break;
            case R.id.bt2550:
                intentPre.putExtra("rango","25-50");
                break;
            case R.id.bt50:
                intentPre.putExtra("rango",">50");
                break;
        }// fin Switch
        finish();
        startActivity(intentPre);

    }// fin on click

    public void volver(View view){
        Intent i = new Intent(this, MainActivity.class );
        this.finish();
        startActivity(i);
    }//fin volver view



}// fin clase selecPrecio