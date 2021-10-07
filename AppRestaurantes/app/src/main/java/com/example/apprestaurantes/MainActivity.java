package com.example.apprestaurantes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    MisRestaurantes misRes;
    private static String[] StrCategorias = {"","Tapas","Tradicional","Asiatico","Mejicano","Hindu","Vegetariano","Vegano","Moderno"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuopciones, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if ( id == R.id.itAcerca ) {
            Intent i = new Intent(this, acercaDe.class );
            startActivity(i);
        }
        if ( id == R.id.itSalir ) {
            //Toast.makeText(this,"SALIR DE LA APLICACION",Toast.LENGTH_LONG);
            this.finish();
            finish();

        }
        return super.onOptionsItemSelected(item);
    }
    public void misRestaurantes(View view){
        Intent i = new Intent(this, MisRestaurantes.class );
        startActivity(i);
    }// fin mis restaurantes view

    public void porFavoritos(View v){
        Intent i = new Intent(this, PorFavoritos.class);
        //this.finish();
       startActivity(i);
    }// fin favoritos view

    public void porCategoria(View v){
        Intent i = new Intent(this,selecCategoria.class);
        startActivity(i);
    }// fin por tipo

    public void PorPrecio(View v){
        Intent i = new Intent(this,selecPrecio.class);
        startActivity(i);
    }

    public void Nuevo(View view){
        Intent i = new Intent(this, NuevoRestaurante.class );
        startActivity(i);
    }// fin mis restaurantes view

    /*GET SET*/

    public static String[] getStrCategorias() {
        return StrCategorias;
    }
}//fin main activity