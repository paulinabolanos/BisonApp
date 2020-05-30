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
    EditText edTxtNoControl, edTxtNombre, edTxtApellido, edTxtPassword;
    Button btnRegistrar;
    ImageView imageView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

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

