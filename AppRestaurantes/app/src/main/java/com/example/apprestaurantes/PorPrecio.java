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

public class PorPrecio extends AppCompatActivity {
    private EditText etRangoPre;
    private String RangoSelec;
    private static ArrayList<restaurante> listaRestPorPre;
    private ListView lvItemRestPre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_por_precio);
        etRangoPre=(EditText)findViewById(R.id.etRangoPre);
        //
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(),"bd_restaurantes",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        //
        lvItemRestPre=(ListView)findViewById(R.id.lvResPre);
        //recoje la categoria seleccionada en la categoria anterior
        Bundle bundle = getIntent().getExtras();
        RangoSelec = bundle.getString("rango");
        etRangoPre.setText(RangoSelec.toString());
        //Toast.makeText(this,"rango pasado"+RangoSelec.toString(), Toast.LENGTH_SHORT).show();

        listaRestPorPre=llenarLista();//rellena la lista de restaurantes que tengan el rango de precio seleccionado
        Adaptador adaptador = new Adaptador(this,listaRestPorPre);
        lvItemRestPre.setAdapter(adaptador);

        lvItemRestPre.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                restaurante rest = listaRestPorPre.get(position);//guarda el restaurante elegido por posicion
                String RestauranteEledigo = rest.getNombre().toString(); // nombre del restaurante
                Intent intent = new Intent(PorPrecio.this,activity_Restaurante.class);
                intent.putExtra("volver","PorPrecio");
                intent.putExtra("rango",RangoSelec);
                intent.putExtra("restaurante",RestauranteEledigo);
                startActivity(intent);
            }// fin onItemClick
        });

    }// fin on create por precio

    public ArrayList<restaurante> llenarLista(){
        String qt="";
        ArrayList<restaurante> lista = new ArrayList<restaurante>();
         // busca restaurantes de la categoria seleccionada
        switch (RangoSelec){// depende del rago de precio, hace una busqueda diferente
            case "<15":
                qt="SELECT * FROM restaurantes WHERE precio BETWEEN  0 and 15";
                break;
            case"15-25":
                qt="SELECT * FROM restaurantes WHERE precio BETWEEN  15 and 25";
                break;
            case"25-50":
                qt="SELECT * FROM restaurantes WHERE precio BETWEEN  25 and 50";
                break;
            case">50":
                qt="SELECT * FROM restaurantes WHERE precio BETWEEN  50 and 99999";
                break;
        }// fin switch rango precio

        //
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"bd_restaurantes",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        try (Cursor fila = bd.rawQuery(qt, null)) {
            if (fila.moveToFirst()) {// si encuentra restaurantes
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
                }
                while (fila.moveToNext());
                fila.close();
                bd.close();
                //Toast.makeText(this, "DEVUELVE LISTA RESTAURANTES " + RangoSelec, Toast.LENGTH_LONG).show();
                return lista;
            } else {
                Toast.makeText(this, "No Hay restaurantes en este rango de precio " + RangoSelec, Toast.LENGTH_LONG).show();
                etRangoPre.setText(RangoSelec +" no hay restaurantes en este rango");
                fila.close();
                bd.close();
                return lista;//vacia
            }
        } // lanza la busqueda con los rangos seleccionados
        catch (Exception e){
            String mensaje = e.getMessage();
            String fallo = e.getLocalizedMessage();
            Toast.makeText(this, mensaje + RangoSelec, Toast.LENGTH_LONG).show();
            Toast.makeText(this, fallo + RangoSelec, Toast.LENGTH_LONG).show();
            //etRangoPre.setText(RangoSelec +" no hay restaurantes en este rango");
            bd.close();
            return lista;//vacia
        }
    }//fin llenar lista

    public void volver(View view){
        Intent i = new Intent(this, selecPrecio.class );
        this.finish();
        startActivity(i);
    }//fin volver view

}// fin clase por precio