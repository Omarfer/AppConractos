package com.example.omarf.miscontactos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//2 TODO: Se extiende RecyclerView.Adapter<Ubicación del VierwHolder>
public class ContactoAdaptador extends RecyclerView.Adapter<ContactoAdaptador.ContactoViewHolder> {
    //Traemos el mopdelo Contacto
    ArrayList<Contacto> contactos;

    //Constructor de ContactoAdaptador
    public ContactoAdaptador(ArrayList<Contacto> contactos){
        this.contactos = contactos;
    }

    // 3 TODO: Y se debe crear(GENERAR) sus 3 constructores!!
    @NonNull
    @Override
    //Inflamos el layout y lo pasará al viewholder para que él obtenga los views
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_contacto, parent, false);
        return new ContactoViewHolder(v);
    }

    @Override
    //Asocia cada elemento de la lista con cada view
    public void onBindViewHolder(@NonNull ContactoViewHolder contactoViewHolder, int position) {
        Contacto contacto = contactos.get(position);
        contactoViewHolder.imgFoto.setImageResource(contacto.getFoto());
        contactoViewHolder.tvNombreCV.setText(contacto.getNombre());
        contactoViewHolder.tvTelefonoCV.setText(contacto.getTelefono());
    }

    @Override
    public int getItemCount() { //Cantidad de contactos que tiene mi lista
        return contactos.size();
    }


    //1 TODO: Creamos una clase ViewHolder estática, y heredamos RecyclerView,ViewHolder para tener nuestros objetos.
    public static class ContactoViewHolder extends RecyclerView.ViewHolder {
        //Declarar los views
        private ImageView imgFoto;
        private TextView tvNombreCV;
        private TextView tvTelefonoCV;

        //Necesitamos un Contructor con el mismo nombre de la superclase
        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            //Casteo:
            imgFoto      = itemView.findViewById(R.id.imgFoto);
            tvNombreCV   = itemView.findViewById(R.id.tvNombreCV);
            tvTelefonoCV = itemView.findViewById(R.id.tvTelefonoCV);
        }
    }
}
