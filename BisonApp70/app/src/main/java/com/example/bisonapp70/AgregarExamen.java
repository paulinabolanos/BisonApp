package com.example.bisonapp70;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarExamen extends AppCompatActivity {
    EditText edTxtNombreE, edTxtMateriaE, edTxtDescripcionE, edTxtDiaE, edTxtMesE, edTxtAnoE, edTxtHoraE;
    Button btnGuardarE;

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
    }
}
