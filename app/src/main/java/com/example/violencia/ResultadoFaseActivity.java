package com.example.violencia;

import static com.example.violencia.R.raw.advertencia;
import static com.example.violencia.R.raw.advertenciados;
import static com.example.violencia.R.raw.alertados;
import static com.example.violencia.R.raw.alertatres;
import static com.example.violencia.R.raw.alertauno;
import static com.example.violencia.R.raw.bien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

public class ResultadoFaseActivity extends AppCompatActivity {

    Button aceptarResultado;
    TextView mensajeResultado;
    LottieAnimationView lottieani;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_fase);
        mensajeResultado=(TextView) findViewById(R.id.txtMensajeResultado);
        aceptarResultado=(Button) findViewById(R.id.btnAceptarResultado);
        lottieani=(LottieAnimationView) findViewById(R.id.lottieAnimation);


        String dato=getIntent().getStringExtra("resultadoFase");
        mensajeResultado.setText("" +validarResultado(dato));
        cambiarLottie(dato);
        //Toast.makeText(ResultadoFaseActivity.this, ""+dato, Toast.LENGTH_SHORT).show();

        aceptarResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ResultadoFaseActivity.this, NavigationDrawer.class);
                startActivity(intent);
            }
        });

    }



    private String validarResultado(String dato){
        int valor=Integer.parseInt(dato);
        if(valor<6){
            //Toast.makeText(ResultadoFaseActivity.this, "Es menor a 6", Toast.LENGTH_SHORT).show();
            return "Estimada, usted no sufre de violencia. Se le recomienda realizar los test una vez a la semana\n\nEl amor no reclama posesiones sino que da libertad.\n(Rabindranath Tagore)";
        }else{
            if(valor<11){
                //Toast.makeText(ResultadoFaseActivity.this, "Es menor a 11", Toast.LENGTH_SHORT).show();
                return "Estimada, usted está presentando algunos síntomas de violencia, le recomendamos dialogar con su pareja o comunicarse con la felcv para asesoramientos y mejorar su relación.\n\nRompe el silencio. Cuando seas testigo de la violencia contra las mujeres no te quedes de brazos cruzados. Actúa.\n(Ban Ki Moon)";
            }else{
                if(valor<15){
                    //Toast.makeText(ResultadoFaseActivity.this, "Es menor a 15", Toast.LENGTH_SHORT).show();
                    return "Estimada, usted está presentando varios síntomas de violencia. Se le recomienda comunicarse con la felcv para su asesoramiento\n\nMi silencio no me protegió. Tú silencio no te protegerá.\n(Audre Lorde)";
                }else{
                    //Toast.makeText(ResultadoFaseActivity.this, "Alerta", Toast.LENGTH_SHORT).show();
                    return "Estimada, usted está sufriendo violencia en muchos aspectos y corre peligro, le recomendamos contactarse con el SLIM.\n\nDefiende tu vida, lucha por tu independencia, busca tu felicidad y aprende a quererte\n(Izaskun González)";
                }

            }
        }
    }





    public void cambiarLottie(String dato){
        int valor=Integer.parseInt(dato);
        if(valor<6){
            lottieani.setAnimation(bien);
        }else{
            if(valor<11){
                lottieani.setAnimation(advertenciados);
            }else{
                if(valor<15){
                    lottieani.setAnimation(alertatres);
                }else{
                    lottieani.setAnimation(alertatres);
                }

            }
        }
    }




}