package com.example.ejercicioprac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import backend.Usuarios;

public class Act5 extends AppCompatActivity {

    ListView lista;
    Intent obj;
    Usuarios backendList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act5);
        backendList = Usuarios.getInstance();
        final ListView lista = findViewById(R.id.lvLista);
        String[] correos = Arrays.copyOf(backendList.getAllCorreos(), backendList.getAllCorreos().length, String[].class);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, correos);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int item = position;
                String valorItem = String.valueOf(lista.getItemAtPosition(position));
                int index = backendList.getIndiceCorreo(valorItem);
                obj = new Intent(getApplicationContext(), Act3.class);
                obj.putExtra("pCorreo", valorItem);
                obj.putExtra("pNombres", backendList.getNombre(index));
                obj.putExtra("pClave", backendList.getClave(index));
                obj.putExtra("pTipo", backendList.getNivel(index));
                obj.putExtra("pAccion", "listado");
                startActivityForResult(obj,4);
                finish();
            }
        });

    }

    public void onActivityResult(int codActividad, int codResult, Intent data)
    {
        if(codActividad==4){
            if(codResult==RESULT_OK){
                finish();
            }


        }
    }
}
