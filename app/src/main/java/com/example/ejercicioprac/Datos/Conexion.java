package com.example.ejercicioprac.Datos;

import android.content.Context;

public class Conexion {
    private static   ConexionSQLite conexionSQLite;
    static String  nombreBD ="usuariosBD";

    public  static ConexionSQLite getConexion(Context context){
        if (conexionSQLite == null){
            conexionSQLite = new ConexionSQLite(context,nombreBD,null,1);
        }
        return conexionSQLite;
    }
}
