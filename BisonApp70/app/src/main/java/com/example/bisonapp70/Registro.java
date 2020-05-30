package com.example.bisonapp70;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Registro extends AppCompatActivity {

    EditText edTxtNoControl, edTxtNombre, edTxtApellido, edTxtPassword;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edTxtNoControl = findViewById(R.id.edTxtNoControl);
        edTxtNombre = findViewById(R.id.edTxtNombre);
        edTxtApellido = findViewById(R.id.edTxtApellido);
        edTxtPassword = findViewById(R.id.edTxtPassword);

        btnRegistrar = findViewById(R.id.btnRegistrar);

        final DBSQLite objDB = new DBSQLite(getApplicationContext());

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edTxtNoControl.getText().toString().equals("") || edTxtNombre.getText().toString().equals("") || edTxtApellido.getText().toString().equals("")
                        ||edTxtPassword.getText().toString().equals("")){

                    Toast.makeText(getApplicationContext(), "Faltan campos por llenar", Toast.LENGTH_SHORT).show();
                }else{
                    objDB.agregarUsuario(edTxtNoControl.getText().toString(), edTxtNombre.getText().toString(),
                            edTxtApellido.getText().toString(), edTxtPassword.getText().toString());
                    Toast.makeText(getApplicationContext(), "Se agregó correctamente", Toast.LENGTH_SHORT).show();
                    finish();
                }



                edTxtNoControl.setText("");
                edTxtNombre.setText("");
                edTxtApellido.setText("");
                edTxtPassword.setText("");
            }
        });

    }
}
