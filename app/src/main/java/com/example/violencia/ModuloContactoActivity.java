package com.example.violencia;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.violencia.Modelo.ModelContactosActivity;

import java.util.ArrayList;

public class ModuloContactoActivity extends AppCompatActivity {

    Button eliminarLista,guardar;
    ImageButton seleccionar,atras;
    EditText nombreContacto,telefono;
    static final int PICK_CONTACT_REQUEST=1;
    ListView listaContactos;



    AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(ModuloContactoActivity.this,"administracion",null,1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulo_contacto);

        eliminarLista=(Button) findViewById(R.id.btnEliminarLista);
        seleccionar=(ImageButton) findViewById(R.id.ibtnSeleccionar);
        nombreContacto=(EditText) findViewById(R.id.etNombreContacto);
        telefono=(EditText) findViewById(R.id.etTelefono);
        guardar=(Button) findViewById(R.id.btnGuardarContacto);
        listaContactos=(ListView) findViewById(R.id.lvListaContactos);
        atras= (ImageButton) findViewById(R.id.imgAtras);

        String idUsuario=getIntent().getStringExtra("idUsuario");
        String nombres=getIntent().getStringExtra("nombres");
        String primerApellido=getIntent().getStringExtra("primerApellido");
        String numeroCI=getIntent().getStringExtra("numeroCI");
        String foto=getIntent().getStringExtra("foto");
        String correo=getIntent().getStringExtra("correo");







        cargarPreferenciasSesionContacto();


        //Toast.makeText(ModuloContactoActivity.this, ""+nombres+" "+idUsuario, Toast.LENGTH_SHORT).show();


        //Permiso para enviar mensajes de ayuda
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ModuloContactoActivity.this, new String[]{Manifest.permission.SEND_SMS}, 1);
        }





        /*eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SharedPreferences preferences= getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
                    preferences.edit().clear().commit();
                    Intent intent=new Intent(ModuloContactoActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    Toast.makeText(ModuloContactoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });*/


        //Selecciona un contacto del celular
        seleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SeleccionarContacto();
            }
        });


        //Click para segui avanzando de contactos a alerta
        /*continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ModuloContactoActivity.this,NavigationDrawer.class);
                intent.putExtra("idUsuario",idUsuario);
                intent.putExtra("nombres",nombres);
                intent.putExtra("primerApellido",primerApellido);
                intent.putExtra("numeroCI",numeroCI);
                intent.putExtra("foto",foto);
                intent.putExtra("correo",correo);
                startActivity(intent);
                finish();
            }
        });*/







        //Click para registrar los contactos en SQLite
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarContactoLista();
            }
        });



        eliminarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarListView();
            }
        });

        listarContactos();



        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ModuloContactoActivity.this,MenuOpcionesActivity.class);
                intent.putExtra("idUsuario",idUsuario);
                intent.putExtra("nombres",nombres);
                intent.putExtra("primerApellido",primerApellido);
                intent.putExtra("numeroCI",numeroCI);
                intent.putExtra("foto",foto);
                intent.putExtra("correo",correo);
                startActivity(intent);
                finish();
            }
        });


    }


    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Desea salir de la App?")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }






    public void cargarPreferenciasSesionContacto(){
        SharedPreferences preferencesSesion=getSharedPreferences("sesion",Context.MODE_PRIVATE);
        String idUsuario=preferencesSesion.getString("idUsuario","No encontrado");
        String nombres=preferencesSesion.getString("nombres","No encontrado");
        String primerApellido=preferencesSesion.getString("primerApellido","No encontrado");
        String numeroCI=preferencesSesion.getString("numeroCI","No encontrado");
        String correo=preferencesSesion.getString("correo","No encontrado");
        //Toast.makeText(this, "Preferences"+nombres+" "+primerApellido, Toast.LENGTH_SHORT).show();
    }


    public void eliminarListView(){
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        BaseDeDatos.delete("contactos",null,null);
        BaseDeDatos.execSQL("DROP TABLE contactos");
        BaseDeDatos.execSQL("create table if not exists contactos(idContacto integer primary key autoincrement, nombre text, numero int)");
        Toast.makeText(ModuloContactoActivity.this,"La lista a sido limpiado",Toast.LENGTH_SHORT).show();
        BaseDeDatos.close();
        finish();
        startActivity(getIntent());
    }







    //Metodo para seleccionar los contactos del telefono
    private void SeleccionarContacto() {
        Intent selectContactItem=new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        selectContactItem.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(selectContactItem,PICK_CONTACT_REQUEST);
    }





    //Metodo sobreescrito cuando acabemos de seleccionar el contacto del telefono
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_CONTACT_REQUEST) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);

                if(cursor.moveToFirst()){
                    int columnaNombre=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                    int columnaNumero=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    String nombre=cursor.getString(columnaNombre);
                    String numero=cursor.getString(columnaNumero);
                    nombreContacto.setText(nombre);
                    telefono.setText(numero);

                }
            }
        }
    }




    //Metodo para guardar contacto en listview
    public void guardarContactoLista(){
        AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(ModuloContactoActivity.this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos=admin.getWritableDatabase();//Abrir modo de lectura y escritura la bdd

        String snombreContacto=nombreContacto.getText().toString();//obtenemos el valor de los edittext
        String sTelefono=telefono.getText().toString();

        if(!snombreContacto.isEmpty() && !sTelefono.isEmpty()){//condicion para no dejar vacio los campos de contactos
            ContentValues registro= new ContentValues();
            registro.put("nombre",snombreContacto);//guardamos dentro de la bdd ahora falta insertar en la tabla
            registro.put("numero",sTelefono);

            BaseDeDatos.insert("contactos",null,registro);//registramos a la tabla
            BaseDeDatos.close();//Cerramos la base de datos;
            nombreContacto.setText("");
            telefono.setText("");
            Toast.makeText(ModuloContactoActivity.this,"Registro exitoso", Toast.LENGTH_SHORT).show();
            listarContactos();
        }else{
            Toast.makeText(ModuloContactoActivity.this,"Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }


    }


    public void listarContactos(){
        ArrayList<ModelContactosActivity> lista= obtenerContactos();
        if(!lista.isEmpty()){
            ArrayAdapter<ModelContactosActivity> adaptador = new ArrayAdapter<ModelContactosActivity>(ModuloContactoActivity.this, android.R.layout.simple_list_item_1,lista);
            listaContactos.setAdapter(adaptador);
        }
    }



    public ArrayList<ModelContactosActivity> obtenerContactos() {
        SQLiteDatabase BaseDeDatos=admin.getWritableDatabase();
        //select * from contactos
        Cursor cursor= BaseDeDatos.rawQuery("SELECT * FROM contactos",null);
        ArrayList<ModelContactosActivity> lista=new ArrayList<ModelContactosActivity>();


        while(cursor.moveToNext()){
            ModelContactosActivity cm=new ModelContactosActivity();
            cm.setId(cursor.getInt(0));
            cm.setContacto(cursor.getString(1));
            cm.setTelefono(cursor.getString(2));
            lista.add(cm);
        }
        BaseDeDatos.close();
        return lista;
    }

}