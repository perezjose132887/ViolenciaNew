package com.example.violencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {


    String idUsuario,nombres,primerApellido,numeroCI,foto,correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


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
        },3000);

    }
}