package com.example.bisonapp70;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class AgregarExamen extends AppCompatActivity {
    EditText edTxtNombreE, edTxtMateriaE, edTxtDescripcionE, edTxtDiaE, edTxtMesE, edTxtAnoE, edTxtHoraE;
    Button btnGuardarE;
    Button btnGuardarN;

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
        edTxtHoraE = findViewById(R.id.edTxtHoraE);
        btnGuardarE = findViewById(R.id.btnGuardarE);
        btnGuardarN = findViewById(R.id.btnGuardarN);

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


                    int dia = Integer.parseInt(edTxtDiaE.getText().toString());
                    int mes = (Integer.parseInt(edTxtMesE.getText().toString()))-1; //enero empieza en 0
                    int ano = Integer.parseInt(edTxtAnoE.getText().toString());

                    Toast.makeText(getApplicationContext(), "Día: "+dia+" Mes: "+mes+" Año: "+ano, Toast.LENGTH_LONG).show();

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, ano);
                    calendar.set(Calendar.MONTH, mes);
                    calendar.set(Calendar.DAY_OF_MONTH, dia);
                    calendar.set(Calendar.HOUR_OF_DAY, 04);
                    calendar.set(Calendar.MINUTE, 24);

                    if (calendar.getTime().compareTo(new Date()) < 0)
                        calendar.add(Calendar.DAY_OF_MONTH, 1);

                    Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                    if (alarmManager != null) {
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

                    }

                    NotificationHelper notificationHelper = new NotificationHelper(getBaseContext());
                    notificationHelper.createNotification();

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
