package com.example.apprestaurantes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PorFavoritos extends AppCompatActivity {
    private static ArrayList<restaurante> listaRestPorFav;
    private ListView lvItemRestFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        //
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(),"bd_restaurantes",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        //
        lvItemRestFav=(ListView)findViewById(R.id.lvResFav);

        listaRestPorFav = llenarLista(); //rellena la lista de restaurantes que tengan la categoria pasada

        Adaptador adaptador = new Adaptador(this,listaRestPorFav);
        lvItemRestFav.setAdapter(adaptador);

        lvItemRestFav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                restaurante rest = listaRestPorFav.get(position);//guarda el restaurante elegido por posicion
                String RestauranteEledigo = rest.getNombre().toString(); // nombre del restaurante
                Intent intent = new Intent(PorFavoritos.this,activity_Restaurante.class);
                intent.putExtra("volver","PorFavoritos");
                intent.putExtra("restaurante",RestauranteEledigo);
                startActivity(intent);
            }// fin onItemClick
        });

    }// fin on create

    public ArrayList<restaurante> llenarLista(){
        Boolean favorito= false;
        ArrayList<restaurante> lista = new ArrayList<restaurante>();
        String qt="SELECT * FROM restaurantes WHERE favorito = 1"; // busca restaurantes que esten marcados como favorito
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"bd_restaurantes",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery(qt,null);
        if(fila.moveToFirst()) {// si encuentra restaurantes
            do {
                String nombre = fila.getString(0);
                String direccion = fila.getString(1);
                int telefono = fila.getInt(2);
                String categoria = fila.getString(3);
                int precio = fila.getInt(4);
                String comentario = fila.getString(5);
                String valoracion = fila.getString(6);
                int favoritoInt = fila.getInt(7);
                if (favoritoInt != 0) {
                    favorito = true;
                }
                restaurante rest = new restaurante(nombre, direccion, telefono, categoria, precio, comentario, valoracion, favorito);
                lista.add(rest);
            }
            while (fila.moveToNext());
            fila.close();
            bd.close();
            //Toast.makeText(this, "DEVUELVE LISTA RESTAURANTES " + CateSelec, Toast.LENGTH_LONG).show();
            return lista;
        }
        else{
            Toast.makeText(this, "No Hay restaurantes en FAVORITOS ", Toast.LENGTH_LONG).show();
            fila.close();
            bd.close();
            return lista;//vacia
        }
    }//fin llenar lista


    public void volver(View view){
        Intent i = new Intent(this, MainActivity.class );
        this.finish();
        startActivity(i);
    }//fin volver view
}// fin clase favoritos