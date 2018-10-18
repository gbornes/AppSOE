package com.example.usuario.appsoe;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.Random;

import static com.example.usuario.appsoe.ConfigYoutube.DEVELOPER_KEY;

public class Resultado extends YouTubeBaseActivity
implements YouTubePlayer.OnInitializedListener {
    public static final String DEVELOPER_KEY = ConfigYoutube.DEVELOPER_KEY;
    private static String VIDEO_ID_SISTEMAS = ConfigYoutube.YOUTUBE_VIDEO_CODE_SISTEMAS;
    private static String VIDEO_ID_INDUSTRIAL = ConfigYoutube.YOUTUBE_VIDEO_CODE_INDUSTRIAL;
    private static String VIDEO_ID_QUIMICA = ConfigYoutube.YOUTUBE_VIDEO_CODE_QUIMICA;
    private static String VIDEO_ID_CIVIL = ConfigYoutube.YOUTUBE_VIDEO_CODE_CIVIL;
    private static String VIDEO_ID_ELECTRICA = ConfigYoutube.YOUTUBE_VIDEO_CODE_ELECTRICA;
    private static String VIDEO_ID_MECANICA = ConfigYoutube.YOUTUBE_VIDEO_CODE_MECANICA;
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    YouTubePlayerFragment myYouTubePlayerFragment;

    public int num_tipo_ingenieria;

    ImageButton imgBtnClose;
    TextView tvTipoIngenieria;
    TextView tvDejanos;
    Button btnAceptar;
    //VideoView videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        num_tipo_ingenieria = getIntent().getExtras().getInt("num_tipo_ingenieria");

        imgBtnClose = (ImageButton) findViewById(R.id.imgBtnClose);
        tvTipoIngenieria = (TextView) findViewById(R.id.tvTipoIngenieria);
        tvDejanos = (TextView) findViewById(R.id.tvDejanos);
        tvDejanos.setText(Html.fromHtml(getResources().getString(R.string.activity_resultado_dejanos_tus_datos)));

        //videoView = (VideoView) findViewById(R.id.videoView);

        //Calculo un Random entre los tipo de ingenieria

        //Le doy el valor obtenido al TextView
        SetearTVTipoIngenieria();


        myYouTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager()
                .findFragmentById(R.id.youtubeplayerfragment);

        myYouTubePlayerFragment.initialize(DEVELOPER_KEY, Resultado.this);

        //Muestro el video
        //VideoPlay(videoView);

        imgBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Close(view);
            }
        });

    }




    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(
                    "There was an error initializing the YouTubePlayer (%1$s)",
                    errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {
        if (!wasRestored) {
            //Segun el tipo de Ingenieria es el video que muestro
            switch (num_tipo_ingenieria){
                case 0:
                    player.cueVideo(VIDEO_ID_SISTEMAS);
                    break;
                case 1:
                    player.cueVideo(VIDEO_ID_ELECTRICA);
                    break;
                case 2:
                    player.cueVideo(VIDEO_ID_INDUSTRIAL);
                    break;
                case 3:
                    player.cueVideo(VIDEO_ID_CIVIL);
                    break;
                case 4:
                    player.cueVideo(VIDEO_ID_QUIMICA);
                    break;
                case 5:
                    player.cueVideo(VIDEO_ID_MECANICA);
                    break;
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
// Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(DEVELOPER_KEY, this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtubeplayerfragment);
    }



    public void SetearTVTipoIngenieria (){
        String tipo_ing = "";
        switch (num_tipo_ingenieria){
            case 0:
                tipo_ing = "Ingeniería en Sistemas";
                break;
            case 1:
                tipo_ing= "Ingeniería Electrica";
                break;
            case 2:
                tipo_ing = "Ingeniería Indrustrial";
                break;
            case 3:
                tipo_ing = "Ingeniería Civil";
                break;
            case 4:
                tipo_ing = "Ingeniería Quimica";
                break;
            case 5:
                tipo_ing = "Ingeniería Mecanica";
                break;
        }
        tvTipoIngenieria.setText(tipo_ing);
    }

    public void AbrirCompletarDatos (View view){
        //Abro el Activity Presentacion
        Intent ActCompletarDatos = new Intent(this, CompletarDatos.class);
        startActivity(ActCompletarDatos);
    }


    public void VideoPlay (View view){

        //String videopath = "android.resourse://com.example.usuario.appsoe"+R.raw.utnvideobienvenida;

        //Uri uri = Uri.parse ("https://www.youtube.com/watch?v=zcG2kTwSolg");
        //videoView.setMediaController(new MediaController(this));
        //videoView.setVideoURI(uri);
        //videoView.requestFocus();
        //videoView.start();
    }

    public void Close (View view){
        finish();
    }

}
