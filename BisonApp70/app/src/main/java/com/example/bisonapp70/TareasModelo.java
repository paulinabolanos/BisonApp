package com.example.bisonapp70;

import android.widget.ImageView;

//Clase que nos sirve para representar a la actividad y sus datos
public class TareasModelo {
    //Variabes necesarias que son los datos de las actividades
    String actividad, nombre, materia, descripcion, dia, mes, ano, hora;

    //Contructores
    public TareasModelo() {
    }

    public TareasModelo(String actividad, String nombre, String materia, String descripcion, String dia, String mes,
                        String ano, String hora) {
        this.actividad = actividad;
        this.nombre = nombre;
        this.materia = materia;
        this.descripcion = descripcion;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.hora = hora;
    }

    //Metodos Get and set
    public String getActividad() {
        return actividad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMateria() {
        return materia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDia() {
        return dia;
    }

    public String getMes() {
        return mes;
    }

    public String getAno() {
        return ano;
    }

    public String getHora() {
        return hora;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
