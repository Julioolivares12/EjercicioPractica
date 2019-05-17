package com.example.ejercicioprac.Datos;

import android.database.sqlite.SQLiteDatabase;

public class Crud {

    /*
    table = nombre de la tabla
    campoAeliminar campo para compara y poder eliminar
    campo es el valor con el que se compara campoAeliminar
    */
    public static void eliminar(SQLiteDatabase database,String table, String campoAeliminar, String campo){
        String sql = "delete from '"+table+"' where '"+campoAeliminar+"' == '"+campo+"'";
        database.execSQL(sql);
    }
}
