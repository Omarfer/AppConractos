package com.example.omarf.miscontactos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class DetalleContacto extends AppCompatActivity {

    //Declaramos de manera global los TextViews
    private TextView tvNombre;
    private TextView tvTelefono;
    private TextView tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        //TODO hasta aquí ya funciona para poder ver un detalle correspondiente a su item seleccionado
    }


    //4 TODO: EMPEZAREMOS a implementar los INTENTS IMPLICITOS!:

    //Creamos un método para ejecutar una llamada
    public void llamar(View v){
        String telefono = tvTelefono.getText().toString();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case whese the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telefono)));
    }

    //Creamos Metodo para Envar Email
    public void enviarMail(View v){
        String email = tvEmail.getText().toString();
        Intent emailIntent = new Intent((Intent.ACTION_SEND));
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, email);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Email "));
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
