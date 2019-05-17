package com.example.ejercicioprac.Datos;

import android.content.ContentValues;
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

    public static void actualizar(SQLiteDatabase database,Usuario usuario){
        String q = "";
        ContentValues contentValues = new ContentValues();
        contentValues.put("correos",usuario.getCorreo());
        contentValues.put("nombres",usuario.getNombre());
        contentValues.put("claves",usuario.getPass());
        contentValues.put("tipo",usuario.getTipo());
        String[] args = new String[]{usuario.getCorreo()};
        database.update("usuarios",contentValues,"correos=?",args);
    }

    public static boolean comprobarCorreo(SQLiteDatabase database,String correo){
        boolean existe = false;
        String q = "select correos from usuarios where correos = '"+correo+"'";
        Cursor cursor = database.rawQuery(q,null);
        if (cursor.moveToFirst()){
            existe = true;
        }
        return existe;
    }
}
