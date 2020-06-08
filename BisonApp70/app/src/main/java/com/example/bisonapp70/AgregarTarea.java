package com.example.bisonapp70;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AgregarTarea extends AppCompatActivity {
    EditText edTxtNombreT, edTxtMateriaT, edTxtDescripcionT, edTxtDiaT, edTxtMesT, edTxtAnoT, edTxtHoraT, edTxtMinT;
    Button btnGuardarT, btnInicio, btnAgregar, btnMostrar, btnBuscar;
    Button btnGuardarN;
    NotificationManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_tarea);

        edTxtNombreT = findViewById(R.id.edTxtNombreT);
        edTxtMateriaT = findViewById(R.id.edTxtMateriaT);
        edTxtDescripcionT = findViewById(R.id.edTxtDescripcionT);
        edTxtDiaT = findViewById(R.id.edTxtDiaT);
        edTxtMesT = findViewById(R.id.edTxtMesT);
        edTxtAnoT = findViewById(R.id.edTxtAnoT);
        edTxtHoraT = findViewById(R.id.edTxtHoraT);
        edTxtMinT = findViewById(R.id.edTxtMinT);
        btnGuardarT = findViewById(R.id.btnGuardarT);
        btnGuardarN = findViewById(R.id.btnGuardarN);

        btnInicio = findViewById(R.id.btnInicio);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnMostrar = findViewById(R.id.btnMostrar);
        btnBuscar = findViewById(R.id.btnBuscar);

        /*Creamos una instancia de la clase "DBSQLite" para hacer uso de los metodos de esa clase,
        los cuales nos permiten dar mantenimiento a la base de datos SQLite*/
        final DBSQLite objDB = new DBSQLite(getApplicationContext());

        manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        btnGuardarT.setOnClickListener(new View.OnClickListener() {
            String noControl = getIntent().getStringExtra("noControl");
            String actividad = "tarea";
            @Override
            public void onClick(View v) {
                //Verificamos si todos los campos fueron llenados
                if(edTxtNombreT.getText().toString().equals("")  || edTxtMateriaT.getText().toString().equals("") ||
                        edTxtDescripcionT.getText().toString().equals("")||
                        edTxtDiaT.getText().toString().equals("")||
                        edTxtMesT.getText().toString().equals("")||
                        edTxtAnoT.getText().toString().equals("")||edTxtHoraT.getText().toString().equals("")){
                    //Agregamos una notificación Toast para inficar que faltan campos por llenar
                    Toast.makeText(getApplicationContext(), "Faltan campos por llenar", Toast.LENGTH_SHORT).show();
                }else{
                    /*Mediante la instancia creada anteriormente llamamos al metodo "agregarActividad()"
                    y dentro de este colocamos los objetos de tipo EditText que se encargan de
                    obtener los datos que se coloquen en el Layout.*/
                    objDB.agregarActividad( edTxtNombreT.getText().toString(), noControl,edTxtMateriaT.getText().toString(),
                            edTxtDescripcionT.getText().toString(), actividad, edTxtDiaT.getText().toString(), edTxtMesT.getText().toString()
                            , edTxtAnoT.getText().toString(), edTxtHoraT.getText().toString());
                    //Agregamos una notificación Toast para ver si los datos fueron agregados correctamente.
                    Toast.makeText(getApplicationContext(), "Se agregó correctamente", Toast.LENGTH_SHORT).show();

                    //Vaciamos los EditText
                    edTxtNombreT.setText("");
                    edTxtMateriaT.setText("");
                    edTxtDescripcionT.setText("");
                    edTxtDiaT.setText("");
                    edTxtMesT.setText("");
                    edTxtAnoT.setText("");
                    edTxtHoraT.setText("");

                    //Finalizamos la actividad
                    finish();
                }
            }
        });

        btnGuardarN.setOnClickListener(new View.OnClickListener() {
            String noControl = getIntent().getStringExtra("noControl");
            String actividad = "tarea";
            @Override
            public void onClick(View v) {



                if(edTxtNombreT.getText().toString().equals("")  || edTxtMateriaT.getText().toString().equals("") ||
                        edTxtDescripcionT.getText().toString().equals("")||
                        edTxtDiaT.getText().toString().equals("")||
                        edTxtMesT.getText().toString().equals("")||
                        edTxtAnoT.getText().toString().equals("")||edTxtHoraT.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Faltan campos por llenar", Toast.LENGTH_SHORT).show();
                }else {
                    objDB.agregarActividad(edTxtNombreT.getText().toString(), noControl, edTxtMateriaT.getText().toString(),
                            edTxtDescripcionT.getText().toString(), actividad, edTxtDiaT.getText().toString(), edTxtMesT.getText().toString()
                            , edTxtAnoT.getText().toString(), edTxtHoraT.getText().toString());
                    Toast.makeText(getApplicationContext(), "Se agregó correctamente", Toast.LENGTH_SHORT).show();

                    int dia4 = Integer.parseInt(edTxtDiaT.getText().toString());
                    int mes4 = (Integer.parseInt(edTxtMesT.getText().toString())) - 1; //enero empieza en 0
                    int ano4 = Integer.parseInt(edTxtAnoT.getText().toString());
                    int hora4 = Integer.parseInt(edTxtHoraT.getText().toString());
                    int min4 = Integer.parseInt(edTxtMinT.getText().toString());


                    Calendar calendar4= Calendar.getInstance();

                    calendar4.set(ano4, mes4, dia4, hora4, min4);
                    Log.wtf("Verificacion", "Año: " + ano4 + " Mes: " + mes4 + " Dia: " + dia4 + " Hora: " + hora4 + " Minutos: " + min4);
                    Long noti = (calendar4.getTimeInMillis());

                    Intent intent = new Intent(AgregarTarea.this, NotificationReceiverE.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(AgregarTarea.this, 0, intent, 0);

                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, noti, pendingIntent);

                    Toast.makeText(getApplicationContext(), "Se ha configurado el recordatorio", Toast.LENGTH_SHORT).show();

                    edTxtNombreT.setText("");
                    edTxtMateriaT.setText("");
                    edTxtDescripcionT.setText("");
                    edTxtDiaT.setText("");
                    edTxtMesT.setText("");
                    edTxtAnoT.setText("");
                    edTxtHoraT.setText("");

                    finish();

                }
            }
        });

        //Cada boton tiene un evento click que nos manda hacia otra actividad
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Inicio.class);
                startActivity(intent);
                finish();
            }
        });
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Mostrar.class);
                startActivity(intent);
                finish();
            }
        });
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Buscar.class);
                startActivity(intent);
                finish();
            }
        });
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Agregar.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
