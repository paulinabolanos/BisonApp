package com.example.bisonapp70;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBSQLite extends SQLiteOpenHelper {
    public static final String nombreBD = "bisonapp";
    public static final int versionBD = 1;

    private static final String tablaActividades = "create table actividades(nombre text primary key, noControl text, materia text," +
            "descripcion text, actividad text, dia text, mes text, ano text, hora text )";
    private static final String tablaUsuarios = "create table usuarios(noControl text primary key ,nombre text, apellido text, password text)";

    public DBSQLite(Context context) {
        super(context, nombreBD, null, versionBD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tablaActividades);
        db.execSQL(tablaUsuarios);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists actividades");
        db.execSQL(tablaActividades);
        db.execSQL("drop table if exists usuarios");
        db.execSQL(tablaUsuarios);
    }

    public void agregarUsuario (String noControl, String nombre, String apellido, String password){
        SQLiteDatabase db = getWritableDatabase();
        if(db != null){
            db.execSQL("insert into usuarios values('"+ noControl + "','" + nombre + "','" + apellido + "','" + password + "')");
            db.close();
        }
    }

    public int login (String noControl, String password){
        SQLiteDatabase db = getReadableDatabase();
        int i = 0;
        Cursor cursor = db.rawQuery("select * from usuarios", null);
        if(cursor != null && cursor.moveToFirst()){
            do{
                if(cursor.getString(0).equals(noControl) && cursor.getString(3).equals(password)){
                    i++;
                }
            }while(cursor.moveToNext());
        }
        return i;
    }

    public void agregarActividad(String nombre, String noControl, String materia, String descripcion, String actividad, String dia,
                                 String mes, String ano, String hora){
        SQLiteDatabase db = getWritableDatabase();

        if(db != null){
            db.execSQL("insert into actividades values('"+ nombre + "','" + noControl +  "','" + materia + "','" +
                    descripcion + "','" + actividad + "','"  + dia  + "','" + mes + "','" + ano + "','" + hora +"')");
            db.close();
        }
    }


    public List<TareasModelo> mostrarTareas(String control){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from actividades where noControl = '" + control + "'" , null);
        List<TareasModelo> tareas = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                tareas.add(new TareasModelo(cursor.getString(4), cursor.getString(0),
                        cursor.getString(2), cursor.getString(3), cursor.getString(5),
                        cursor.getString(6), cursor.getString(7), cursor.getString(8)));
            }while(cursor.moveToNext());
        }
        return tareas;
    }

    public void editarTarea (String nombre, String materia, String descripcion, String dia, String mes, String ano, String hora){
        SQLiteDatabase db = getWritableDatabase();
        if(db != null){
            db.execSQL("update actividades set nombre='" + nombre + "',materia ='" + materia + "',descripcion ='" + descripcion +
                    "',dia ='" + dia +  "',mes ='" + mes +  "',ano ='" + ano +
                    "',hora ='" + hora + "'where nombre = '" + nombre + "'");
            db.close();
        }
    }
    public void eliminarTarea (String nombre){
        SQLiteDatabase db = getWritableDatabase();
        if(db != null){
            db.execSQL("delete from actividades where nombre ='" + nombre + "'");
            db.close();
        }
    }

    public void buscarActividad(TareasModelo actividades, String nombre){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from actividades where nombre ='" + nombre + "'" , null);
        if(cursor.moveToFirst()){
            do{
                actividades.setMateria(cursor.getString(2));
                actividades.setDescripcion(cursor.getString(3));
                actividades.setActividad(cursor.getString(4));
                actividades.setDia(cursor.getString(5));
                actividades.setMes(cursor.getString(6));
                actividades.setAno(cursor.getString(7));
                actividades.setHora(cursor.getString(8));

            }while(cursor.moveToNext());
        }
    }


}
