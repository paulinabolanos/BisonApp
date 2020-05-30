package com.example.bisonapp70;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TareasAdaptador extends RecyclerView.Adapter<TareasAdaptador.ViewHolder>  {




    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtVwActividad, txtVwNombre,txtVwMateria, txtVwDescripcion, txtVwDia, txtVwMes, txtVwAno, txtVwHora;
        Button btnEditar, btnEliminar;
        Context context;
        ImageView imgVw;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            txtVwActividad = itemView.findViewById(R.id.txtVwActividad);
            txtVwNombre = itemView.findViewById(R.id.txtVwNombre);
            txtVwMateria = itemView.findViewById(R.id.txtVwMateria);
            txtVwDescripcion = itemView.findViewById(R.id.txtVwDescripcion);
            txtVwDia = itemView.findViewById(R.id.txtVwDia);
            txtVwMes = itemView.findViewById(R.id.txtVwMes);
            txtVwAno = itemView.findViewById(R.id.txtVwAno);
            txtVwHora = itemView.findViewById(R.id.txtVwHora);

            //imgVw = itemView.findViewById(R.id.imgVw);
        }


    }
    public List<TareasModelo> tareaLista;

    public  TareasAdaptador (List<TareasModelo> tareaLista){
        this.tareaLista = tareaLista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.actividad, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.txtVwActividad.setText(tareaLista.get(position).getActividad());
        holder.txtVwNombre.setText(tareaLista.get(position).getNombre());
        holder.txtVwMateria.setText(tareaLista.get(position).getMateria());
        holder.txtVwDescripcion.setText(tareaLista.get(position).getDescripcion());
        holder.txtVwDia.setText(tareaLista.get(position).getDia());
        holder.txtVwMes.setText(tareaLista.get(position).getMes());
        holder.txtVwAno.setText(tareaLista.get(position).getAno());
        holder.txtVwHora.setText(tareaLista.get(position).getHora());





    }


    @Override
    public int getItemCount() {
        return tareaLista.size();
    }

}
