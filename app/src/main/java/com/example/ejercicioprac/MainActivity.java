package com.example.ejercicioprac;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.ejercicioprac.Datos.Conexion;
import com.example.ejercicioprac.Datos.ConexionSQLite;
import com.example.ejercicioprac.Datos.Usuario;

import backend.Usuarios;

public class MainActivity extends AppCompatActivity {

    EditText edtUsuario, edtPass;

    String usuario = "", pass="";

    ConexionSQLite conexionSQLite ;
    SQLiteDatabase base ;
    public static final int MAINACTVITY_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsuario = findViewById(R.id.edtUsuario);
        edtPass = findViewById(R.id.edtClave);
        conexionSQLite= Conexion.getConexion(getApplicationContext());
        base = conexionSQLite.getWritableDatabase();
    }

    public void ingresar(View v)
    {

        usuario = edtUsuario.getText().toString().trim();
        pass = edtPass.getText().toString().trim();

        if(TextUtils.isEmpty(usuario)){
            edtUsuario.requestFocus();
            edtUsuario.setError("Ingresar usuario");
        }
        else if(TextUtils.isEmpty(pass)){
            edtPass.requestFocus();
            edtPass.setError("Ingresar clave");
        }
        else {

            login(edtUsuario.getText().toString(),edtPass.getText().toString());

        }
    }

    public void login(String correo , String pass){
        String misql="select correos,claves , tipo from usuarios where correos == '"+correo+"' and claves == '"+pass+"'";

     //base.execSQL(misql);
     Cursor cursor = base.rawQuery(misql,null);
        Usuario usuario = new Usuario();
        if (cursor.moveToFirst()){
            //camtuando los datos
            String email = cursor.getString(0);
            String clave = cursor.getString(1);
            if (email.equals(correo) && clave.equals(pass)){
                Intent intent = new Intent(getApplicationContext(),Act2.class);
                startActivityForResult(intent,MAINACTVITY_REQUEST);
            }
            else{
                Toast.makeText(getApplicationContext(),"usuairo incorrecto", Toast.LENGTH_SHORT).show();
            }
        }else {

            Toast.makeText(getApplicationContext(),"correo o contraseña incorrectos",Toast.LENGTH_SHORT).show();
        }
        
    }
    public void onActivityResult(int codActividad, int codResult, Intent data)
    {
        if(codActividad==1){
            if(codResult==RESULT_CANCELED){
                edtUsuario.requestFocus();
                edtUsuario.setText("");
                edtPass.setText("");
            }
        }
    }

}
