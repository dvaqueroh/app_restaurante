package com.example.apprestaurantes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table restaurantes(nombre text primary key, direccion text, telefono integer,categoria text, precio integer,comentario text,valoracion text, favorito boolean)");
    }// fin ON CREATE

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists restaurantes");
        onCreate(db);

    }// fin ON UPGRADE



}/// final clase AdminSQLiteOpenHelper
