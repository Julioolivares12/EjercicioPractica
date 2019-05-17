package com.example.ejercicioprac;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejercicioprac.Datos.Conexion;
import com.example.ejercicioprac.Datos.ConexionSQLite;

import backend.Usuarios;

public class Act6 extends AppCompatActivity {
    String accion = "", correo="";
    Boolean sinError = true;
    Intent obj;
    EditText edtCorreo;
    Usuarios backendList;
    ConexionSQLite conexionSQLite ;
    SQLiteDatabase base ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act6);
        backendList = Usuarios.getInstance();
        Bundle datos = getIntent().getExtras();
        accion = datos.getString("pAccion");
        edtCorreo = findViewById(R.id.edtCorreo6);

        conexionSQLite = Conexion.getConexion(getApplicationContext());

    }


    public void Buscar(View v){

        correo = edtCorreo.getText().toString();

        if(TextUtils.isEmpty(correo)){
            edtCorreo.requestFocus();
            edtCorreo.setError("Ingresar el correo");
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            edtCorreo.requestFocus();
            edtCorreo.setError("El correo no es valido. Ingrese de nuevo");
        }
        else {
            
            if(accion.equals("editar")){
                obj = new Intent(getApplicationContext(), Act3.class);
                sinError=false;

            }else if(accion.equals("eliminar")){
                obj = new Intent(getApplicationContext(), Act4.class);
                sinError=false;

            }else if(accion.equals("buscar")){

                obj = new Intent(getApplicationContext(), Act4.class);
                sinError=false;
            }else
                sinError=true;

            if(!sinError){
                String miBusqueda = "select * from usuarios where correos == '"+edtCorreo+"'";


                //int index = backendList.getIndiceCorreo(correo);
                /*if(index > -1){
                    obj.putExtra("pCorreo", correo);
                    obj.putExtra("pNombres", backendList.getNombre(index));
                    obj.putExtra("pClave", backendList.getClave(index));
                    obj.putExtra("pTipo", backendList.getNivel(index));
                    obj.putExtra("pAccion", accion);
                    startActivityForResult(obj,4);*/
                obj.putExtra("pCorreo",edtCorreo.getText().toString().trim());
                startActivityForResult(obj,4);

                }else{
                    Toast.makeText(getApplicationContext(), "No se encontro informacion del usuario", Toast.LENGTH_SHORT).show();
                }

            }


    }


    public void onActivityResult(int codActividad, int codResult, Intent data){

        if(codActividad==4){
            if(codResult==RESULT_OK){
                edtCorreo.setText("");
            }
            else
                finish();
        }
    }

    public void Cancelar(View v) {
        finish();
    }
}
