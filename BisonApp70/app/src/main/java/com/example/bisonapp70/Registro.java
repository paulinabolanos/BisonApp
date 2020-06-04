package com.example.bisonapp70;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class Registro extends AppCompatActivity {

    private static final int RESULT_LOAD_IMG = 1;
    //Declaramos 4 objetos tipo EditText para cada uno de los campos del registro
    EditText edTxtNoControl, edTxtNombre, edTxtApellido, edTxtPassword;
    // Declaramos un objeto tipo Button para agregar los usuarios
    Button btnRegistrar;
    ImageView imageView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        /*Asociamos los objetos creados anteriormente con los elementos de nuestro formulario
        que nos permite registrar nuevos usuarios*/

        edTxtNoControl = findViewById(R.id.edTxtNoControl);
        edTxtNombre = findViewById(R.id.edTxtNombre);
        edTxtApellido = findViewById(R.id.edTxtApellido);
        edTxtPassword = findViewById(R.id.edTxtPassword);
        imageView5 = findViewById(R.id.imageView5);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
            }
        });

        /*Creamos una instancia de la clase "DBSQLite" para hacer uso de los metodos de esa clase,
        los cuales nos permiten dar mantenimiento a la base de datos SQLite*/
        final DBSQLite objDB = new DBSQLite(getApplicationContext());

        //Generamos el evento click del boton "Registrar"
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verificamos si todos los campos del formulario fueron llenados
                if(edTxtNoControl.getText().toString().equals("") || edTxtNombre.getText().toString().equals("") || edTxtApellido.getText().toString().equals("")
                        ||edTxtPassword.getText().toString().equals("")){
                    //Agregamos una notificación Toast para inficar que faltan campos por llenar
                    Toast.makeText(getApplicationContext(), "Faltan campos por llenar", Toast.LENGTH_SHORT).show();
                }else{
                    /*Mediante la instancia creada anteriormente llamamos al metodo "agregarUsuario()"
                    y dentro de este colocamos los objetos de tipo EditText que se encargan de
                    obtener los datos que se coloquen en el formulario de registro.*/
                    objDB.agregarUsuario(edTxtNoControl.getText().toString(), edTxtNombre.getText().toString(),
                            edTxtApellido.getText().toString(), edTxtPassword.getText().toString());
                    //Agregamos una notificación Toast para ver si los datos fueron agregados correctamente.
                    Toast.makeText(getApplicationContext(), "Se agregó correctamente", Toast.LENGTH_SHORT).show();
                    //Finalizamos la actividad
                    finish();
                }

                //Vaciamos los EditText del formulario
                edTxtNoControl.setText("");
                edTxtNombre.setText("");
                edTxtApellido.setText("");
                edTxtPassword.setText("");
            }
        });

    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imageView5.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Algo salió mal", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(this, "No has escogido una imagen",Toast.LENGTH_LONG).show();
        }
    }
}

