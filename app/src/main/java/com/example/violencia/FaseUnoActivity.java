package com.example.violencia;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
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

public class FaseUnoActivity extends AppCompatActivity {


    RadioButton f1p1r1,f1p1r2,f1p1r3,
                f1p2r1,f1p2r2,f1p2r3,
                f1p3r1,f1p3r2,f1p3r3,
                f1p4r1,f1p4r2,f1p4r3,
                f1p5r1,f1p5r2,f1p5r3;
    EditText result1,result2,result3,result4,result5,resultadoFaseUno;
    Button registrarFaseUno;
    String Uno="1",Dos="2",Tres="3",sresult1,sresult2,sresult3,sresult4,sresult5,idUsuario,sresultadoFaseUno;

    String idNombreFaseUno="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fase_uno);

        f1p1r1=(RadioButton) findViewById(R.id.rbtnf1p1r1);
        f1p1r2=(RadioButton) findViewById(R.id.rbtnf1p1r2);
        f1p1r3=(RadioButton) findViewById(R.id.rbtnf1p1r3);
        f1p2r1=(RadioButton) findViewById(R.id.rbtnf1p2r1);
        f1p2r2=(RadioButton) findViewById(R.id.rbtnf1p2r2);
        f1p2r3=(RadioButton) findViewById(R.id.rbtnf1p2r3);
        f1p3r1=(RadioButton) findViewById(R.id.rbtnf1p3r1);
        f1p3r2=(RadioButton) findViewById(R.id.rbtnf1p3r2);
        f1p3r3=(RadioButton) findViewById(R.id.rbtnf1p3r3);
        f1p4r1=(RadioButton) findViewById(R.id.rbtnf1p4r1);
        f1p4r2=(RadioButton) findViewById(R.id.rbtnf1p4r2);
        f1p4r3=(RadioButton) findViewById(R.id.rbtnf1p4r3);
        f1p5r1=(RadioButton) findViewById(R.id.rbtnf1p5r1);
        f1p5r2=(RadioButton) findViewById(R.id.rbtnf1p5r2);
        f1p5r3=(RadioButton) findViewById(R.id.rbtnf1p5r3);



        result1=(EditText)findViewById(R.id.etResult1);
        result2=(EditText)findViewById(R.id.etResult2);
        result3=(EditText)findViewById(R.id.etResult3);
        result4=(EditText)findViewById(R.id.etResult4);
        result5=(EditText)findViewById(R.id.etResult5);
        resultadoFaseUno=(EditText) findViewById(R.id.etResultadoFaseUno);


        registrarFaseUno=(Button) findViewById(R.id.btnVerificarFaseUno);



        registrarFaseUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalificarFaseUno();
                try {

                    Thread.sleep(5*1000);
                    RegistrarUno("http://192.168.1.100/violencia/registrarFaseUno.php");
                    //Toast.makeText(FaseUnoActivity.this, "Hola Mundo", Toast.LENGTH_SHORT).show();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });







    }



    public void RegistrarUno(String URL){
        sresult1=result1.getText().toString().trim();
        sresult2=result2.getText().toString().trim();
        sresult3=result3.getText().toString().trim();
        sresult4=result4.getText().toString().trim();
        sresult5=result5.getText().toString().trim();
        sresultadoFaseUno=resultadoFaseUno.getText().toString().trim();

        SharedPreferences preferences=getSharedPreferences("sesion", Context.MODE_PRIVATE);
        idUsuario=preferences.getString("idUsuario","No encontrado");

        if(f1p1r1.isChecked() || f1p1r2.isChecked() || f1p1r3.isChecked() &&
                f1p2r1.isChecked() || f1p2r2.isChecked() || f1p2r3.isChecked() &&
                f1p3r1.isChecked() || f1p3r2.isChecked() || f1p3r3.isChecked() &&
                f1p4r1.isChecked() || f1p4r2.isChecked() || f1p4r3.isChecked() &&
                f1p5r1.isChecked() || f1p5r2.isChecked() || f1p5r3.isChecked()){


            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(FaseUnoActivity.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(FaseUnoActivity.this,error.toString(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> parametros = new Hashtable<String, String>();
                    parametros.put("idUsuario",idUsuario);
                    parametros.put("idNombre",idNombreFaseUno);
                    parametros.put("respuesta1", sresult1);
                    parametros.put("respuesta2", sresult2);
                    parametros.put("respuesta3", sresult3);
                    parametros.put("respuesta4", sresult4);
                    parametros.put("respuesta5", sresult5);
                    parametros.put("resultado", sresultadoFaseUno);
                    return parametros;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(FaseUnoActivity.this);
            requestQueue.add(stringRequest);
        }else {
            Toast.makeText(FaseUnoActivity.this, "Seleccione una respuesta por cada pregunta", Toast.LENGTH_SHORT).show();
        }
    }






    public void CalificarFaseUno(){

        int resultado=0;
        if(f1p1r1.isChecked()){
            resultado+=1;
            result1.setText(Uno);
        }
        if(f1p1r2.isChecked()){
            resultado+=2;
            result1.setText(Dos);
        }
        if(f1p1r3.isChecked()){
            resultado+=3;
            result1.setText(Tres);
        }
        if(f1p2r1.isChecked()){
            resultado+=1;
            result2.setText(Uno);
        }
        if(f1p2r2.isChecked()){
            resultado+=2;
            result2.setText(Dos);
        }
        if(f1p2r3.isChecked()){
            resultado+=3;
            result2.setText(Tres);
        }
        if(f1p3r1.isChecked()){
            resultado+=1;
            result3.setText(Uno);
        }
        if(f1p3r2.isChecked()){
            resultado+=2;
            result3.setText(Dos);
        }
        if(f1p3r3.isChecked()){
            resultado+=3;
            result3.setText(Tres);
        }
        if(f1p4r1.isChecked()){
            resultado+=1;
            result4.setText(Uno);
        }
        if(f1p4r2.isChecked()){
            resultado+=2;
            result4.setText(Dos);
        }
        if(f1p4r3.isChecked()){
            resultado+=3;
            result4.setText(Tres);
        }
        if(f1p5r1.isChecked()){
            resultado+=1;
            result5.setText(Uno);
        }
        if(f1p5r2.isChecked()){
            resultado+=2;
            result5.setText(Dos);
        }
        if(f1p5r3.isChecked()){
            resultado+=3;
            result5.setText(Tres);
        }

        resultadoFaseUno.setText(""+resultado);

    }






}