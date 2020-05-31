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

public class AgregarProyecto extends AppCompatActivity {
    EditText edTxtNombreP, edTxtMateriaP, edTxtDescripcionP, edTxtDiaP, edTxtMesP, edTxtAnoP, edTxtHoraP, edTxtMinP;
    Button btnGuardarP;
    Button btnGuardarN;
    NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_proyecto);

        edTxtNombreP = findViewById(R.id.edTxtNombreP);
        edTxtMateriaP = findViewById(R.id.edTxtMateriaP);
        edTxtDescripcionP = findViewById(R.id.edTxtDescripcionP);
        edTxtDiaP = findViewById(R.id.edTxtDiaP);
        edTxtMesP = findViewById(R.id.edTxtMesP);
        edTxtAnoP = findViewById(R.id.edTxtAnoP);
        edTxtHoraP = findViewById(R.id.edTxtHoraT);
        edTxtMinP = findViewById(R.id.edTxtMinT);
        btnGuardarP = findViewById(R.id.btnGuardarP);
        btnGuardarN = findViewById(R.id.btnGuardarN);
        manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        final DBSQLite objDB = new DBSQLite(getApplicationContext());

        btnGuardarP.setOnClickListener(new View.OnClickListener() {
            String noControl = getIntent().getStringExtra("noControlp");
            String actividad = "proyecto";
            @Override
            public void onClick(View v) {
                if(edTxtNombreP.getText().toString().equals("")  || edTxtMateriaP.getText().toString().equals("") ||
                        edTxtDescripcionP.getText().toString().equals("")||
                        edTxtDiaP.getText().toString().equals("")||
                        edTxtMesP.getText().toString().equals("")||
                        edTxtAnoP.getText().toString().equals("")||edTxtHoraP.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Faltan campos por llenar", Toast.LENGTH_SHORT).show();
                }else{
                    objDB.agregarActividad( edTxtNombreP.getText().toString(), noControl,edTxtMateriaP.getText().toString(),
                            edTxtDescripcionP.getText().toString(), actividad, edTxtDiaP.getText().toString(), edTxtMesP.getText().toString()
                            , edTxtAnoP.getText().toString(), edTxtHoraP.getText().toString());
                    Toast.makeText(getApplicationContext(), "Se agregó correctamente", Toast.LENGTH_SHORT).show();

                    edTxtNombreP.setText("");
                    edTxtMateriaP.setText("");
                    edTxtDescripcionP.setText("");
                    edTxtDiaP.setText("");
                    edTxtMesP.setText("");
                    edTxtAnoP.setText("");
                    edTxtHoraP.setText("");

                    finish();
                }
            }
        });

        btnGuardarN.setOnClickListener(new View.OnClickListener() {
            String noControl = getIntent().getStringExtra("noControl");
            String actividad = "tarea";
            @Override
            public void onClick(View v) {

                if(edTxtNombreP.getText().toString().equals("")  || edTxtMateriaP.getText().toString().equals("") ||
                        edTxtDescripcionP.getText().toString().equals("")||
                        edTxtDiaP.getText().toString().equals("")||
                        edTxtMesP.getText().toString().equals("")||
                        edTxtAnoP.getText().toString().equals("")||edTxtHoraP.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Faltan campos por llenar", Toast.LENGTH_SHORT).show();
                }else {
                    objDB.agregarActividad(edTxtNombreP.getText().toString(), noControl, edTxtMateriaP.getText().toString(),
                            edTxtDescripcionP.getText().toString(), actividad, edTxtDiaP.getText().toString(), edTxtMesP.getText().toString()
                            , edTxtAnoP.getText().toString(), edTxtHoraP.getText().toString());
                    Toast.makeText(getApplicationContext(), "Se agregó correctamente", Toast.LENGTH_SHORT).show();

                    int dia3 = Integer.parseInt(edTxtDiaP.getText().toString());
                    int mes3 = (Integer.parseInt(edTxtMesP.getText().toString())) - 1; //enero empieza en 0
                    int ano3 = Integer.parseInt(edTxtAnoP.getText().toString());
                    int hora3 = Integer.parseInt(edTxtHoraP.getText().toString());
                    int min3 = Integer.parseInt(edTxtMinP.getText().toString());


                    Calendar calendar3 = Calendar.getInstance();

                    calendar3.set(ano3, mes3, dia3, hora3, min3);
                    Log.wtf("Verificacion", "Año: " + ano3 + " Mes: " + mes3 + " Dia: " + dia3 + " Hora: " + hora3 + " Minutos: " + min3);
                    Long noti = (calendar3.getTimeInMillis());

                    Intent intent = new Intent(AgregarProyecto.this, NotificationReceiverE.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(AgregarProyecto.this, 0, intent, 0);

                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, noti, pendingIntent);

                    Toast.makeText(getApplicationContext(), "Se ha configurado el recordatorio", Toast.LENGTH_SHORT).show();

                    edTxtNombreP.setText("");
                    edTxtMateriaP.setText("");
                    edTxtDescripcionP.setText("");
                    edTxtDiaP.setText("");
                    edTxtMesP.setText("");
                    edTxtAnoP.setText("");
                    edTxtHoraP.setText("");

                    finish();

                }
            }
        });
    }
}
