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

        /*Creamos una instancia de la clase "DBSQLite" para hacer uso de los metodos de esa clase,
        los cuales nos permiten dar mantenimiento a la base de datos SQLite*/
        final DBSQLite objDB = new DBSQLite(getApplicationContext());

        //Cada boton tiene un evento click que nos manda hacia otra actividad
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Inicio.class);
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

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Mostrar.class);
                startActivity(intent);
                finish();
            }
        });
        //Evento click en nuestro boton para buscar una actividad
        btnBuscarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos una instancia de la clase "TareasModelo"
                TareasModelo actividad = new TareasModelo();
                //Invocamos el metodo "buscarActividad()" y e damos como parametros la insrancia y el nombre a buscar
                objDB.buscarActividad(actividad, edTxtNombreB.getText().toString());
                //A cada uno de los EditText le agregamos cada uno de los campos solicitados por la busqueda
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
                //Llamamos al metodo "editarTarea! creado en la clase "DBSQLite" y le damos como parametros el nombre... a modificar
                objDB.editarTarea(edTxtNombreB.getText().toString(), edTxtMateriaB.getText().toString(), edTxtDescripcionB.getText().toString(),
                        edTxtDiaB.getText().toString(), edTxtMesB.getText().toString(), edTxtAnoB.getText().toString(), edTxtHoraB.getText().toString());
                //Notificacion tipo Toas para confirmar que los datos fueron mofdificacos correctamente
                Toast.makeText(getApplicationContext(), "Los datos se modificaron", Toast.LENGTH_SHORT).show();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Llamamos al metodo "eliminarTarea" y le damos como parametro el nombre de la actividad que se borará
                objDB.eliminarTarea(edTxtNombreB.getText().toString());
                //Agregamos una notificacion tipo Toast para confirmar que los datos fueron eliminados correctamente
                Toast.makeText(getApplicationContext(), "El dato se borró", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
