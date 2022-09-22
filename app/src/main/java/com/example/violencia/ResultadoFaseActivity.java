package com.example.violencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ResultadoFaseActivity extends AppCompatActivity {

    Button aceptarResultado;
    TextView mensajeResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_fase);
        mensajeResultado=(TextView) findViewById(R.id.txtMensajeResultado);
        aceptarResultado=(Button) findViewById(R.id.btnAceptarResultado);


        String dato=getIntent().getStringExtra("resultadoFase");
        mensajeResultado.setText(dato+" - " +validarResultado(dato));
        Toast.makeText(ResultadoFaseActivity.this, ""+dato, Toast.LENGTH_SHORT).show();

        aceptarResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ResultadoFaseActivity.this,DenunciaFragment.class);
                startActivity(intent);
            }
        });

    }



    private String validarResultado(String dato){
        int valor=Integer.parseInt(dato);
        if(valor<6){
            //Toast.makeText(ResultadoFaseActivity.this, "Es menor a 6", Toast.LENGTH_SHORT).show();
            return "Es menor a 6";
        }else{
            if(valor<11){
                //Toast.makeText(ResultadoFaseActivity.this, "Es menor a 11", Toast.LENGTH_SHORT).show();
                return "Es menor a 11";
            }else{
                //Toast.makeText(ResultadoFaseActivity.this, "Alerta", Toast.LENGTH_SHORT).show();
                return "ALERTA";
            }
        }
    }


}