package com.example.violencia;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.violencia.Modelo.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DenunciaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DenunciaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int RESULT_OK = -1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Spinner violencia;
    View vista;
    int sidCategoria;
    EditText declaracion;
    String sdeclaracion,imagenViolencia;
    Bitmap bitmap;
    ImageView fotoViolencia;
    int PICK_IMAGE_REQUEST=1;
    Button mostrarGaleria,registrarDenuncia;
    TextView titulo;
    EditText idBuscar,ciBuscar;


    String idUsuario;
    String nombres;




    public DenunciaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DenunciaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DenunciaFragment newInstance(String param1, String param2) {
        DenunciaFragment fragment = new DenunciaFragment();
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
            //idUsuario=getArguments().getString("idUsuario");
            //nombres=getArguments().getString("nombres");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_denuncia, container, false);
        violencia=(Spinner) vista.findViewById(R.id.spViolencia);
        declaracion=(EditText) vista.findViewById(R.id.etDeclaracion);
        fotoViolencia=(ImageView) vista.findViewById(R.id.imgFotoViolencia);
        mostrarGaleria=(Button) vista.findViewById(R.id.btnVerGaleria);
        registrarDenuncia=(Button) vista.findViewById(R.id.btnRegistrarDenuncia);
        titulo=(TextView) vista.findViewById(R.id.txtTitulo);


        //Toast.makeText(getContext(), ""+idUsuario+"-"+nombres, Toast.LENGTH_SHORT).show();


        String[] opcionesViolencia={"Violencia Fisica","Violencia Psicológica","Violencia Económica","Violencia Sexual","Violencia Emocional","Otro"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),R.layout.spinner_departamento_item_jose,opcionesViolencia);
        violencia.setAdapter(adapter);



        mostrarGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verGaleria();
            }
        });



        registrarDenuncia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //insertarDenuncia("https://codeperez.000webhostapp.com/ModuloDenunciaActivity.php");
                insertarDenuncia("https://luchacontralaviolencia.000webhostapp.com/ModuloDenunciaActivity.php");
                //insertarDenuncia("http://192.168.1.100/violencia/ModuloDenunciaActivity.php");

            }
        });


        /*titulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });*/





        return vista;
    }



    public String getStringImagen(Bitmap bmp){
        ByteArrayOutputStream baos= new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] imageBytes=baos.toByteArray();
        String encodedImage= Base64.encodeToString(imageBytes,Base64.DEFAULT);
        return encodedImage;
    }






    /*public void buscarIdUsuario(String URL){
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        idBuscar.setText(jsonObject.getString("idUsuario"));
                        Toast.makeText(getContext(), "Cédula correcto ahora presione REGISTRAR", Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Cédula NO Identificado", Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
    }*/





    public void insertarDenuncia(String URL){
        sidCategoria=idCategoria();
        sdeclaracion=declaracion.getText().toString().trim();
        imagenViolencia=getStringImagen(bitmap);
        SharedPreferences preferences=getActivity().getSharedPreferences("sesion", Context.MODE_PRIVATE);
        idUsuario=preferences.getString("idUsuario","No encontrado");



        if(sdeclaracion.isEmpty()){
            Toast.makeText(getContext(), "LLene todo los campos", Toast.LENGTH_SHORT).show();
        }else {
            final ProgressDialog loading = ProgressDialog.show(getActivity(),"Subiendo","Espere por favor");
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    loading.dismiss();
                    Toast.makeText(getContext(), "Registro Exitoso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(),NavigationDrawer.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    loading.dismiss();
                    Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {



                    Map<String, String> parametros = new Hashtable<String, String>();
                    parametros.put("idUsuario",idUsuario);
                    parametros.put("idCategoria", String.valueOf(sidCategoria));
                    parametros.put("declaracion", sdeclaracion);
                    parametros.put("foto",imagenViolencia);
                    return parametros;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(stringRequest);
        }

    }



    public void verGaleria(){
        //Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //startActivityForResult(intent,1);
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Selecciona una imagen"),PICK_IMAGE_REQUEST);
    }




    //La foto sacado lo colocamos en el ImageView
    public void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*if (requestCode == 1 && resultCode == RESULT_OK) {

                Bundle extras=data.getExtras();
                Bitmap imgBitmap=(Bitmap) extras.get("data");
                foto.setImageBitmap(imgBitmap);

        }*/
        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data != null && data.getData() != null){
            Uri filePath=data.getData();
            try{
                bitmap= MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(),filePath);
                fotoViolencia.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }



    //Agregar para el idDepartamento
    public int idCategoria() {
        int valor = 0;
        if (violencia.getSelectedItem().toString() == "Violencia Física") {
            valor = 1;
        } else {
            if (violencia.getSelectedItem().toString() == "Violencia Psicológica") {
                valor = 2;
            } else {
                if (violencia.getSelectedItem().toString() == "Violencia Económica") {
                    valor = 3;
                } else {
                    if (violencia.getSelectedItem().toString() == "Violencia Sexual") {
                        valor = 4;
                    } else {
                        if (violencia.getSelectedItem().toString() == "Violencia Emocional") {
                            valor = 5;
                        } else {
                            valor = 6;
                        }
                    }
                }
            }
        }
        return valor;
    }
}