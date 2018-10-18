package com.example.usuario.appsoe;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    ImageButton imgBtnHelp, imgBtnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        imgBtnHelp = (ImageButton) findViewById(R.id.imgBtnHelp);
        imgBtnInfo = (ImageButton) findViewById(R.id.imgBtnInfo);

        //Al hacer click en el Boton LOGIN
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Llamo al proc CargarDatos para insertar los datos del Usuario en la BBDD
                //10.0.2.2 porque es el ip del emulador de android studio
                //new CargarDatos().execute("http://10.0.2.2/ProyectoSOE/registro2.php?nombre="+"".toString()+"&apellido="+"".toString());
                AbrirPresentacion(v);
            }
        });



        imgBtnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbrirActivityInformacion(v);
            }
        });

        imgBtnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbrirActivityHelp(v);
            }
        });
    }



    //Este metodo lo invoco en el btniniciar.setOnClickListener para abrir el siguiente Activity
    //ATENCION!! DEBO CAMBIARLO --> Tiene que abrir la vista PRESENTACION y ese llevarme a la 1er preg del test.
    public void AbrirPresentacion (View view){
        //Abro el Activity Presentacion
        Intent ActPresentacion = new Intent(this, Presentacion.class);
        startActivity(ActPresentacion);
    }

    public void AbrirActivityInformacion (View view){
        //Abro el Activity Informacion
        Intent ActInformacion = new Intent(this, Informacion.class);
        startActivity(ActInformacion);
    }

    public void AbrirActivityHelp (View view){
        //Abro el Activity Help
        Intent ActHelp = new Intent(this, Help.class);
        startActivity(ActHelp);
    }

    public void AbrirCompletarDatos (View view){
        //Abro el Activity Presentacion
        Intent ActCompletarDatos = new Intent(this, CompletarDatos.class);
        startActivity(ActCompletarDatos);
    }


    /*Procedimientos para trabajar con la Base de Datos*/
    private class CargarDatos extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(getApplicationContext(), "Los datos se almacenaron correctamente", Toast.LENGTH_SHORT).show();

        }
    }


    /* Este método agarra la URL que le ingresemos
     * Crea un InputStream; le da la longitud maxima (500)
      * convierte la URL que le pasamos en un OBJETO de tipo URL
      * Crea la conexion y la abre
      * configura los parametros de timeout de lectura, de conexion, el metodo en el que vamos a enviar los datos (GET)
      * y arranca la conexion*/
    private String downloadUrl(String myurl) throws IOException {
        Log.i("URL",""+myurl);
        myurl = myurl.replace(" ","%20");
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            //Guardo un mensaje para ver qué me esta respondiendo (si esta ok o no)
            Log.d("respuesta", "The response is: " + response);
            //Lo que responda la url lo guarda en is (inputStream)
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
}
