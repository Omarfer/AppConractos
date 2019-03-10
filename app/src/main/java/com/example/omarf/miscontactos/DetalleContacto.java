package com.example.omarf.miscontactos;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.omarf.miscontactos.Utils.IntentUtils;

public class DetalleContacto extends AppCompatActivity implements View.OnClickListener {

    //Declaramos de manera global los TextViews
    private TextView tvNombre;
    private TextView tvTelefono;
    private TextView tvEmail;
    private IntentUtils intentUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); REVISAR BOTTON UP

        intentUtils = new IntentUtils(this);
        setContentView(R.layout.activity_detalle_contacto);

        //1 TODO: Recibiremos los intent.putExtras de la clase MainActivity
        //Bundle funciona como una especie de receptor de "URLs"(direcciones)
        Bundle parametros = getIntent().getExtras();//En Android los parametros se llaman Extras
        String nombre   = parametros.getString(getResources().getString(R.string.pnombre));//nombre
        String telefono = parametros.getString(getResources().getString(R.string.ptelefono));//telefono
        String email    = parametros.getString(getResources().getString(R.string.pemail));//email

        //2 TODO: Conectamos con las id requeridas de "activity_detalle_contacto.xml" haciendo Casteo
        tvNombre   = findViewById(R.id.tvNombre);
        tvTelefono = findViewById(R.id.tvTelefono);
        tvEmail    = findViewById(R.id.tvEmail);

        //3 TODO: Con setText Empezamos a meter en los TextView de la vista(2), lo que recibimos del MainActivity(1)
        tvNombre.setText(nombre);
        tvTelefono.setText(telefono);
        tvEmail.setText(email);

        tvEmail.setOnClickListener(this);
        tvTelefono.setOnClickListener(this);

        //TODO hasta aquí ya funciona para poder ver un detalle correspondiente a su item seleccionado
    }


    //4 TODO: EMPEZAREMOS a implementar los INTENTS IMPLICITOS!:
    //Ejecutamos llamadas y email:
    public void onClick(View v){
        switch (v.getId()){
            case R.id.tvTelefono:
                String telefono = tvTelefono.getText().toString();
                intentUtils.intentCall(getString(R.string.contacto_telefono_valor_format, telefono));
                break;

            case R.id.tvEmail:
                String email = tvEmail.getText().toString();
                intentUtils.intentEmail(email, getString(R.string.contacto_email_subject), getString(R.string.contacto_email_body), getString(R.string.contacto_email_title));
                break;
        }
    }

    //TODO: Usar esto en caso de usar un finish() en Intent en el MainAcivity, para regresar
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(DetalleContacto.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
