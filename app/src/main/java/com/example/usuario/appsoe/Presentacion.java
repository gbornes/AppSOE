package com.example.usuario.appsoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Presentacion extends AppCompatActivity {


    Button btnComenzarTest;
    ImageButton imgBtnHelp, imgBtnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentacion);

        btnComenzarTest = (Button) findViewById(R.id.btnComenzarTest);
        imgBtnHelp = (ImageButton) findViewById(R.id.imgBtnHelp);
        imgBtnInfo = (ImageButton) findViewById(R.id.imgBtnInfo);


        //FALTA EL DEL BOTON QUE ABRA EL ACTIVITY ACERCA DE
        imgBtnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbrirActivityInformacion(v);
            }
        });

        imgBtnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Close(v);
            }
        });

        btnComenzarTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IniciarTest(v);
            }
        });
    }


    public void Close (View view){
        finish();
    }

    public void AbrirActivityInformacion (View view){
        //Abro el Activity Informacion
        Intent ActInformacion = new Intent(this, Informacion.class);
        startActivity(ActInformacion);
    }

    public void IniciarTest (View view){
        //Abro el Activity Pregunta1
        Intent ActTest = new Intent(this, Test.class);
        startActivity(ActTest);
    }
}
