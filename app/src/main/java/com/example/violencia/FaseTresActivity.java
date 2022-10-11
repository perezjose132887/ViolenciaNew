package com.example.violencia;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Hashtable;
import java.util.Map;

public class FaseTresActivity extends AppCompatActivity {


    RadioButton f3p1r1,f3p1r2,f3p1r3,
            f3p2r1,f3p2r2,f3p2r3,
            f3p3r1,f3p3r2,f3p3r3,
            f3p4r1,f3p4r2,f3p4r3,
            f3p5r1,f3p5r2,f3p5r3;
    EditText result1,result2,result3,result4,result5,resultadoFaseTres;
    Button registrarFaseTres;
    String Cero="0",Uno="1",Dos="2",sresult1,sresult2,sresult3,sresult4,sresult5,idUsuario,sresultadoFaseTres;

    String idNombreFaseTres="3";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fase_tres);


        f3p1r1=(RadioButton) findViewById(R.id.rbtnf3p1r1);
        f3p1r2=(RadioButton) findViewById(R.id.rbtnf3p1r2);
        f3p1r3=(RadioButton) findViewById(R.id.rbtnf3p1r3);
        f3p2r1=(RadioButton) findViewById(R.id.rbtnf3p2r1);
        f3p2r2=(RadioButton) findViewById(R.id.rbtnf3p2r2);
        f3p2r3=(RadioButton) findViewById(R.id.rbtnf3p2r3);
        f3p3r1=(RadioButton) findViewById(R.id.rbtnf3p3r1);
        f3p3r2=(RadioButton) findViewById(R.id.rbtnf3p3r2);
        f3p3r3=(RadioButton) findViewById(R.id.rbtnf3p3r3);
        f3p4r1=(RadioButton) findViewById(R.id.rbtnf3p4r1);
        f3p4r2=(RadioButton) findViewById(R.id.rbtnf3p4r2);
        f3p4r3=(RadioButton) findViewById(R.id.rbtnf3p4r3);
        f3p5r1=(RadioButton) findViewById(R.id.rbtnf3p5r1);
        f3p5r2=(RadioButton) findViewById(R.id.rbtnf3p5r2);
        f3p5r3=(RadioButton) findViewById(R.id.rbtnf3p5r3);



        result1=(EditText)findViewById(R.id.etResult1);
        result2=(EditText)findViewById(R.id.etResult2);
        result3=(EditText)findViewById(R.id.etResult3);
        result4=(EditText)findViewById(R.id.etResult4);
        result5=(EditText)findViewById(R.id.etResult5);
        resultadoFaseTres=(EditText) findViewById(R.id.etResultadoFaseTres);


        registrarFaseTres=(Button) findViewById(R.id.btnVerificarFaseTres);


        registrarFaseTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalificarFaseTres();
                RegistrarTres("http://192.168.1.102/violencia/registrarFaseUno.php");
                //RegistrarTres("https://luchacontralaviolencia.000webhostapp.com/registrarFaseUno.php");
                Intent intent=new Intent(FaseTresActivity.this,ResultadoFaseActivity.class);
                intent.putExtra("resultadoFase",resultadoFaseTres.getText().toString());
                startActivity(intent);
                finish();
            }
        });



    }




    private void RegistrarTres(String URL){
        sresult1=result1.getText().toString().trim();
        sresult2=result2.getText().toString().trim();
        sresult3=result3.getText().toString().trim();
        sresult4=result4.getText().toString().trim();
        sresult5=result5.getText().toString().trim();
        sresultadoFaseTres=resultadoFaseTres.getText().toString().trim();

        SharedPreferences preferences=getSharedPreferences("sesion", Context.MODE_PRIVATE);
        idUsuario=preferences.getString("idUsuario","No encontrado");

        if(f3p1r1.isChecked() || f3p1r2.isChecked() || f3p1r3.isChecked() &&
                f3p2r1.isChecked() || f3p2r2.isChecked() || f3p2r3.isChecked() &&
                f3p3r1.isChecked() || f3p3r2.isChecked() || f3p3r3.isChecked() &&
                f3p4r1.isChecked() || f3p4r2.isChecked() || f3p4r3.isChecked() &&
                f3p5r1.isChecked() || f3p5r2.isChecked() || f3p5r3.isChecked()){


            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(FaseTresActivity.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(FaseTresActivity.this,error.toString(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> parametros = new Hashtable<String, String>();
                    parametros.put("idUsuario",idUsuario);
                    parametros.put("idNombre",idNombreFaseTres);
                    parametros.put("respuesta1", sresult1);
                    parametros.put("respuesta2", sresult2);
                    parametros.put("respuesta3", sresult3);
                    parametros.put("respuesta4", sresult4);
                    parametros.put("respuesta5", sresult5);
                    parametros.put("resultado", sresultadoFaseTres);
                    return parametros;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(FaseTresActivity.this);
            requestQueue.add(stringRequest);
        }else {
            Toast.makeText(FaseTresActivity.this, "Seleccione una respuesta por cada pregunta", Toast.LENGTH_SHORT).show();
        }
    }






    private void CalificarFaseTres(){

        int resultado=0;
        if(f3p1r1.isChecked()){
            resultado+=0;
            result1.setText(Cero);
        }
        if(f3p1r2.isChecked()){
            resultado+=1;
            result1.setText(Uno);
        }
        if(f3p1r3.isChecked()){
            resultado+=2;
            result1.setText(Dos);
        }
        if(f3p2r1.isChecked()){
            resultado+=0;
            result2.setText(Cero);
        }
        if(f3p2r2.isChecked()){
            resultado+=1;
            result2.setText(Uno);
        }
        if(f3p2r3.isChecked()){
            resultado+=2;
            result2.setText(Dos);
        }
        if(f3p3r1.isChecked()){
            resultado+=0;
            result3.setText(Cero);
        }
        if(f3p3r2.isChecked()){
            resultado+=1;
            result3.setText(Uno);
        }
        if(f3p3r3.isChecked()){
            resultado+=2;
            result3.setText(Dos);
        }
        if(f3p4r1.isChecked()){
            resultado+=0;
            result4.setText(Cero);
        }
        if(f3p4r2.isChecked()){
            resultado+=1;
            result4.setText(Uno);
        }
        if(f3p4r3.isChecked()){
            resultado+=2;
            result4.setText(Dos);
        }
        if(f3p5r1.isChecked()){
            resultado+=0;
            result5.setText(Cero);
        }
        if(f3p5r2.isChecked()){
            resultado+=1;
            result5.setText(Uno);
        }
        if(f3p5r3.isChecked()){
            resultado+=2;
            result5.setText(Dos);
        }

        resultadoFaseTres.setText(""+resultado);

    }



}