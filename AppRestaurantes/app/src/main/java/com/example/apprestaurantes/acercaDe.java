package com.example.apprestaurantes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class acercaDe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
    }//fin oncreate

    public void volver(View view){
        Intent i = new Intent(this, MainActivity.class );
        this.finish();
        startActivity(i);
    }//fin acercade view
}//fin clase acercaDe