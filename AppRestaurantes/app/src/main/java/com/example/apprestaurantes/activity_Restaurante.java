package com.example.apprestaurantes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class activity_Restaurante extends AppCompatActivity {
    private TextView ResNombre,ResDireccion,ResTelefono,ResCategoria,ResPrecio,ResComentario;
    private RatingBar rbRes;
    private Button btVolver;
    private ImageButton ibLlamar,ibFavorito;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__restaurante);
        ///
        ResNombre=(TextView) findViewById(R.id.ResNombre);
        ResDireccion=(TextView) findViewById(R.id.ResDireccion);
        ResTelefono=(TextView) findViewById(R.id.ResTelefono);
        ResCategoria=(TextView) findViewById(R.id.ResCategoria);
        ResPrecio=(TextView) findViewById(R.id.ResPrecio);
        ResComentario=(TextView) findViewById(R.id.ResComentario);
        rbRes=(RatingBar) findViewById(R.id.rbRes);
        ibLlamar=(ImageButton)findViewById(R.id.btLlamar);
        ibFavorito=(ImageButton)findViewById(R.id.btFavorito);
        btVolver=(Button)findViewById(R.id.btResVolver);

        //
        Bundle bundle = getIntent().getExtras();
        String restaurante=bundle.getString("restaurante");// recojemos el nombre del restaurante elegido para hacer busqueda y cargar datos
        //Toast.makeText(this,restaurante,Toast.LENGTH_LONG);
        buscar(restaurante);

    }// fin on create
    public void buscar(String Buscado){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"bd_restaurantes",null,1);
        SQLiteDatabase bd =  admin.getWritableDatabase();

        //String Buscado = ResNombre.getText().toString();
        if(!Buscado.isEmpty()){
            //Toast.makeText(this,"Buscas:"+Buscado, Toast.LENGTH_LONG).show();
            Cursor fila = bd.rawQuery("SELECT * FROM restaurantes WHERE nombre= '"+Buscado+"' ",null);
            if(fila.moveToFirst()){
                ResNombre.setText(fila.getString(0));
                ResDireccion.setText(fila.getString(1));
                ResTelefono.setText(fila.getString(2));
                ResCategoria.setText(fila.getString(3));
                ResPrecio.setText(fila.getString(4));
                ResComentario.setText(fila.getString(5));
                rbRes.setRating(fila.getInt(6));
                int numfav = fila.getInt(7);
                //Toast.makeText(this,"campo favorito: "+ numfav, Toast.LENGTH_LONG).show();
                if (numfav==0) {
                    ibFavorito.setActivated(false);
                }
                else{
                    ibFavorito.setActivated(true);
                }
                bd.close();
            }
        }else{
            Toast.makeText(this,"campo vacio", Toast.LENGTH_LONG).show();
            bd.close();
        }
    }// fin BUSCAR

    public void marcarFavorito(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"bd_restaurantes",null,1);
        SQLiteDatabase bd =  admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        boolean favorito=false;
        String nombre = ResNombre.getText().toString();

        if(ibFavorito.isActivated()) { //si ya es favorito, se quita de favoritos con false
            String sq= "UPDATE restaurantes SET favorito = 0 WHERE nombre='"+nombre+"'";
            bd.execSQL(sq);
            bd.close();
            ibFavorito.setActivated(false);
            Toast.makeText(this, "se quito de favoritos", Toast.LENGTH_SHORT).show();
        }
        else{ // agregar restaurante a favorito

            String sq= "UPDATE restaurantes SET favorito = 1 WHERE nombre='"+nombre+"'";
            bd.execSQL(sq);
            bd.close();
            ibFavorito.setActivated(true);
            Toast.makeText(this, "se añadio a favoritos", Toast.LENGTH_SHORT).show();
        }//fin else


    }// FIN MARCAR FAVORITO

    public void Llamar(View v) {
        //Intent i = new Intent(Intent.ACTION_CALL);
        //i.setData(Uri.parse(ResTelefono.getText().toString()));
        Intent intent= new Intent(Intent.ACTION_DIAL,Uri.fromParts("tel",ResTelefono.getText().toString(),null));
        startActivity(intent);
    }

    public void volver(View view){
        Bundle bundle = getIntent().getExtras();
        String volver=bundle.getString("volver"); // guarda el valor de la pagina a la que tiene que volver
        switch (volver){
            case "MisRestaurantes":
                Intent intMisRes = new Intent(this, MisRestaurantes.class );
                this.finish();
                startActivity(intMisRes);
                break;
            case"PorCategoria":
                Intent intPorCat = new Intent(this, PorCategoria.class );
                this.finish();
                startActivity(intPorCat);
                break;
            case"PorPrecio":
                Intent intPorPre = new Intent(this, PorPrecio.class );
                intPorPre.putExtra("rango",bundle.getString("rango"));
                this.finish();
                startActivity(intPorPre);
                break;
            case"PorFavoritos":
                Intent intPorFav = new Intent(this, PorFavoritos.class );
                this.finish();
                startActivity(intPorFav);
                break;
        }// fin switch volver

    }//fin volver view

}// fin clase