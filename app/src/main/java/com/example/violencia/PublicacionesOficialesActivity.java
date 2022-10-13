package com.example.violencia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.violencia.Modelo.AdaptadorPublicacionOficiales;
import com.example.violencia.Modelo.ListaPublicaciones;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PublicacionesOficialesActivity extends AppCompatActivity {


    List<ListaPublicaciones> publicacionList;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicaciones_oficiales);


        recyclerView=findViewById(R.id.ReciclerDenuncias);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        publicacionList=new ArrayList<>();
        //listarOficiales("https://codeperez.000webhostapp.com/ListaPublicaciones.php");
        listarOficiales("https://luchacontralaviolencia.000webhostapp.com/ListaPublicaciones.php");
        //listarOficiales("http://192.168.1.103/violencia/ListaPublicaciones.php");

    }




    public void listarOficiales(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {

                    JSONArray array= new JSONArray(response);
                    for (int i=0; i<array.length(); i++){
                        JSONObject DenunciaUsuarios=array.getJSONObject(i);
                        String tipo=DenunciaUsuarios.getString("tipo");

                        if(tipo.equals("1")){
                            publicacionList.add(new ListaPublicaciones(
                                    DenunciaUsuarios.getString("idPublicacion"),
                                    DenunciaUsuarios.getString("titulo"),
                                    DenunciaUsuarios.getString("contenido"),
                                    DenunciaUsuarios.getString("tipo"),
                                    DenunciaUsuarios.getString("fotoPublicacion")

                            ));
                        }
                    }
                    AdaptadorPublicacionOficiales adaptadorPublicacionOficiales= new AdaptadorPublicacionOficiales(PublicacionesOficialesActivity.this,publicacionList);
                    recyclerView.setAdapter(adaptadorPublicacionOficiales);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PublicacionesOficialesActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(this).add(stringRequest);
    }





}