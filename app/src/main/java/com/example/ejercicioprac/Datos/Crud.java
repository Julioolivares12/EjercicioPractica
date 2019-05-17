package com.example.ejercicioprac.Datos;

import android.database.Cursor;
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

    public static Usuario buscarPorCorreo(SQLiteDatabase database,String correo){
        Usuario usuario = new Usuario();
        String q = "select * from usuarios where correos == '"+correo+"'";
        Cursor cursor = database.rawQuery(q,null);
        if (cursor.moveToFirst()){
            usuario.setCorreo(cursor.getString(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setPass(cursor.getString(2));
            usuario.setTipo(cursor.getString(3));
        }
        return usuario;
    }
}
