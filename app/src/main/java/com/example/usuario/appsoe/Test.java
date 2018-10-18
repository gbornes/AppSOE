package com.example.usuario.appsoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class Test extends AppCompatActivity {

    //Para el VIEWPAGER (id:Container)
    private QuestionSliderAdapter questionSliderAdapter;
    private CustomViewPager mViewPager;


    //Array para gestionar las preguntas (Por ahora 15 preguntas)
    public int array_respuestas [] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    //Contadores para las diferentes especialidades
    public int Cant_Sistemas, Cant_Industrial, Cant_Quimica, Cant_Civil, Cant_Electrica, Cant_Mecanica;
    public String resultado_especialidad;
    public int posicion_mayor = 0;


    //Botones SI y NO para responder
    public ImageButton imgBtnYes;
    public ImageButton imgBtnNo;
    public ImageButton imgBtnPrevious;

    //"Variables" para la ProgressBar
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private TextView txtProgress;
    private TextView respuestas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        imgBtnYes = (ImageButton) findViewById(R.id.imgBtnYes);
        imgBtnNo  = (ImageButton) findViewById(R.id.imgBtnNo);
        imgBtnPrevious  = (ImageButton) findViewById(R.id.imgBtnPrevious);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        txtProgress = (TextView) findViewById(R.id.txtProgress);
        respuestas = (TextView) findViewById(R.id.respuestas);


        //PARA EL VIEWPAGER
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        questionSliderAdapter = new QuestionSliderAdapter(this);
        // Set up the ViewPager with the sections adapter.
        mViewPager = (CustomViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(questionSliderAdapter);
        //Para bloquear el slide del viewPager
        mViewPager.setPagingEnabled(false);


        //view3 =(ViewGroup) findViewById(R.id.view3);

        //txtQuestion = (TextView) findViewById(R.id.txtQuestion);

        //Inicializo cómo se encuentra la barra de progreso al iniciar
        progressStatus = 1;
        txtProgress.setText (progressStatus+"/"+progressBar.getMax());

        //txtQuestion.setText(R.string.question1);

        //Desabilito el boton previous
        botonPrevious();


        imgBtnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (progressStatus == 15){
                    finish();

                    CalcularResultado();
                    OpenResultado(view);

                }else {
                    array_respuestas[progressStatus - 1] = 1;
                    AumentarProgressBar();
                    mViewPager.setCurrentItem(progressStatus - 1);
                    botonPrevious();
                }
            }
        });

        imgBtnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (progressStatus == 15){
                    finish();

                    CalcularResultado();
                    OpenResultado(view);

                }else {
                    array_respuestas[progressStatus - 1] = 0;
                    AumentarProgressBar();
                    mViewPager.setCurrentItem(progressStatus - 1);
                    botonPrevious();
                }
            }
        });

        imgBtnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisminuirProgressBar();
                mViewPager.setCurrentItem(progressStatus -1);
                botonPrevious();
            }
        });
    }



    public void botonPrevious () {
        if (progressStatus == 1){
            imgBtnPrevious.setEnabled(false);
        }
        else imgBtnPrevious.setEnabled(true);

    }

    public void AumentarProgressBar (){
        if (progressStatus < 15) {
                progressStatus += 1;
                progressBar.setProgress(progressStatus);
                txtProgress.setText (progressStatus+"/"+progressBar.getMax());
            }
    }

    public void DisminuirProgressBar (){
        if (progressStatus > 1) {
            progressStatus -= 1;
            progressBar.setProgress(progressStatus);
            txtProgress.setText (progressStatus+"/"+progressBar.getMax());
        }
    }

    public void AbrirActivityHelp (View view){
        //Abro el Activity Presentacion
        Intent ActHelp = new Intent(this, Help.class);
        startActivity(ActHelp);
    }


    public void CalcularResultado () {

        int i;

        //Este vector sera de long 6 por la cantidad de especialidades. Muestra la cant de respuestas positivas por especialidad
        //POSICION: 0: Sistemas; 1: Electrica; 2: Industrial; 3: Civil; 4: Quimica; 5: Mecanica
        int array_Cant_porEspecialidad [] = {0, 0, 0, 0, 0, 0};

        //Variables para recorrer el vector y ver cual es el mayor y su posicion
        int numero_mayor;

        //Recorro el vector de respuestas totales para asignar los valores a los contadores (Que se almacenaran en el vector array_Cant_porEspecialidad)
        for (i = 0; i <= array_respuestas.length; i++){
            if (i == 0 || i < 3){
                if (array_respuestas [i] == 1) {
                    Cant_Sistemas = Cant_Sistemas + 1;
                }
            } else {
                if (i == 3 || i < 5){
                    if (array_respuestas [i] == 1) {
                        Cant_Electrica = Cant_Electrica + 1;
                    }
                } else {
                    if (i == 5 || i < 7){
                        if (array_respuestas [i] == 1) {
                            Cant_Industrial = Cant_Industrial + 1;
                        }
                    } else {
                        if (i == 7 || i < 9){
                            if (array_respuestas [i] == 1) {
                                Cant_Civil = Cant_Civil + 1;
                            }
                        } else {
                            if (i == 9 || i < 11){
                                if (array_respuestas [i] == 1) {
                                    Cant_Quimica = Cant_Quimica + 1;
                                }
                            } else {
                                if (i == 11 || i < 13){
                                    if (array_respuestas [i] == 1) {
                                        Cant_Mecanica = Cant_Mecanica + 1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    //Asigno los valores calculados al array_Cant_porEspecialidad
        for (i= 0; i < array_Cant_porEspecialidad.length; i++){
            if (i == 0){
                array_Cant_porEspecialidad [i] = Cant_Sistemas;
            } else {
                if (i == 1){
                    array_Cant_porEspecialidad [i] = Cant_Electrica;
                } else {
                    if (i == 2){
                        array_Cant_porEspecialidad [i] = Cant_Industrial;
                    } else {
                        if (i == 3){
                            array_Cant_porEspecialidad [i] = Cant_Civil;
                        } else {
                            if (i == 4){
                                array_Cant_porEspecialidad [i] = Cant_Quimica;
                            } else {
                                if (i == 5){
                                    array_Cant_porEspecialidad [i] = Cant_Mecanica;
                                }
                            }
                        }
                    }
                }
            }
        }

        //Ahora recorro el vector array_Cant_porEspecialidad para ver qué numero es el mayor y asignarlo al resultado final(tipo ing)
        numero_mayor = 0;
        posicion_mayor = 0;
        for (i=0; i < array_Cant_porEspecialidad.length; i++){
            if (array_Cant_porEspecialidad[i] > numero_mayor){
                numero_mayor = array_Cant_porEspecialidad [i];
                posicion_mayor = i;
            }
        }
        // CASE para ver en qué posicion del vector esta el numero mayor, es decir, que ingenieria es mas afin.
        switch (posicion_mayor){
            case 0:
                resultado_especialidad = "Ingeniería en Sistemas";
                break;
            case 1:
                resultado_especialidad = "Ingeniería Electrica";
                break;
            case 2:
                resultado_especialidad = "Ingeniería Indrustrial";
                break;
            case 3:
                resultado_especialidad = "Ingeniería Civil";
                break;
            case 4:
                resultado_especialidad = "Ingeniería Quimica";
                break;
            case 5:
                resultado_especialidad = "Ingeniería Mecanica";
                break;
        }

        //Toast.makeText(this, "La ingenieria es "+resultado_especialidad, Toast.LENGTH_LONG).show();

    }


    public void open(View view){
        //Creo el dialog
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        //Le doy el mensaje que mostrara el Dialog
        alertDialogBuilder.setMessage("Seguro que desea salir del test?");
                //Seteo el Boton "Si"/"SALIR"
                alertDialogBuilder.setPositiveButton("SALIR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                //Seteo el boton "no"/"Cancelar"
                alertDialogBuilder.setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                            //Toast.makeText(Test.this,"Continuando test",Toast.LENGTH_SHORT).show();
                        }

                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    public void OpenResultado (View view){
        //Abro el Activity Pregunta1
        Intent ActResultado = new Intent(this, Resultado.class);
        ActResultado.putExtra("num_tipo_ingenieria", posicion_mayor);
        startActivity(ActResultado);
    }

    public void AbrirCompletarDatos (View view){
        //Abro el Activity Presentacion
        Intent ActCompletarDatos = new Intent(this, CompletarDatos.class);
        startActivity(ActCompletarDatos);
    }

    public void MostrarMensaje (View view){
        Toast.makeText(this, "Para ver el resultado, complete los siguientes datos", Toast.LENGTH_LONG).show();
    }

}

