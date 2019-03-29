package com.example.omarf.miscontactos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**2: Se extiende RecyclerView.Adapter<Ubicación del VierwHolder> **/
public class ContactoAdaptador extends RecyclerView.Adapter<ContactoAdaptador.ContactoViewHolder> {
    //Traemos el mopdelo Contacto
    ArrayList<Contacto> contactos;
    Activity activity;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    //Constructor de ContactoAdaptador
    public ContactoAdaptador(ArrayList<Contacto> contactos, Activity activity){
        this.contactos = contactos;
        this.activity = activity;
    }

    /** 3: Y se debe crear(GENERAR) sus 3 constructores!! **/
    @NonNull
    @Override
    //Inflamos el layout y lo pasará al viewholder para que él obtenga los views
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_contacto, parent, false);
        return new ContactoViewHolder(v);
    }

    @Override
    //Asocia cada elemento de la lista con cada view
    public void onBindViewHolder(@NonNull final ContactoViewHolder contactoViewHolder, int position) {
        final Contacto contacto = contactos.get(position);
        contactoViewHolder.imgFoto.setImageResource(contacto.getFoto());
        contactoViewHolder.tvNombreCV.setText(contacto.getNombre());
        contactoViewHolder.tvTelefonoCV.setText(contacto.getTelefono());

        contactoViewHolder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, DetalleContacto.class);
                i.putExtra("nombre", contacto.getNombre());
                i.putExtra("telefono", contacto.getTelefono());
                i.putExtra("email", contacto.getEmail());
                activity.startActivity(i);
            }
        });

        contactoViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Diste Like a " + contacto.getNombre(), Toast.LENGTH_SHORT).show();
            }
        });

        contactoViewHolder.cvContacto.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(contactoViewHolder.getAdapterPosition());
                return false;
            }
        });

    }

    @Override
    public int getItemCount() { //Cantidad de contactos que tiene mi lista
        return contactos.size();
    }


    /** 1: Creamos una clase ViewHolder estática, y heredamos RecyclerView,ViewHolder para tener nuestros objetos.**/
    public static class ContactoViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        //Declarar los views
        private ImageView imgFoto;
        private TextView tvNombreCV;
        private TextView tvTelefonoCV;
        private ImageButton btnLike;
        private CardView cvContacto;

        //Necesitamos un Contructor con el mismo nombre de la superclase
        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            //Casteo:
            imgFoto      = itemView.findViewById(R.id.imgFoto);
            tvNombreCV   = itemView.findViewById(R.id.tvNombreCV);
            tvTelefonoCV = itemView.findViewById(R.id.tvTelefonoCV);
            btnLike      = itemView.findViewById(R.id.btnLike);
            cvContacto   = itemView.findViewById(R.id.cvContacto);

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(0,R.id.mEditar,0,"Editar");
            menu.add(0,R.id.mEliminar,0,"Eliminar");
        }
    }
}
