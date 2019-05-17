package com.example.ejercicioprac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Act3 extends AppCompatActivity {

    EditText edtNombres, edtCorreo, edtClave, edtConfirmar;
    RadioButton rdbUsuario, rdbAdmin, rdbAsistente;
    Button btnGuardar, btnCancelar;
    String nombres="", correo = "", clave ="", confirmar="", tipo="", accion="", textBtn1="", textBtn2="";

    Intent objAct4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act3);

        Bundle datos = getIntent().getExtras();

        edtNombres = findViewById(R.id.edtNombre);
        edtCorreo = findViewById(R.id.edtCorreo);
        edtClave = findViewById(R.id.edtClave);
        edtConfirmar = findViewById(R.id.edtConfirmar);

        rdbUsuario = findViewById(R.id.rdbUsuario);
        rdbAsistente = findViewById(R.id.rdbAsistente);
        rdbAdmin = findViewById(R.id.rdbAdmin);

        btnGuardar = findViewById(R.id.btnGuardar);
        btnCancelar = findViewById(R.id.btnCancelar);

        setDatos(datos);
        edtCorreo.setText(correo);
        edtNombres.setText(nombres);
        edtClave.setText(clave);
        edtConfirmar.setText(confirmar);
        btnGuardar.setText(textBtn1);
        btnCancelar.setText(textBtn2);
    }

    public void guardar(View v)
    {
        nombres = edtNombres.getText().toString();
        correo = edtCorreo.getText().toString();
        clave = edtClave.getText().toString();
        confirmar = edtConfirmar.getText().toString();

        if(TextUtils.isEmpty(nombres)){
            edtNombres.requestFocus();
            edtNombres.setError("Ingrese el nombre");
        }
        else if(TextUtils.isEmpty(correo)){
            edtCorreo.requestFocus();
            edtCorreo.setError("Ingresar el correo");
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            edtCorreo.requestFocus();
            edtCorreo.setError("El correo no es valido. Ingrese de nuevo");
        }
        else if(TextUtils.isEmpty(clave)){
            edtClave.requestFocus();
            edtClave.setError("Debe ingresar la clave");
        }
        else if (TextUtils.isEmpty(confirmar)){
            edtConfirmar.requestFocus();
            edtConfirmar.setError("Debe confirmar la clave");
        }
        else if(!clave.equals(confirmar)){
            edtClave.requestFocus();
            edtClave.setError("Las claves ingresadas no son iguales. Ingrese de nuevo");
            edtClave.setText("");
            edtConfirmar.setText("");
        }
        else {

            if(rdbUsuario.isChecked()){
                tipo="Usuario";
            }
            else if(rdbAsistente.isChecked()){
                tipo="Asistente";
            }else {
                tipo="Administrador";
            }

            objAct4 = new Intent(getApplicationContext(), Act4.class);

            //pasar datos
            objAct4.putExtra("pNombres", nombres);
            objAct4.putExtra("pCorreo", correo);
            objAct4.putExtra("pClave", clave);
            objAct4.putExtra("pTipo", tipo);
            objAct4.putExtra("pAccion", accion);

            startActivityForResult(objAct4,1);

        }
    }

    public void onActivityResult(int codActividad, int codResult, Intent data)
    {
        if(codActividad==1){
            if(codResult==RESULT_CANCELED){
               nombres=data.getStringExtra("nombres");
               correo = data.getStringExtra("correo");
               clave= data.getStringExtra("clave");
               confirmar = data.getStringExtra("clave");
            }
            else
                finish();
        }
    }

    public  void cancelar(View v)
    {
        setResult(RESULT_OK, objAct4);
        finish();

    }

    public void setDatos(Bundle pDatos)
    {
        accion =pDatos.getString("pAccion");

        if(accion.equals("editar"))
        {
            nombres=pDatos.getString("pNombres");
            correo = pDatos.getString("pCorreo");
            clave =pDatos.getString("pClave");
            confirmar=pDatos.getString("pClave");
            tipo = pDatos.getString("pTipo");
            edtCorreo.setEnabled(false);
            textBtn1="Guardar Cambios";
            textBtn2 = "Cancelar";

        }else if(accion.equals("listado")){
            nombres=pDatos.getString("pNombres");
            correo = pDatos.getString("pCorreo");
            clave =pDatos.getString("pClave");
            confirmar=pDatos.getString("pClave");
            tipo = pDatos.getString("pTipo");
            edtCorreo.setEnabled(false);
            textBtn1="Guardar Cambios";
            textBtn2 = "Cancelar";
        }else {
            accion="nuevo";
            textBtn1="Guardar";
            textBtn2 = "Cancelar";
        }
    }
}
