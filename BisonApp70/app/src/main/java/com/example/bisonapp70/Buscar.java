package com.example.bisonapp70;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Buscar extends AppCompatActivity {
    Button btnInicio, btnAgregar, btnMostrar, btnBuscarActividad, btnEditar, btnEliminar;
    EditText edTxtNombreB, edTxtMateriaB, edTxtDescripcionB, edTxtDiaB, edTxtMesB, edTxtAnoB, edTxtHoraB;
    TextView txtVwActividadB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        btnInicio = findViewById(R.id.btnInicio);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnMostrar = findViewById(R.id.btnMostrar);
        btnBuscarActividad = findViewById(R.id.btnBuscarActividad);
        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);

        edTxtNombreB = findViewById(R.id.edTxtNombreB);
        edTxtMateriaB = findViewById(R.id.edTxtMateriaB);
        edTxtDescripcionB = findViewById(R.id.edTxtDescripcionB);
        edTxtDiaB = findViewById(R.id.edTxtDiaB);
        edTxtMesB = findViewById(R.id.edTxtMesB);
        edTxtAnoB = findViewById(R.id.edTxtAnoB);
        edTxtHoraB = findViewById(R.id.edTxtHoraB);

        txtVwActividadB = findViewById(R.id.txtVwActividadB);

        final DBSQLite objDB = new DBSQLite(getApplicationContext());

        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Inicio.class);
                startActivity(intent);
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Agregar.class);
                startActivity(intent);
            }
        });

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Mostrar.class);
                startActivity(intent);
            }
        });
        btnBuscarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TareasModelo actividad = new TareasModelo();
                objDB.buscarActividad(actividad, edTxtNombreB.getText().toString());
                txtVwActividadB.setText(actividad.getActividad());
                edTxtMateriaB.setText(actividad.getMateria());
                edTxtDescripcionB.setText(actividad.getDescripcion());
                edTxtDiaB.setText(actividad.getDia());
                edTxtMesB.setText(actividad.getMes());
                edTxtAnoB.setText(actividad.getAno());
                edTxtHoraB.setText(actividad.getHora());
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objDB.editarTarea(edTxtNombreB.getText().toString(), edTxtMateriaB.getText().toString(), edTxtDescripcionB.getText().toString(),
                        edTxtDiaB.getText().toString(), edTxtMesB.getText().toString(), edTxtAnoB.getText().toString(), edTxtHoraB.getText().toString());
                Toast.makeText(getApplicationContext(), "Los datos se modificaron", Toast.LENGTH_SHORT).show();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objDB.eliminarTarea(edTxtNombreB.getText().toString());
                Toast.makeText(getApplicationContext(), "El dato se borró", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
