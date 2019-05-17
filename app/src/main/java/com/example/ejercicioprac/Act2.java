package com.example.ejercicioprac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Act2 extends AppCompatActivity {
    Intent obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act2);
    }

    //nuevo
    public void NuevoAct3(View v) {

        obj = new Intent(getApplicationContext(),Act3.class);
        obj.putExtra("pAccion", "nuevo");
        startActivityForResult(obj, 2);
    }

    //buscar
    public void BuscarAct6(View v){
        obj = new Intent(getApplicationContext(),Act6.class);
        obj.putExtra("pAccion", "buscar" );
        startActivityForResult(obj, 2);
    }
    //editar
    public void EditarAct6(View v){
        obj = new Intent(getApplicationContext(),Act6.class);

        //pasar datos
        obj.putExtra("pAccion", "editar" );
        startActivityForResult(obj, 2);
    }
    //eliminar
    public void EliminarAct6(View v){
        obj = new Intent(getApplicationContext(),Act6.class);
        obj.putExtra("pAccion", "eliminar" );
        startActivityForResult(obj, 2);
    }
    //listado
    public void ListadoAct5(View v){
        obj = new Intent(getApplicationContext(),Act5.class);

        startActivity(obj);
    }

    //preguntar al usuario antes de salir
    public void Salir(View v){
        obj = new Intent();
        setResult(RESULT_CANCELED, obj);
        Toast.makeText(getApplicationContext(), "Saliendo del sistema..." , Toast.LENGTH_LONG).show();
        finish();
    }
}
