package com.example.bisonapp70;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edTxtNoControl, edTxtPassword;
    Button btnIngresar, btnRegistrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edTxtNoControl = findViewById(R.id.edTxtNoControl);
        edTxtPassword = findViewById(R.id.edTxtPassword);

        btnIngresar = findViewById(R.id.btnIngresar);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        final DBSQLite objDB = new DBSQLite(getApplicationContext());

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(objDB.login(edTxtNoControl.getText().toString(), edTxtPassword.getText().toString()) != 0){
                    Intent intent = new Intent(getApplicationContext(), Inicio.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT).show();

                    Intent intent2 = new Intent(getApplicationContext(), AgregarTarea.class);
                    Intent intent3 = new Intent(getApplicationContext(), AgregarProyecto.class);
                    Intent intent4 = new Intent(getApplicationContext(), AgregarExamen.class);
                    Intent intent5 = new Intent(getApplicationContext(), Mostrar.class);

                    intent2.putExtra("noControl", edTxtNoControl.getText().toString());
                    intent3.putExtra("noControlp", edTxtNoControl.getText().toString());
                    intent4.putExtra("noControle", edTxtNoControl.getText().toString());
                    intent5.putExtra("noControlb", edTxtNoControl.getText().toString());


                    edTxtPassword.setText("");
                }else if (edTxtNoControl.getText().toString().equals("") && edTxtPassword.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Cree una cuenta", Toast.LENGTH_SHORT).show();
                }else{
                    String text = "Sus datos para ingresar son incorrectos o no existe la cuenta";
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Registro.class);
                startActivity(intent);
            }
        });
    }
}
