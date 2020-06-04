package com.example.bisonapp70;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/*Clase que nos sirve para el mantenimiento de la base de datos, extiende de SQLiteOpenHelper para realizar
altas, bajas, cambios y consultas desde esta clase y hacer uso de los demas componentes.*/

public class DBSQLite extends SQLiteOpenHelper {
    /*Definimos las variables para el nombre de la base de datos y la version, la cual nos servira
     al momento de realizar un cambio en caso de que se desee modificar la estructura de la base de datos.*/
    public static final String nombreBD = "bisonapp";
    public static final int versionBD = 1;

    /*Variables que guardan el script que permite la creación de las tablas, las cuales nos
    servirán para almacenar los datos de los usuarios y las actividades. */
    private static final String tablaActividades = "create table actividades(nombre text primary key, noControl text, materia text," +
            "descripcion text, actividad text, dia text, mes text, ano text, hora text )";
    private static final String tablaUsuarios = "create table usuarios(noControl text primary key ,nombre text, apellido text, password text)";

    /*Constructor de nuestra clase agregando las variables que contienen el nombre y la versión
    de nuestra base de datos.*/
    public DBSQLite(Context context) {
        super(context, nombreBD, null, versionBD);
    }

    /*Se genera el metodo "onCreate", este método se ejecuta automáticamente y crea las tablas que
    conforman nuestra base de datos*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Mediante el metodo "execSQL" ejecutamos las sentencias SQL
        db.execSQL(tablaActividades);
        db.execSQL(tablaUsuarios);

    }

    /*Generamos el metodo onUpgrade, este metodo se lanza unicamente cuando sea necesaria una
    actialización en la estructura de la base de datos o cuando se presente una conversión de datos.*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Se eliminan las versiones anteriores de las tablas si es que existen y se crea una nueva versión
        db.execSQL("drop table if exists actividades");
        db.execSQL(tablaActividades);
        db.execSQL("drop table if exists usuarios");
        db.execSQL(tablaUsuarios);
    }

    /*Metodo que se encarga de agregar registros a la tabla "Usuarios" de la base de datos, para ello
    le asignamos 4 parametros tipo String que sirven para ingresar los datos desde la actividad
     que "Registro".*/
    public void agregarUsuario (String noControl, String nombre, String apellido, String password){
        /*Creamos un objeto (db) de la clase "SQLiteDatabase", luego llamamos al metodo
        "getWritableDatabase()", el cual nos permite trabajar con la base de datos en modo de
        lectura y escritura*/
        SQLiteDatabase db = getWritableDatabase();
        //Verificamos si se abre bien la base de datos
        if(db != null){
            /*Mediante el metodo "execSQL()" ejecutamos una consulta "insert" a la tabla "usuarios"
            agregando los parametros que asignamos en el metodo "agregarUsuario"*/
            db.execSQL("insert into usuarios values('"+ noControl + "','" + nombre + "','" + apellido + "','" + password + "')");
            //Cerramos la conexión con la base de datos
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


    /*Creamos una función tipo List basada en la clase "TareasModelo", la cual nos permite consultar
    los datos agregados en la tabla "actividades"*/
    public List<TareasModelo> mostrarTareas(String control){
        /*Creamos un objeto de la clase "SQLiteDatabase()" y llamamos al metodo "getReadableDatabase()",
        el cual nos permite trabajar con  la base de datos en modo de lectura*/
        SQLiteDatabase db = getReadableDatabase();
        /*Hacemos uso de la clase "Cursor", la cual permite recuperar datos mediante una consulta
        select y mediante el metodo "rawQuery()" indicamos los campos a recuperar y los criterios
                de selección de la consulta*/
        Cursor cursor = db.rawQuery("select * from actividades where noControl = '" + control + "'" , null);
        /*Declaramos una lista basada en la clase "TareasModelo" que se encargará de almacenar todos
        los datos de la consulta SQLite*/
        List<TareasModelo> tareas = new ArrayList<>();
        //Mediante esta condición verificamos si existe al menos un registro
        if(cursor.moveToFirst()){
            do{
                /*Haciendo uso de un do-while recorremos el cursor y agregamos todos los registros que
            se vayan encontrando dentro de la lista "tareas"*/
                tareas.add(new TareasModelo(cursor.getString(4), cursor.getString(0),
                        cursor.getString(2), cursor.getString(3), cursor.getString(5),
                        cursor.getString(6), cursor.getString(7), cursor.getString(8)));
            }while(cursor.moveToNext());
        }
        //Retornamos la lista con los registros ya encontrados
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
