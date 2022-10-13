package com.example.violencia;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.violencia.Modelo.AdaptadorDenuncia;
import com.example.violencia.Modelo.ListaDenuncias;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

    List<ListaDenuncias> denunciasList;
    RecyclerView recyclerView;


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

        recyclerView=vista.findViewById(R.id.ReciclerDenuncias);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        denunciasList=new ArrayList<>();

        //listarDenuncia("https://codeperez.000webhostapp.com/ListaMisDenuncias.php");
        listarDenuncia("https://luchacontralaviolencia.000webhostapp.com/ListaMisDenuncias.php");
        //listarDenuncia("http://192.168.1.103/violencia/ListaMisDenuncias.php");




        return vista;
    }



    public void listarDenuncia(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                SharedPreferences preferences=getActivity().getSharedPreferences("sesion", Context.MODE_PRIVATE);
                String nu=preferences.getString("idUsuario","No encontrado");
                try {

                    JSONArray array= new JSONArray(response);
                    for (int i=0; i<array.length(); i++){
                        JSONObject DenunciaUsuarios=array.getJSONObject(i);
                        String idUsuario=DenunciaUsuarios.getString("idUsuario");

                        if(nu.equals(idUsuario)){
                            denunciasList.add(new ListaDenuncias(
                                    DenunciaUsuarios.getString("idDenuncia"),
                                    DenunciaUsuarios.getString("nombres"),
                                    DenunciaUsuarios.getString("declaracion"),
                                    DenunciaUsuarios.getString("estado"),
                                    DenunciaUsuarios.getString("idUsuario"),
                                    DenunciaUsuarios.getString("fechaRegistro"),
                                    DenunciaUsuarios.getString("foto")

                            ));
                        }
                    }
                    AdaptadorDenuncia adaptadordenuncia= new AdaptadorDenuncia(getActivity(),denunciasList);
                    recyclerView.setAdapter(adaptadordenuncia);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(getContext()).add(stringRequest);
    }




}