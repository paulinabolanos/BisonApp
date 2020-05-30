package com.example.bisonapp70;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mostrar extends AppCompatActivity {
    private RecyclerView recVw;
    private TareasAdaptador tareasAdaptador;
    Button btnInicio, btnAgregar, btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        recVw = findViewById(R.id.recVw);
        recVw.setLayoutManager(new LinearLayoutManager(this));

        DBSQLite db = new DBSQLite(getApplicationContext());

        String noControl = getIntent().getStringExtra("noControlb");

        tareasAdaptador = new TareasAdaptador(db.mostrarTareas(noControl));



        recVw.setAdapter(tareasAdaptador);

        btnInicio = findViewById(R.id.btnInicio);

        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Inicio.class);
                startActivity(intent);
                finish();
            }
        });
        btnAgregar = findViewById(R.id.btnAgregar);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Agregar.class);
                startActivity(intent);
                finish();
            }
        });

        btnBuscar = findViewById(R.id.btnBuscar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Buscar.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
