package com.example.apprestaurantes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NuevoRestaurante extends AppCompatActivity {
    private EditText etNombre,etCategoria,etPrecio,etDireccion,etTelefono,etComentario;
    private RatingBar rbValoracion;
    private Spinner spCategoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_restaurante);

        etNombre=(EditText)findViewById(R.id.etNombreRest);
        etCategoria=(EditText)findViewById(R.id.etCategoria);
        etPrecio=(EditText)findViewById(R.id.etPrecio);
        etDireccion=(EditText)findViewById(R.id.etDireccion);
        etTelefono=(EditText)findViewById(R.id.etTelefono);
        etComentario=(EditText)findViewById(R.id.etComentario);
        rbValoracion=(RatingBar)findViewById(R.id.rbValoracion);
        spCategoria=(Spinner)findViewById(R.id.spCategoria);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,MainActivity.getStrCategorias());

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spCategoria.setAdapter(adapter);

        spCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView adapter, View v, int i, long lng) {
                String  CatSelecc = spCategoria.getSelectedItem().toString();
                etCategoria.setText(CatSelecc);
                //Toast.makeText(NuevoRestaurante.this, "tipo - "+ CatSelecc, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView arg0) {
                Toast.makeText(getApplicationContext(), "NO HA SELECCIONADO NADA", Toast.LENGTH_SHORT).show();

            }
        });

       // String name = spCategoria.getSelectedItem().toString();

    }// fin on create

    public void guardar(View v) {
        boolean Rcreado = false;
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"bd_restaurantes",null,1);
        //AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String nombre = etNombre.getText().toString();
        //String categoria = etCategoria.getText().toString();
        String categoria = spCategoria.getSelectedItem().toString();
        String precio = etPrecio.getText().toString();
        String direccion = etDireccion.getText().toString();
        String telefono =  etTelefono.getText().toString();
        String comentario = etComentario.getText().toString();
        float valoracion = rbValoracion.getRating();
        Boolean favorito = false;

            String val = Float.toString(valoracion);;
            int tlf = Integer.valueOf(telefono);
            int prec = Integer.valueOf(precio);

            restaurante restNuevo = new restaurante(nombre,direccion,tlf,categoria,prec,comentario,val,favorito);

        if (nombre.isEmpty()||direccion.isEmpty()){
            Toast.makeText(this, "CAMPO VACIO", Toast.LENGTH_SHORT).show();
        }
        else { // si estan todos los campos rellenos crea el nuevo registro
            ContentValues registro = new ContentValues();
            registro.put("nombre", nombre);
            registro.put("direccion", direccion);
            registro.put("telefono", telefono);
            registro.put("categoria", categoria);
            registro.put("precio", precio);
            registro.put("comentario", comentario);
            registro.put("valoracion", val);
            registro.put("favorito", favorito);
            bd.insert("restaurantes", null, registro);
            bd.close();
            limpiar();
            //TOAST CON ICONO//
            Toast toast3 = new Toast(getApplicationContext());
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.toast_layout,
                    (ViewGroup) findViewById(R.id.lytLayout));
            TextView txtMsg = (TextView)layout.findViewById(R.id.txtMensaje);
            txtMsg.setText("Restaurante Creado");
            toast3.setDuration(Toast.LENGTH_SHORT);
            toast3.setView(layout);
            toast3.show();
        }


    }// fin guardar
    /*
    public void buscar(View v){
        //AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"bd_restaurantes",null,1);
        SQLiteDatabase bd =  admin.getWritableDatabase();

        String Buscado = etNombre.getText().toString();
        if(!Buscado.isEmpty()){
            Toast.makeText(this,"Buscas:"+Buscado, Toast.LENGTH_LONG).show();
            //Cursor fila = bd.rawQuery("SELECT * FROM pruebaRestaurante WHERE nombre= '"+Buscado+"' ",null);
            Cursor fila = bd.rawQuery("SELECT * FROM restaurantes WHERE nombre= '"+Buscado+"' ",null);
            if(fila.moveToFirst()){
                etNombre.setText(fila.getString(0));
                etDireccion.setText(fila.getString(1));
                etTelefono.setText(fila.getString(2));
                etCategoria.setText(fila.getString(3));
                etPrecio.setText(fila.getString(4));
                etComentario.setText(fila.getString(5));
                rbValoracion.setRating(fila.getInt(6));
                bd.close();
            }
        }else{
            Toast.makeText(this,"campo vacio", Toast.LENGTH_LONG).show();
            bd.close();
        }
    }

     */
    public void limpiar(View v){
        etNombre.setText("");
        //etCategoria.setText("");
        spCategoria.setSelection(0);
        etPrecio.setText("");
        etDireccion.setText("");
        etTelefono.setText("");
        etComentario.setText("");
        rbValoracion.setRating(0);

    }// fin limpiar

    public void limpiar(){
        etNombre.setText("");
       //etCategoria.setText("");
        etPrecio.setText("");
        etDireccion.setText("");
        etTelefono.setText("");
        etComentario.setText("");
        rbValoracion.setRating(0);

    }// fin limpiar

    public void volver(View view){
        Intent i = new Intent(this, MainActivity.class );
        this.finish();
        startActivity(i);
    }//fin volver view
}// fin clase nuevo restaurante