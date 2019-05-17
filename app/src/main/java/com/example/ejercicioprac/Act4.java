package com.example.ejercicioprac;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejercicioprac.Datos.Conexion;
import com.example.ejercicioprac.Datos.ConexionSQLite;
import com.example.ejercicioprac.Datos.Crud;

import backend.Usuarios;

public class Act4 extends AppCompatActivity {

    TextView tvNombre, tvCorreo, tvClave, tvTipo;
    String nombres="", correo="", clave="", tipo="", accion="", textBtn1="", textBtn2="";
    Button btnAccion, btnCancel;
    Intent objRetorno = new Intent();
    Usuarios backendList;
    ConexionSQLite conexionSQLite ;
    SQLiteDatabase base ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act4);
        backendList = Usuarios.getInstance();
        Bundle datos = getIntent().getExtras();

        conexionSQLite = Conexion.getConexion(getApplicationContext());
        base = conexionSQLite.getWritableDatabase();
        tvNombre = findViewById(R.id.tvNombre);
        tvCorreo = findViewById(R.id.tvCorreo);
        tvClave = findViewById(R.id.tvClave);
        tvTipo = findViewById(R.id.tvTipo);
        btnAccion = findViewById(R.id.btnConfirmar);
        btnCancel = findViewById(R.id.btnCancelar);
        setDatos(datos);

        tvNombre.setText("Nombre: " + nombres);
        tvCorreo.setText("Correo: " + correo);
        tvClave.setText("Clave: " + clave);
        tvTipo.setText("Tipo: " + tipo);
        btnAccion.setText(textBtn1);
        btnCancel.setText(textBtn2);

    }

    public void confirmar(View v){

        if(accion.equals("buscar")){
            Toast.makeText(getApplicationContext(), "Regresando a buscar...", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK, objRetorno);
            finish();

        }else if(accion.equals("eliminar")){
            Crud.eliminar(base,"usuarios","correos",correo);
            
            Toast.makeText(getApplicationContext(), "Datos eliminados correctamente", Toast.LENGTH_LONG).show();
            setResult(RESULT_CANCELED, objRetorno);
            finish();

        }else if(accion.equals("nuevo")){
            if( backendList.getIndiceCorreo(correo)> -1){
                Toast.makeText(getApplicationContext(), "Este correo ya existe", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), "Datos almacenados correctamente", Toast.LENGTH_LONG).show();
                setResult(RESULT_OK, objRetorno);
                backendList.setClave(clave);
                backendList.setCorreo(correo);
                backendList.setNivel(tipo);
                backendList.setNombre(nombres);
                finish();
            }
        }else if(accion.equals("editar")){
            int index = backendList.getIndiceCorreo(correo);
            backendList.setCorreo(index,correo);
            backendList.setNombre(index,nombres);
            backendList.setNivel(index,tipo);
            backendList.setClave(index,clave);
            Toast.makeText(getApplicationContext(), "Datos modificados correctamente", Toast.LENGTH_LONG).show();
            setResult(RESULT_OK, objRetorno);
            finish();

        }else if(accion.equals("listado")){
            int index = backendList.getIndiceCorreo(correo);
            backendList.setCorreo(index,correo);
            backendList.setNombre(index,nombres);
            backendList.setNivel(index,tipo);
            backendList.setClave(index,clave);
            Toast.makeText(getApplicationContext(), "Datos modificados correctamente", Toast.LENGTH_LONG).show();
            setResult(RESULT_OK, objRetorno);
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(), "Sin Informacion. Intente mas tarde", Toast.LENGTH_LONG).show();
            finish();
        }


    }
    //Para cancelar y si es buscar que regrese al menu
    public void cancelar(View v){

        if (accion.equals("buscar")){

            setResult(RESULT_CANCELED, objRetorno);
            finish();

        }else if(accion.equals("eliminar")){
            setResult(RESULT_OK, objRetorno);
            finish();

        }else if(accion.equals("nuevo")) {
            objRetorno.putExtra("nombres", nombres);
            objRetorno.putExtra("correo", correo);
            objRetorno.putExtra("clave", clave);
            objRetorno.putExtra("tipo", tipo);
            setResult(RESULT_CANCELED, objRetorno);
            finish();
        }
        else if(accion.equals("editar")){
            objRetorno.putExtra("nombres", nombres);
            objRetorno.putExtra("correo", correo);
            objRetorno.putExtra("clave", clave);
            objRetorno.putExtra("tipo", tipo);
            setResult(RESULT_CANCELED, objRetorno);
            finish();
        }
        else if(accion.equals("listado")){
            objRetorno.putExtra("nombres", nombres);
            objRetorno.putExtra("correo", correo);
            objRetorno.putExtra("clave", clave);
            objRetorno.putExtra("tipo", tipo);
            setResult(RESULT_CANCELED, objRetorno);
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(), "Sin Informacion. Intente mas tarde", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void setDatos(Bundle pDatos ){

        accion =pDatos.getString("pAccion");

        if(accion.equals("buscar")){
            nombres=pDatos.getString("pNombres");
            correo = pDatos.getString("pCorreo");
            clave =pDatos.getString("pClave");
            tipo = pDatos.getString("pTipo");
            textBtn1="Regresar";
            textBtn2="Ir al menu";

        }else if(accion.equals("eliminar")){
            nombres=pDatos.getString("pNombre");
            correo = pDatos.getString("pCorreo");
            clave =pDatos.getString("pClave");
            tipo = pDatos.getString("pTipo");
            textBtn1="Confirmar para eliminar";
            textBtn2="Cancelar";
        }
        else if(accion.equals("nuevo")){
            nombres = pDatos.getString("pNombres");
            correo = pDatos.getString("pCorreo");
            clave = pDatos.getString("pClave");
            tipo = pDatos.getString("pTipo");
            textBtn1="Confirmar";
            textBtn2="Cancelar";
        }else if(accion.equals("editar")){
            nombres = pDatos.getString("pNombres");
            correo = pDatos.getString("pCorreo");
            clave = pDatos.getString("pClave");
            tipo = pDatos.getString("pTipo");
            textBtn1="Confirmar Cambios";
            textBtn2="Cancelar";
        }
        else if(accion.equals("listado")){
            nombres = pDatos.getString("pNombres");
            correo = pDatos.getString("pCorreo");
            clave = pDatos.getString("pClave");
            tipo = pDatos.getString("pTipo");
            textBtn1="Confirmar Cambios";
            textBtn2="Cancelar";
        }
        else
            Toast.makeText(getApplicationContext(), "No se puede cargar la informacion", Toast.LENGTH_LONG).show();

    }
}
