package com.example.usuario.appsoe;

import android.content.Intent;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CompletarDatos extends AppCompatActivity {

    EditText fieldNombre;
    EditText fieldApellido;
    EditText fieldProvincia;
    EditText fieldLocalidad;
    EditText fieldEscuela;
    EditText fieldAño;
    EditText fieldEmail;
    Button btnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completar_datos);

        btnContinuar = (Button) findViewById(R.id.btnContinuar);

        fieldNombre = (EditText) findViewById(R.id.fieldNombre);
        fieldApellido = (EditText) findViewById(R.id.fieldApellido);
        fieldProvincia = (EditText) findViewById(R.id.fieldProvincia);
        fieldLocalidad = (EditText) findViewById(R.id.fieldLocalidad);
        fieldEscuela = (EditText) findViewById(R.id.fieldEscuela);
        fieldAño = (EditText) findViewById(R.id.fieldAño);
        fieldEmail = (EditText) findViewById(R.id.fieldEmail);



        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creo las variables para verificar que esten completos los campos
                String nombre = fieldNombre.getText().toString();
                String apellido = fieldApellido.getText().toString();
                String provincia = fieldProvincia.getText().toString();
                String localidad = fieldLocalidad.getText().toString();
                String escuela = fieldEscuela.getText().toString();
                String año = fieldAño.getText().toString();
                String email = fieldEmail.getText().toString();

                //Verifico que los campos estén todos completos
                verificarCampos(nombre, apellido, provincia, localidad, escuela, año, email);

                //Llamo al proc CargarDatos para insertar los datos del Usuario en la BBDD
                //10.0.2.2 porque es el ip del emulador de android studio
                //new CompletarDatos.CargarDatos().execute("http://10.0.2.2/ProyectoSOE/registro2.php?nombre="+fieldNombre.getText().toString()+"&apellido="+fieldApellido.getText().toString()+"&email="+fieldEmail.getText().toString()+"&provincia="+fieldProvincia.getText().toString()+"&localidad="+fieldLocalidad.getText().toString()+"&escuela="+fieldEscuela.getText().toString()+"&año="+fieldAño.getText().toString());
                //finish();
            }
        });
    }



    public void verificarCampos (String nom, String ape, String pro, String loc, String esc, String añ, String ema) {
        if (nom.equals("") || ape.equals("") || pro.equals("") || loc.equals("") || esc.equals("") || añ.equals("") || ema.equals("")) {
            Toast.makeText(getApplicationContext(), "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            //Llamo al proc CargarDatos para insertar los datos del Usuario en la BBDD
            //10.0.2.2 porque es el ip del emulador de android studio
            new CompletarDatos.CargarDatos().execute("http://10.0.2.2/ProyectoSOE/registro2.php?nombre="+fieldNombre.getText().toString()+"&apellido="+fieldApellido.getText().toString()+"&email="+fieldEmail.getText().toString()+"&provincia="+fieldProvincia.getText().toString()+"&localidad="+fieldLocalidad.getText().toString()+"&escuela="+fieldEscuela.getText().toString()+"&año="+fieldAño.getText().toString());
            finish();
        }
    }



    public void Exit (View view){
        finish();
    }

    public void AbrirResultado (View view){
        //Abro el Activity Pregunta1
        Intent ActResultado = new Intent(this, Resultado.class);
        startActivity(ActResultado);
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

            //Toast.makeText(getApplicationContext(), "Los datos se almacenaron correctamente", Toast.LENGTH_SHORT).show();

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
