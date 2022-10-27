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
import com.example.violencia.Modelo.AdaptadorInstituciones;
import com.example.violencia.Modelo.ListaInstituciones;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InstitucionesChuquisacaActivity extends AppCompatActivity {

    List<ListaInstituciones> institucionList;
    RecyclerView recyclerInst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instituciones_chuquisaca);



        recyclerInst=findViewById(R.id.ReciclerInstituciones);
        recyclerInst.setHasFixedSize(true);
        recyclerInst.setLayoutManager(new LinearLayoutManager(this));
        institucionList=new ArrayList<>();
        //listarInstituciones("https://codeperez.000webhostapp.com/ListaInstituciones.php");
        listarInstituciones("https://luchacontralaviolencia.000webhostapp.com/ListaInstituciones.php");
        //listarInstituciones("http://192.168.1.103/violencia/ListaInstituciones.php");

    }


    public void listarInstituciones(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {

                    JSONArray array= new JSONArray(response);
                    for (int i=0; i<array.length(); i++){
                        JSONObject Insti=array.getJSONObject(i);
                        String ciudad=Insti.getString("idDepartamento");

                        if(ciudad.equals("2")) {
                            institucionList.add(new ListaInstituciones(
                                    Insti.getString("idDepartamento"),
                                    Insti.getString("nombreInstitucion"),
                                    Insti.getString("direccion"),
                                    Insti.getString("telefono")

                            ));
                        }
                    }
                    AdaptadorInstituciones adaptadorInstituciones= new AdaptadorInstituciones(InstitucionesChuquisacaActivity.this,institucionList);
                    recyclerInst.setAdapter(adaptadorInstituciones);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InstitucionesChuquisacaActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(this).add(stringRequest);
    }


}