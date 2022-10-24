package com.example.violencia;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.violencia.Modelo.ListaPublicaciones;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

public class DetallePublicacionActivity extends AppCompatActivity {

    private TextView idDetalle,nombreDetalle;
    private ImageView fotoDetalle;
    private Button meGusta;
    private TextView contarMeGsuta;

    String sidPublicacion,sidUsuario;

    private ListaPublicaciones listaPublicaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_publicacion);
        setTitle(getClass().getSimpleName());

        initViews();
        initValues();
        listaPublicaciones= (ListaPublicaciones) getIntent().getExtras().getSerializable("itemDetalle");
        Toast.makeText(this, listaPublicaciones.getTitulo(), Toast.LENGTH_SHORT).show();


        sidPublicacion=listaPublicaciones.getIdPublicacion();
        //ContarMeGusta("http://192.168.1.103/violencia/ContarMeGusta.php?idPublicacion="+sidPublicacion);
        ContarMeGusta("https://luchacontralaviolencia.000webhostapp.com/ContarMeGusta.php?idPublicacion="+sidPublicacion);



        meGusta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //registrarMeGusta("http://192.168.1.103/violencia/RegistrarMeGusta.php");
                registrarMeGusta("https://luchacontralaviolencia.000webhostapp.com/RegistrarMeGusta.php");
                finish();
                startActivity(getIntent());

            }
        });



    }

    private void initViews() {
        idDetalle=findViewById(R.id.txtidDetalle);
        fotoDetalle=findViewById(R.id.imgFotoDenunciaDetalle);
        nombreDetalle=findViewById(R.id.txtnombreDetalle);
        meGusta=findViewById(R.id.btnMeGusta);
        contarMeGsuta=findViewById(R.id.txtConteoLike);
    }

    private void initValues() {
        listaPublicaciones= (ListaPublicaciones) getIntent().getExtras().getSerializable("itemDetalle");
        idDetalle.setText(listaPublicaciones.getTitulo());
        //fotoDetalle.setImageResource(listaPublicaciones.getFotoPublicacion());
        Glide.with(this)
                .load(listaPublicaciones.getFotoPublicacion())
                .into(fotoDetalle);
        nombreDetalle.setText(remplazar(""+listaPublicaciones.getContenido()));
    }

    public static String remplazar (String str) {
        return str.replaceAll("<br />", "\n");
    }




    public void registrarMeGusta(String URL) {
        sidPublicacion=listaPublicaciones.getIdPublicacion();
        SharedPreferences preferences = getSharedPreferences("sesion", Context.MODE_PRIVATE);
        sidUsuario = preferences.getString("idUsuario", "No encontrado");



            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(DetallePublicacionActivity.this, "Me Gusta", Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(DetallePublicacionActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> parametros = new Hashtable<String, String>();
                    parametros.put("idPublicacion", sidPublicacion);
                    parametros.put("idUsuario", sidUsuario);
                    return parametros;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


    }





    public void ContarMeGusta(String URL){
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for(int i=0; i<response.length(); i++){
                    try{
                        jsonObject=response.getJSONObject(i);
                        contarMeGsuta.setText(jsonObject.getString("totalLikes"));
                    }catch (JSONException e){
                        Toast.makeText(DetallePublicacionActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetallePublicacionActivity.this,"ERROR",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }







}