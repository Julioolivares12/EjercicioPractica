package com.example.ejercicioprac.Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Random;

public class ConexionSQLite extends SQLiteOpenHelper {
    public ConexionSQLite(Context context, String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String consultaSql = "create table usuarios(correos text primary key, nombres text, claves text, tipo text)";
        db.execSQL(consultaSql);
        String insertarUsuario="insert into usuarios (correos ,nombres,claves,tipo ) values ('julio90@mail.com','julio','123','administrador')";
        db.execSQL(insertarUsuario);


        /*
        otra forma de insertar datos
        * ContentValues contentValues = new ContentValues();
        contentValues.put("id", new Random().nextInt());
        contentValues.put("nombres","julio");
        contentValues.put("claves","123");
        contentValues.put("tipo","admin");
        db.insert("usuarios","id",contentValues);
        *
        * */
       // System.out.println(idResult);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
