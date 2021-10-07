package com.example.apprestaurantes;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import android.widget.Toast;

import androidx.annotation.Nullable;

import static android.database.sqlite.SQLiteDatabase.*;

public class controladorTablaRestaurantes {
    /* COntantes tabla Restaurantes*/
    public static final String TABLA_RESTAURANTES = "restaurantes";
    //
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_DIRECCION = "direccion";
    public static final String CAMPO_TELEFONO = "telefono";
    public static final String CAMPO_CATEGORIA = "categoria";
    public static final String CAMPO_PRECIO = "precio";
    public static final String CAMPO_COMENTARIO = "comentario";
    public static final String CAMPO_VALORACION = "valoracion";
    public static final String CAMPO_FAVORITO = "favorito";

    public static final String CREAR_TABLA_RESTAURANTE =  "CREATE TABLE "+TABLA_RESTAURANTES+" ("+CAMPO_NOMBRE+" text primary key, "+CAMPO_DIRECCION+" text, "+CAMPO_TELEFONO+" integer,"+CAMPO_CATEGORIA+" text, "+CAMPO_PRECIO+" integer,"+CAMPO_COMENTARIO+" text,"+CAMPO_VALORACION+" integer, "+CAMPO_FAVORITO+" boolean)";
    private static Object AdminSQLiteOpenHelper;
    private static Object NuevoRestaurante;

    public controladorTablaRestaurantes(NuevoRestaurante nuevoRestaurante) {

    }


    public static boolean crearRestaurante(restaurante restaurante){
        boolean creado;
        ContentValues registro = new ContentValues();
        registro.put(CAMPO_NOMBRE, restaurante.getNombre());
        registro.put(CAMPO_DIRECCION, restaurante.getDireccion());
        registro.put(CAMPO_TELEFONO, restaurante.getTelefono());
        registro.put(CAMPO_CATEGORIA, restaurante.getCategoria());
        registro.put(CAMPO_PRECIO, restaurante.getPrecio());
        registro.put(CAMPO_COMENTARIO, restaurante.getComentario());
        registro.put(CAMPO_VALORACION, restaurante.getValoracion());
        registro.put(CAMPO_FAVORITO, restaurante.isFavorito());


        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper((Context) AdminSQLiteOpenHelper, "bd_restaurantes", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        bd.insert(TABLA_RESTAURANTES,null,registro);
        creado=true;
        //creado = bd.insert(TABLA_RESTAURANTES,null,registro)>0;
        bd.close();

        return creado;
    }


}// fi clase controlador tabla
