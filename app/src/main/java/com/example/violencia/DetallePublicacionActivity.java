package com.example.violencia;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.violencia.Modelo.AdaptadorComentarioOficaiales;
import com.example.violencia.Modelo.AdaptadorPublicacionOficiales;
import com.example.violencia.Modelo.ListaComentarios;
import com.example.violencia.Modelo.ListaPublicaciones;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class DetallePublicacionActivity extends AppCompatActivity {

    private TextView idDetalle,nombreDetalle;
    private ImageView fotoDetalle,enviarComentario;
    private Button meGusta;
    private TextView contarMeGsuta;

    private TextView comentario;

    String sidPublicacion,sidUsuario,sComentario;

    private ListaPublicaciones listaPublicaciones;




    List<ListaComentarios> comentarioList;
    RecyclerView recyclerComent;




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






        recyclerComent=findViewById(R.id.ReciclerComentarios);
        recyclerComent.setHasFixedSize(true);
        recyclerComent.setLayoutManager(new LinearLayoutManager(this));
        comentarioList=new ArrayList<>();

        comentario=(TextView) findViewById(R.id.etComentario);
        enviarComentario=(ImageView) findViewById(R.id.imgEnviarComentario);

        //listarComentarios("https://codeperez.000webhostapp.com/ListaComentarios.php");
        listarComentarios("https://luchacontralaviolencia.000webhostapp.com/ListaComentarios.php");
        //listarComentarios("http://192.168.1.103/violencia/ListaComentarios.php");


        enviarComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //RegistrarComentario("https://codeperez.000webhostapp.com/RegistrarComentario.php");
                RegistrarComentario("https://luchacontralaviolencia.000webhostapp.com/RegistrarComentario.php");
                //RegistrarComentario("http://192.168.1.103/violencia/RegistrarComentario.php");
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









    public void listarComentarios(String URL){
        sidPublicacion=listaPublicaciones.getIdPublicacion();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {

                    JSONArray array= new JSONArray(response);
                    for (int i=0; i<array.length(); i++){
                        JSONObject Publicat=array.getJSONObject(i);
                        String idPublicacion=Publicat.getString("idPublicacion");

                        if(idPublicacion.equals(sidPublicacion)){
                            comentarioList.add(new ListaComentarios(
                                    Publicat.getString("idComentario"),
                                    Publicat.getString("idUsuario"),
                                    Publicat.getString("idPublicacion"),
                                    Publicat.getString("correo"),
                                    Publicat.getString("comentario"),
                                    Publicat.getString("fechaRegistro")

                            ));
                        }
                    }
                    AdaptadorComentarioOficaiales adaptadorComentarioOficaiales= new AdaptadorComentarioOficaiales(DetallePublicacionActivity.this,comentarioList);
                    recyclerComent.setAdapter(adaptadorComentarioOficaiales);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetallePublicacionActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(this).add(stringRequest);
    }






    public void RegistrarComentario(String URL) {
        sidPublicacion=listaPublicaciones.getIdPublicacion();
        SharedPreferences preferences = getSharedPreferences("sesion", Context.MODE_PRIVATE);
        sidUsuario = preferences.getString("idUsuario", "No encontrado");
        sComentario=comentario.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(DetallePublicacionActivity.this, "Comentario registrado ", Toast.LENGTH_SHORT).show();
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
                parametros.put("idUsuario", sidUsuario);
                parametros.put("idPublicacion", sidPublicacion);
                parametros.put("comentario", sComentario);

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }







}