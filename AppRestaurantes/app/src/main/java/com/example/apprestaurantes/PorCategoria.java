package com.example.apprestaurantes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PorCategoria extends AppCompatActivity {
    private String CateSelec;
    private static ArrayList<restaurante> listaRestPorCat;
    private ListView lvItemRestCat;
    private EditText etCatList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_por_categoria);
        //
        etCatList=(EditText)findViewById(R.id.etCatList);
        //
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(),"bd_restaurantes",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        //
        lvItemRestCat=(ListView)findViewById(R.id.lvRestCat);

        //recoje la categoria seleccionada en la categoria anterior
        Bundle bundle = getIntent().getExtras();
        CateSelec = bundle.getString("categoria");
        etCatList.setText(CateSelec);
        //Toast.makeText(this,"categoria pasada"+CateSelec.toString(), Toast.LENGTH_SHORT).show();
        /**/
        listaRestPorCat = llenarLista(); //rellena la lista de restaurantes que tengan la categoria pasada

        Adaptador adaptador = new Adaptador(this,listaRestPorCat);
        lvItemRestCat.setAdapter(adaptador);

        lvItemRestCat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                restaurante rest = listaRestPorCat.get(position);//guarda el restaurante elegido por posicion
                String RestauranteEledigo = rest.getNombre().toString(); // nombre del restaurante
                Intent intent = new Intent(PorCategoria.this,activity_Restaurante.class);
                intent.putExtra("volver","PorCategoria");
                intent.putExtra("restaurante",RestauranteEledigo);
                startActivity(intent);
            }// fin onItemClick
        });

    }//fin on create

    public ArrayList<restaurante> llenarLista(){
        ArrayList<restaurante> lista = new ArrayList<restaurante>();
        String qt="SELECT * FROM restaurantes WHERE categoria= '"+CateSelec+"'"; // busca restaurantes de la categoria seleccionada
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"bd_restaurantes",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery(qt,null);
        int i=0;
        if(fila.moveToFirst()) {// si encuentra restaurantes
            do {
                String nombre = fila.getString(0);
                String direccion = fila.getString(1);
                int telefono = fila.getInt(2);
                String categoria = fila.getString(3);
                int precio = fila.getInt(4);
                String comentario = fila.getString(5);
                String valoracion = fila.getString(6);
                String favoritoStr = fila.getString(7);
                boolean favorito = false;
                if (favoritoStr != "0") {
                    favorito = true;
                }
                restaurante rest = new restaurante(nombre, direccion, telefono, categoria, precio, comentario, valoracion, favorito);
                lista.add(rest);
                i++;
            }
            while (fila.moveToNext());
            fila.close();
            bd.close();
            //Toast.makeText(this, "DEVUELVE LISTA RESTAURANTES " + CateSelec, Toast.LENGTH_LONG).show();
            return lista;
        }
        else{
            Toast.makeText(this, "No Hay restaurantes en esa categoria " + CateSelec, Toast.LENGTH_LONG).show();
            etCatList.setText(CateSelec +" no hay restaurantes en esta categoria");
            fila.close();
            bd.close();
            return lista;//vacia
        }
    }//fin llenar lista

    public void volver(View view){
        Intent i = new Intent(this, selecCategoria.class );
        this.finish();
        startActivity(i);
    }//fin volver view
}// fin clase por categoria