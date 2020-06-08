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

//Esta clase tiene la funcion de comunicar al reclyclerView de nuestro layout la cantidad de informacion que se utilizara para llenar cada item de nuestra ista
//Actua como un puiente entre la informacion y la presentacion a mostrar
//Se crea el sdaptador a traves de "RecyclerView.Adapter<TareasAdaptador.ViewHolder>"
public class TareasAdaptador extends RecyclerView.Adapter<TareasAdaptador.ViewHolder>  {
//Los adaptadores para el RecyclerView deben de contener una clase interna que extienda de "RecyclerView.ViewHolder"


    public static class ViewHolder extends RecyclerView.ViewHolder {
        //Se crean las variables necesarias
        private TextView txtVwActividad, txtVwNombre,txtVwMateria, txtVwDescripcion, txtVwDia, txtVwMes, txtVwAno, txtVwHora;

        Context context;
        ImageView imgVw;
        //Metodo constructor de la clase interna y vinculamos cada una de los elementos

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
            imgVw = itemView.findViewById(R.id.imgVw);

        }


    }
    //Creamos una variable tipo lista para almacenar todos los datos mostrados en cada item
    public List<TareasModelo> tareaLista;

    //Metodo constructos del adapotador el cual recibira como parametro la lista creada
    public  TareasAdaptador (List<TareasModelo> tareaLista){
        this.tareaLista = tareaLista;
    }

    @NonNull
    @Override
    //El siguiente metodo es el encargado de inflar el contenido de un nuevo item para la lista
    //Inflar -- procedimiento que se realiza para hacer uso de un layout dentro de otro layout
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.actividad, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;


    }

    @Override
    //Este metodo realiza las modificaciones del contenido para cada item
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.txtVwActividad.setText(tareaLista.get(position).getActividad());
        holder.txtVwNombre.setText(tareaLista.get(position).getNombre());
        holder.txtVwMateria.setText(tareaLista.get(position).getMateria());
        holder.txtVwDescripcion.setText(tareaLista.get(position).getDescripcion());
        holder.txtVwDia.setText(tareaLista.get(position).getDia());
        holder.txtVwMes.setText(tareaLista.get(position).getMes());
        holder.txtVwAno.setText(tareaLista.get(position).getAno());
        holder.txtVwHora.setText(tareaLista.get(position).getHora());

        String actividad = tareaLista.get(position).getActividad();
        if(actividad.equals("tarea")){
            holder.imgVw.setImageResource(R.drawable.tarea);
        }else if (actividad.equals("proyecto")){
            holder.imgVw.setImageResource(R.drawable.proyecto);
        }else if (actividad.equals("examen")){
            holder.imgVw.setImageResource(R.drawable.examen);
        }


    }

    //metodo que le permite determinar al adaptador la candidad de elementos que se procesaran
    @Override
    public int getItemCount() {
        return tareaLista.size();
    }

}
