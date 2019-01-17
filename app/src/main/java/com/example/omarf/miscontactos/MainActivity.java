package com.example.omarf.miscontactos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contacto> contactos; //1 TODO: Declaramos nuestro ArrayList que tiene su Modelo Contacto

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactos = new ArrayList<Contacto>(); //2 TODO: INSTANCIAMOS

        //3 TODO: Agregamos campos al ArrayList, según minimo requerimientos del constructor de Contacto.
        contactos.add(new Contacto("Anahí Salagdo", "987654679", "anahi@gmail.com"));
        contactos.add(new Contacto("Omar Fernando", "973984497", "omarfer007@gmail.com"));
        contactos.add(new Contacto("Cindy Lorena", "987654679", "cindy@gmail.com"));
        contactos.add(new Contacto("Sandra García", "987654679", "samorales@gmail.com"));
        contactos.add(new Contacto("Mariangela Ramírez", "987654679", "mariangela@gmail.com"));
        contactos.add(new Contacto("Reem Alghamdi", "987654679", "remokshine@gmail.com"));

        ArrayList<String> nombresContacto = new ArrayList<>();
        //6 TODO: creamos un for each para indicar qué valores asignaremos de contactos al simple_list_itme_1
        //mediante el ArrayList<String> //Relación con linea 40
        for (Contacto contacto : contactos) {
            nombresContacto.add(contacto.getNombre());//Agarramos los Nombres
        }

        //4 TODO: Hacemos el casteo, encontramos el id del ListView lstContactos
        ListView lstContactos = (ListView) findViewById(R.id.lstContactos);

        //5 TODO: Para llenar una lista de contactos necesitamos un Adaptador, este adapter recibe un arreglo(ArrayAdpter)
        //Android nos provee el layout simple_list_item_1, y le asignamos un nombresContacto x item (usamos el for each)
        lstContactos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombresContacto));

        //TODO: EMPEZAREMOS a implementar los INTENTS EXPLICITOS!:
        // con setOnItemClickListener agarramos el Item: su posición y ID en el ListView.
        lstContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //La lista comienza a agarrar desde el item 0
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Creamos el Intent
                Intent intent = new Intent(MainActivity.this, DetalleContacto.class);
                //Agarramos los parametros anteriormente recogidos con position y los mandamos con un putExtra x c/valor
                intent.putExtra(getResources().getString(R.string.pnombre),contactos.get(position).getNombre());
                intent.putExtra(getResources().getString(R.string.ptelefono),contactos.get(position).getTelefono());
                intent.putExtra(getResources().getString(R.string.pemail),contactos.get(position).getEmail());
                startActivity(intent);//Iniciamos el Intent
                //Con finish finalizamos la actividad cuando se entra a una nueva, esto optimiza memoria.
                finish();//PERO ROMPE LA LINEA DE TAREAS, para esto deberás usar en el Detalle onKeyDowm para poder regresar
                //TODO ESTO SERÁ RECIBIDO POR LA CLASE "DetalleContacto.java"
            }

        });

    }
}
