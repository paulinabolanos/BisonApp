package com.example.bisonapp70;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarTarea extends AppCompatActivity {
    EditText edTxtNombreT, edTxtMateriaT, edTxtDescripcionT, edTxtDiaT, edTxtMesT, edTxtAnoT, edTxtHoraT;
    Button btnGuardarT;

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
        btnGuardarT = findViewById(R.id.btnGuardarT);

        final DBSQLite objDB = new DBSQLite(getApplicationContext());

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
    }
}
