package com.example.violencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SplashActivity extends AppCompatActivity {


    String idUsuario,nombres,primerApellido,numeroCI,foto,correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        //Agregar Animaciones
        Animation animacion1= AnimationUtils.loadAnimation(this,R.anim.desplazamiento_arriba);
        Animation animacion2= AnimationUtils.loadAnimation(this,R.anim.desplazamiento_abajo);
        ImageView img=(ImageView) findViewById(R.id.imgJuntosX);
        TextView uno=(TextView) findViewById(R.id.txt1);
        TextView dos=(TextView) findViewById(R.id.txt2);

        uno.setAnimation(animacion2);
        dos.setAnimation(animacion2);
        img.setAnimation(animacion1);


        idUsuario=getIntent().getStringExtra("idUsuario");
        nombres=getIntent().getStringExtra("nombres");
        primerApellido=getIntent().getStringExtra("primerApellido");
        numeroCI=getIntent().getStringExtra("numeroCI");
        foto=getIntent().getStringExtra("foto");
        correo=getIntent().getStringExtra("correo");


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,ModuloContactoActivity.class);
                intent.putExtra("idUsuario",idUsuario);
                intent.putExtra("nombres",nombres);
                intent.putExtra("primerApellido",primerApellido);
                intent.putExtra("numeroCI",numeroCI);
                intent.putExtra("foto",foto);
                intent.putExtra("correo",correo);
                startActivity(intent);
                finish();
            }
        },4000);

    }
}