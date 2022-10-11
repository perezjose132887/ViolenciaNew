package com.example.violencia;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.violencia.Modelo.Adaptador;
import com.example.violencia.Modelo.DenunciaUsuarios;
import com.example.violencia.Modelo.MisDenuncias;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MisDenunciasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MisDenunciasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vista;
    ListView list;
    Adaptador adaptador;
    public static ArrayList<DenunciaUsuarios> users = new ArrayList<>();
    DenunciaUsuarios denunciaUsuarios;


    public MisDenunciasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MisDenunciasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MisDenunciasFragment newInstance(String param1, String param2) {
        MisDenunciasFragment fragment = new MisDenunciasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista=inflater.inflate(R.layout.fragment_mis_denuncias, container, false);
        list = vista.findViewById(R.id.listMostrar);
        adaptador=new Adaptador(this,users);
        list.setAdapter(adaptador);
        //insertarDenuncia("https://codeperez.000webhostapp.com/misdenuncias.php");
        //insertarDenuncia("https://luchacontralaviolencia.000webhostapp.com/misdenuncias.php");
        listarDenuncia("http://192.168.1.103/violencia/misdenuncias.php");

        return vista;
    }






    public void listarDenuncia(String URL){
        StringRequest request = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        SharedPreferences preferences=getActivity().getSharedPreferences("sesion", Context.MODE_PRIVATE);
                        String nu=preferences.getString("idUsuario","No encontrado");
                        users.clear();
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("datos");
                            if(success.equals("1")){
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String idDenuncia = object.getString("idDenuncia");
                                    String nombres = object.getString("nombres");
                                    String descripcionCategoria = object.getString("descripcionCategoria");
                                    String declaracion = object.getString("declaracion");
                                    String estado = object.getString("estado");
                                    String idUsuario = object.getString("idUsuario");
                                    String fechaRegistro = object.getString("fechaRegistro");
                                    denunciaUsuarios = new DenunciaUsuarios(idDenuncia,nombres,descripcionCategoria,declaracion,estado,idUsuario,fechaRegistro);

                                    if(nu.equals(idUsuario)){
                                        users.add(denunciaUsuarios);
                                        adaptador.notifyDataSetChanged();
                                    }
                                }
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }




}