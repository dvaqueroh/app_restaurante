package com.example.apprestaurantes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class selecCategoria extends AppCompatActivity {
    private EditText etCatSel;
    private Spinner spSelecCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selec_categoria);
        etCatSel=(EditText)findViewById(R.id.etSelCat);
        spSelecCat = (Spinner)findViewById(R.id.spSelCat);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,MainActivity.getStrCategorias());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spSelecCat.setAdapter(adapter);

        spSelecCat.setSelection(0,false);

        spSelecCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView adapter, View v, int i, long lng) {


                String  CatSelecc = spSelecCat.getSelectedItem().toString();
                etCatSel.setText(CatSelecc);
                Intent intentCat = new Intent(selecCategoria.this,PorCategoria.class);
                intentCat.putExtra("volver","");
                intentCat.putExtra("categoria",CatSelecc);
                Toast.makeText(selecCategoria.this, "tipo - "+ CatSelecc, Toast.LENGTH_SHORT).show();
                startActivity(intentCat);

            }

            @Override
            public void onNothingSelected(AdapterView arg0) {
                Toast.makeText(getApplicationContext(), "NO HA SELECCIONADO NADA", Toast.LENGTH_SHORT).show();

            }
        });


    }// fin on create

    public void seleccion(View v){

    }

    public void volver(View view){
        Intent i = new Intent(this, MainActivity.class );
        finish();
        startActivity(i);
    }//fin volver view

}//fin clase