package com.example.apprestaurantes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MisRestaurantes extends AppCompatActivity {
    Context context;

    private AdminSQLiteOpenHelper admin;
    private ListView lvItemRest;
    private Adaptador adaptador;
    private static ArrayList<restaurante> listaRest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_restaurantes);
        //
        admin = new AdminSQLiteOpenHelper(getApplicationContext(),"bd_restaurantes",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        //
        lvItemRest=(ListView) findViewById(R.id.lvRestaurantes);
        listaRest = new ArrayList<restaurante>();
        listaRest = llenarLista();
        adaptador = new Adaptador(this,listaRest);
        lvItemRest.setAdapter(adaptador);
        lvItemRest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                restaurante rest = listaRest.get(position);//guarda el restaurante elegido por posicion
                String RestauranteEledigo = rest.getNombre().toString(); // nombre del restaurante
                Intent intent = new Intent(MisRestaurantes.this,activity_Restaurante.class);
                intent.putExtra("volver","MisRestaurantes");
                intent.putExtra("restaurante",RestauranteEledigo);
                startActivity(intent);
            }// fin onItemClick
        });
    }// fin oncreate



    public ArrayList<restaurante> llenarLista(){
        ArrayList<restaurante> lista = new ArrayList<restaurante>();
        String qt="SELECT * FROM restaurantes ORDER BY nombre"; //busca los restaurantes por orden alfabetico
        admin = new AdminSQLiteOpenHelper(this,"bd_restaurantes",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery(qt,null);

        if(fila.moveToFirst()){
            do {
                String nombre = fila.getString(0);
                String direccion = fila.getString(1);
                int telefono = fila.getInt(2);
                String categoria = fila.getString(3);
                int precio = fila.getInt(4);
                String comentario = fila.getString(5);
                String valoracion = fila.getString(6);
                int favoritoInt = fila.getInt(7);
                boolean favorito = false;
                if (favoritoInt != 0) {
                    favorito = true;
                }
                restaurante rest = new restaurante(nombre, direccion, telefono, categoria, precio, comentario, valoracion, favorito);
                lista.add(rest);
                //Toast.makeText(this,"GRABA RESTAURANTE"+nombre, Toast.LENGTH_SHORT).show();
                System.out.println("GRABA RESTAURANTE" + nombre);
            }
            while (fila.moveToNext());
            fila.close();
            bd.close();
            //Toast.makeText(this,"DEVUELVE LISTA RESTAURANTES", Toast.LENGTH_LONG).show();
            return lista;
        }
        else{//si no hay restaurantes
            Toast.makeText(this,"NO HAY RESTAURANTES GUARDADOS", Toast.LENGTH_LONG).show();
            return lista;//vacia
        }
    }//fin llenar lista


    public void volver(View view){
        Intent i = new Intent(this, MainActivity.class );
        this.finish();
        startActivity(i);
    }//fin volver view

}// fin MisRestaurantes