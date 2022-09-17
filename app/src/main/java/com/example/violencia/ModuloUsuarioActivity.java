package com.example.violencia;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class ModuloUsuarioActivity extends AppCompatActivity {

    Button sacarFoto,registrarUsuario;
    ImageView foto;
    Bitmap bitmap;
    int PICK_IMAGE_REQUEST=1;






    EditText nombres,primerApellido,segundoApellido,ci,nombreUsuario,correo,contrasenaUno,contrasena2,telefono;
    Spinner departamento;
    RadioButton masculino,femenino;
    String snombres;
    String sprimerApellido;
    String ssegundoApellido;
    String sci;
    String snombreUsuario;
    String scorreo;
    String scontrasenaUno;
    String scontrasena2;
    String stelefono;
    int sidDepartamento;
    String ssexo;
    String scontrasena;
    String srol;
    String imagen;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulo_usuario);


        departamento=(Spinner) findViewById(R.id.spDepartamento);
        sacarFoto=(Button) findViewById(R.id.btnSacarFoto);
        foto=(ImageView) findViewById(R.id.imgFoto);
        registrarUsuario=(Button)findViewById(R.id.btnRegistrarUsuario);

        nombres=(EditText) findViewById(R.id.etNombres);
        primerApellido=(EditText) findViewById(R.id.etPrimerApellido);
        segundoApellido=(EditText) findViewById(R.id.etSegundoApellido);
        ci=(EditText) findViewById(R.id.etCi);
        nombreUsuario=(EditText) findViewById(R.id.etNombreUsuario);
        correo=(EditText) findViewById(R.id.etCorreo);
        contrasenaUno=(EditText) findViewById(R.id.etPrimerContrasena);
        contrasena2=(EditText) findViewById(R.id.etSegundoContrasena);
        telefono=(EditText) findViewById(R.id.etTelefono);
        masculino=(RadioButton) findViewById(R.id.rbtnHombre);
        femenino=(RadioButton) findViewById(R.id.rbtnMujer);


        String[] opcionesDepa={"Cochabamba","La Paz","Oruro","Potosi","Tarija","Chuquisaca","Santa Cruz","Beni","Pando"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.spinner_departamento_item_jose,opcionesDepa);
        departamento.setAdapter(adapter);




        //Abrimos la camara para sacar una foto
        sacarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCamara();
            }
        });


        registrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertarUsuario("http://192.168.1.100/violencia/ModuloUsuarioActivity.php");
            }
        });






    }



    public String getStringImagen(Bitmap bmp){
        ByteArrayOutputStream baos= new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] imageBytes=baos.toByteArray();
        String encodedImage= Base64.encodeToString(imageBytes,Base64.DEFAULT);
        return encodedImage;
    }




    public void insertarUsuario(String URL){
        snombres=nombres.getText().toString().trim();
        sprimerApellido=primerApellido.getText().toString().trim();
        ssegundoApellido=segundoApellido.getText().toString().trim();

        snombreUsuario=nombreUsuario.getText().toString().trim();
        sci=ci.getText().toString().trim();
        scorreo=correo.getText().toString().trim();
        scontrasenaUno=contrasenaUno.getText().toString().trim();
        scontrasena2=contrasena2.getText().toString().trim();
        stelefono=telefono.getText().toString().trim();
        sidDepartamento=idDepartamento();
        ssexo=verificarSexo();
        scontrasena=contrasenaUno.getText().toString().trim();
        srol="usuario";


        if(snombres.isEmpty() || sprimerApellido.isEmpty() || sci.isEmpty() || snombreUsuario.isEmpty() || scorreo.isEmpty() || scontrasenaUno.isEmpty() || scontrasena2.isEmpty() || stelefono.isEmpty()){

            Toast.makeText(getApplicationContext(), "LLene todo los campos", Toast.LENGTH_SHORT).show();
        }else {

            if(scontrasenaUno.equals(scontrasena2)){
                try {
                    imagen=getStringImagen(bitmap);
                    final ProgressDialog loading = ProgressDialog.show(ModuloUsuarioActivity.this,"Subiendo","Espere porfavr");
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            loading.dismiss();
                            Toast.makeText(getApplicationContext(), "Registro Exitoso", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ModuloUsuarioActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            loading.dismiss();
                            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {



                            Map<String, String> parametros = new Hashtable<String, String>();
                            parametros.put("idDepartamento",String.valueOf(sidDepartamento));
                            parametros.put("nombres", snombres);
                            parametros.put("primerApellido", sprimerApellido);
                            parametros.put("segundoApellido", ssegundoApellido);
                            parametros.put("telefono", stelefono);
                            parametros.put("ci", sci);
                            parametros.put("sexo",ssexo);
                            parametros.put("nombreUsuario", snombreUsuario);
                            parametros.put("contrasena",scontrasena);
                            parametros.put("foto",imagen);
                            parametros.put("correo", scorreo);
                            parametros.put("rol", srol);
                            return parametros;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(ModuloUsuarioActivity.this);
                    requestQueue.add(stringRequest);
                }catch (Exception e){
                    Toast.makeText(ModuloUsuarioActivity.this, "Selecciona una foto", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        }

    }







    //Verificamos los radioBotones
    public String verificarSexo(){
        String sex="";
        if(femenino.isChecked()){
            sex="F";
        }else{
            if(masculino.isChecked()){
                sex="M";
            }
        }
        return sex;
    }



    //Agregar para el idDepartamento
    public int idDepartamento(){
        int valor=0;
        if (departamento.getSelectedItem().toString()=="Cochabamba") {
            valor=1;
        }else{
            if (departamento.getSelectedItem().toString()=="La Paz") {
                valor=2;
            }else{
                if (departamento.getSelectedItem().toString()=="Oruro") {
                    valor=3;
                }else{
                    if (departamento.getSelectedItem().toString()=="Potosi") {
                        valor=4;
                    }else{
                        if (departamento.getSelectedItem().toString()=="Tarija") {
                            valor=5;
                        }else{
                            if (departamento.getSelectedItem().toString()=="Chuquisaca") {
                                valor=6;
                            }else{
                                if (departamento.getSelectedItem().toString()=="Santa Cruz") {
                                    valor=7;
                                }else{
                                    if (departamento.getSelectedItem().toString()=="Beni") {
                                        valor=8;
                                    }else{
                                        valor=9;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return valor;
    }







    //Abrimos la camara para sacar una foto
    public void abrirCamara(){
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
                bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                foto.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }













}