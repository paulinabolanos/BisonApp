package com.example.bisonapp70;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AgregarExamen extends AppCompatActivity {
    EditText edTxtNombreE, edTxtMateriaE, edTxtDescripcionE, edTxtDiaE, edTxtMesE, edTxtAnoE, edTxtHoraE, edTxtMinE;
    Button btnGuardarE;
    Button btnGuardarN;
    NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_examen);

        edTxtNombreE = findViewById(R.id.edTxtNombreE);
        edTxtMateriaE = findViewById(R.id.edTxtMateriaE);
        edTxtDescripcionE = findViewById(R.id.edTxtDescripcionE);
        edTxtDiaE = findViewById(R.id.edTxtDiaE);
        edTxtMesE = findViewById(R.id.edTxtMesE);
        edTxtAnoE = findViewById(R.id.edTxtAnoE);
        edTxtHoraE = findViewById(R.id.edTxtHoraT);
        btnGuardarE = findViewById(R.id.btnGuardarT);
        btnGuardarN = findViewById(R.id.btnGuardarN);
        edTxtMinE = findViewById(R.id.edTxtMinT);

        manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        final DBSQLite objDB = new DBSQLite(getApplicationContext());

        btnGuardarE.setOnClickListener(new View.OnClickListener() {
            String noControl = getIntent().getStringExtra("noControle");
            String actividad = "examen";
            @Override
            public void onClick(View v) {
                if(edTxtNombreE.getText().toString().equals("")  || edTxtMateriaE.getText().toString().equals("") ||
                        edTxtDescripcionE.getText().toString().equals("")||
                        edTxtDiaE.getText().toString().equals("")||
                        edTxtMesE.getText().toString().equals("")||
                        edTxtAnoE.getText().toString().equals("")||edTxtHoraE.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Faltan campos por llenar", Toast.LENGTH_SHORT).show();
                }else{
                    objDB.agregarActividad( edTxtNombreE.getText().toString(), noControl,edTxtMateriaE.getText().toString(),
                            edTxtDescripcionE.getText().toString(), actividad, edTxtDiaE.getText().toString(), edTxtMesE.getText().toString()
                            , edTxtAnoE.getText().toString(), edTxtHoraE.getText().toString());
                    Toast.makeText(getApplicationContext(), "Se agregó correctamente", Toast.LENGTH_SHORT).show();

                    edTxtNombreE.setText("");
                    edTxtMateriaE.setText("");
                    edTxtDescripcionE.setText("");
                    edTxtDiaE.setText("");
                    edTxtMesE.setText("");
                    edTxtAnoE.setText("");
                    edTxtHoraE.setText("");

                    finish();

                }
            }
        });

        btnGuardarN.setOnClickListener(new View.OnClickListener() {
            String noControl = getIntent().getStringExtra("noControle");
            String actividad = "examen";
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                if(edTxtNombreE.getText().toString().equals("")  || edTxtMateriaE.getText().toString().equals("") ||
                        edTxtDescripcionE.getText().toString().equals("")||
                        edTxtDiaE.getText().toString().equals("")||
                        edTxtMesE.getText().toString().equals("")||
                        edTxtAnoE.getText().toString().equals("")||edTxtHoraE.getText().toString().equals("")||edTxtMinE.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Faltan campos por llenar", Toast.LENGTH_SHORT).show();
                }else
                    {
                    objDB.agregarActividad(edTxtNombreE.getText().toString(), noControl,edTxtMateriaE.getText().toString(),
                            edTxtDescripcionE.getText().toString(), actividad, edTxtDiaE.getText().toString(), edTxtMesE.getText().toString()
                            , edTxtAnoE.getText().toString(), edTxtHoraE.getText().toString()) ;
                    Toast.makeText(getApplicationContext(), "Se agregó correctamente", Toast.LENGTH_SHORT).show();

                    int dia = Integer.parseInt(edTxtDiaE.getText().toString());
                    int mes = (Integer.parseInt(edTxtMesE.getText().toString()))-1; //enero empieza en 0
                    int ano = Integer.parseInt(edTxtAnoE.getText().toString());
                    int hora = Integer.parseInt(edTxtHoraE.getText().toString());
                    int min = Integer.parseInt(edTxtMinE.getText().toString());


                    Calendar calendar = Calendar.getInstance();

                    calendar.set(ano,mes,dia,hora,min);
                    Log.wtf("Verificacion","Año: "+ano+" Mes: "+mes+" Dia: "+dia+" Hora: "+hora+" Minutos: "+min);
                    Long noti = (calendar.getTimeInMillis());

                    Intent intent = new Intent(AgregarExamen.this, NotificationReceiverE.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(AgregarExamen.this, 0, intent, 0);

                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, noti ,pendingIntent);

                    Toast.makeText(getApplicationContext(),"Se ha configurado el recordatorio", Toast.LENGTH_SHORT).show();

                    edTxtNombreE.setText("");
                    edTxtMateriaE.setText("");
                    edTxtDescripcionE.setText("");
                    edTxtDiaE.setText("");
                    edTxtMesE.setText("");
                    edTxtAnoE.setText("");
                    edTxtHoraE.setText("");

                    finish();

                }

            }
        });
    }
}
