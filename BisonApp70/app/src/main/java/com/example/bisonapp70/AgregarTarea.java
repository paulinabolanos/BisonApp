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
    Button btnGuardarT;
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

        final DBSQLite objDB = new DBSQLite(getApplicationContext());

        manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        btnGuardarT.setOnClickListener(new View.OnClickListener() {
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
                }else{
                    objDB.agregarActividad( edTxtNombreT.getText().toString(), noControl,edTxtMateriaT.getText().toString(),
                            edTxtDescripcionT.getText().toString(), actividad, edTxtDiaT.getText().toString(), edTxtMesT.getText().toString()
                            , edTxtAnoT.getText().toString(), edTxtHoraT.getText().toString());
                    Toast.makeText(getApplicationContext(), "Se agregó correctamente", Toast.LENGTH_SHORT).show();

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
    }
}
