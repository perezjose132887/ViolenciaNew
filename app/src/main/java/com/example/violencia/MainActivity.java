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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView crear;
    Button iniciarSecion;
    EditText correo,contrasena;
    String scorreo,scontrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crear=(TextView) findViewById(R.id.tvCrear);

        iniciarSecion=(Button) findViewById(R.id.btnIniciarSecion);
        correo=(EditText) findViewById(R.id.etCorreo);
        contrasena=(EditText) findViewById(R.id.etContraseña);
        recuperarPrefencias();



        //Lleva a la ventana para registrarse un nuevo usuario
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ModuloUsuarioActivity.class);
                startActivity(intent);
            }
        });




        //validamos el email y password en el login()
        iniciarSecion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarUsuario("http://192.168.1.104/violencia/validarUsuario.php");
            }

        });





    }





    private void validarUsuario(String URL ){
        scorreo=correo.getText().toString().trim();
        scontrasena=contrasena.getText().toString().trim();
        if(!scorreo.isEmpty() && !scontrasena.isEmpty()){
            StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(!response.isEmpty()){
                        guardarPreferencias();
                        Intent intent=new Intent(MainActivity.this,ModuloContactoActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(MainActivity.this,"Usuario o contraseña incorrecta",Toast.LENGTH_SHORT).show();
                        correo.requestFocus();
                        correo.setText("");
                        contrasena.setText("");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> parametros=new HashMap<String,String>();
                    parametros.put("correo",scorreo);
                    parametros.put("contrasena",scontrasena);
                    return parametros;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(MainActivity.this,"No se permite campos vacios",Toast.LENGTH_SHORT).show();
        }
    }









    private void guardarPreferencias(){
        SharedPreferences preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();
        editor.putString("correo",scorreo);
        editor.putString("contrasena",scontrasena);
        editor.putBoolean("sesion",true);
        editor.commit();
    }





    private void recuperarPrefencias(){
        SharedPreferences preferences=getSharedPreferences("preferenciasLogin",Context.MODE_PRIVATE);
        correo.setText(preferences.getString("correo",""));
        contrasena.setText(preferences.getString("contrasena",""));
    }






}