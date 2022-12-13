package com.example.examen_noviembre_2022;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Ganadores_Adapter extends RecyclerView.Adapter<Ganadores_Adapter.Ganadores_AdapterHolder>{
    List<Ganadores> ganadores;
    Context c;
    @NonNull
    @Override
    public Ganadores_Adapter.Ganadores_AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        c=parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ganadores_recycler, parent, false);
        return  new Ganadores_AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Ganadores_Adapter.Ganadores_AdapterHolder holder, int position) {
        holder.imprimir(position);
    }

    public Ganadores_Adapter(List<Ganadores> ganadores){
        this.ganadores = ganadores;
    }

    @Override
    public int getItemCount() {
        return ganadores.size();
    }

    public class Ganadores_AdapterHolder extends RecyclerView.ViewHolder{
        TextView id, Nombre, txtCantidad;
        ImageView ivurl;

        public Ganadores_AdapterHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            Nombre = itemView.findViewById(R.id.Nombre);
            txtCantidad = itemView.findViewById(R.id.txtCantidad);
            ivurl = itemView.findViewById(R.id.ivurl);
        }

        public void imprimir (int position){
            id.setText("id: "+ganadores.get(position).getId());
            Nombre.setText("nombre: "+ganadores.get(position).getNombre());
            txtCantidad.setText("numero: "+ganadores.get(position).getNumero());
            //String[] parts = ganadores.get(position).getUrlImg().split("/");

            //String imageUri = ganadores.get(position).getUrlImg();

            Picasso.with(c).load("https://fer-uig.glitch.me/"+ganadores.get(position).getNombre()).into(ivurl);
        }
    }
}
