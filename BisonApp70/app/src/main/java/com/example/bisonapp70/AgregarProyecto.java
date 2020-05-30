package com.example.bisonapp70;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarProyecto extends AppCompatActivity {
    EditText edTxtNombreP, edTxtMateriaP, edTxtDescripcionP, edTxtDiaP, edTxtMesP, edTxtAnoP, edTxtHoraP;
    Button btnGuardarP;

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
        edTxtHoraP = findViewById(R.id.edTxtHoraP);
        btnGuardarP = findViewById(R.id.btnGuardarP);

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
    }
}
