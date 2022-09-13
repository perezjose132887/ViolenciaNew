package com.example.violencia;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Telephony;
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

    Button eliminarLista,guardar,continuar;;
    ImageButton seleccionar;
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
        continuar=(Button) findViewById(R.id.btnContinuar);
        listaContactos=(ListView) findViewById(R.id.lvListaContactos);

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
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ModuloContactoActivity.this,NavigationDrawer.class);
                startActivity(intent);
                finish();
            }
        });




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




    }




    public void eliminarListView(){
        SQLiteDatabase db = admin.getWritableDatabase();
        db.delete("contactos",null,null);
        db.execSQL("DROP TABLE contactos");
        db.execSQL("create table if not exists contactos(idContacto integer primary key autoincrement, nombre text, numero int)");
        Toast.makeText(ModuloContactoActivity.this,"La lista a sido limpiado",Toast.LENGTH_SHORT).show();
        db.close();
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

    private ArrayList<ModelContactosActivity> obtenerContactos() {
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